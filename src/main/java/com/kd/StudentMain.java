package com.kd;

import org.apache.avro.Schema;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.avro.SchemaConverters;
import org.apache.spark.sql.types.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.avro.Schema.Parser;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


import static org.apache.spark.sql.functions.*;

public class StudentMain {

    public static void main(String[] args) throws IOException {


        Logger.getLogger("org.apache").setLevel(Level.WARN);

        String schemaFilePath = "src/main/resources/avro/student.avsc";
        String avroFile = "src/main/resources/student.avro";
        SparkConf conf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        SparkSession sparkSession = SparkSession.builder().appName("testsql").master("local[*]").config("spark.sql.warehouse.dir", "file:///~/tmp").getOrCreate();

        Schema avroSchema = new Parser().parse(new File(schemaFilePath));

        // Step 2: Convert the Avro schema to Spark StructType
        StructType sparkSchema = (StructType) SchemaConverters.toSqlType(avroSchema).dataType();


        System.out.println(Paths.get(schemaFilePath).toUri().getPath());

        Dataset<Row> studentDataset = sparkSession.read().format("avro")
                .schema(sparkSchema)
                .load(avroFile);


        studentDataset.show(false);

        Dataset<Row> referenceDataset = getReferenceDataset(sparkSession);

        // exploding columns the child objects to add values
        Map<String, Column> columns = new HashMap<>();
        columns.put("education", explode(col("education")));
        columns.put("subjects", explode(col("education.subjects")));

        // exploding the actual object to columns
        for (Map.Entry<String, Column> entry : columns.entrySet()) {
            studentDataset = studentDataset.withColumn(entry.getKey(), entry.getValue());
        }

        referenceDataset.show(false);

        // expression to do the join with reference data
        studentDataset = studentDataset
                .join(
                        referenceDataset,
                        functions.expr("education.school_rank = school_rank and subjects.subject_rank = subject_rank").cast("boolean"),
                        "left"
                )
                .select(col("*"));


        // amend the values based on the external configuration
        Dataset<Row> columnsToSelect = getSelectColumn(sparkSession);
        List<AmendConfiguration> groupLogicConfigList = columnsToSelect.as(Encoders.bean(AmendConfiguration.class)).collectAsList();

        studentDataset = amendNewValueAndConstruct(studentDataset, groupLogicConfigList);


        // Reconstructing the original object with added values

        // group and aggregation dataset which will fetch from outside
        // this will be used for reconstruct the objects
        Dataset<Row> groupAggDs = getGroupByDS(sparkSession);
        groupAggDs.show(false);
        List<GroupLogicConfig> groupLogicConfigs = groupAggDs.as(Encoders.bean(GroupLogicConfig.class)).collectAsList();

        Dataset<Row> groupDataset = reconstructTheObject(studentDataset, groupLogicConfigs);
        groupDataset.show(false);

    }


    private static Dataset<Row> reconstructTheObject(Dataset<Row> rowDataset, List<GroupLogicConfig> groupLogicConfigList) {

        for (GroupLogicConfig groupLogicConfig : groupLogicConfigList) {


            Column[] groupByColumns = Arrays.stream(groupLogicConfig.getGroupColumns().split(","))
                    .map(functions::col)
                    .toArray(Column[]::new);

            Column[] aggColumns = Arrays.stream(groupLogicConfig.getAggregateColumns().split(","))
                    .map(functions::col)
                    .toArray(Column[]::new);


            Column aggColumn;

            if (groupLogicConfig.getStruct()) { // if it is a struct (object type to be created)
                aggColumn = collect_list(struct(aggColumns)).alias(groupLogicConfig.getAlias());
            } else {
                aggColumn = collect_list(groupLogicConfig.getAggregateColumns()).alias(groupLogicConfig.getAlias());
            }

            rowDataset = rowDataset
                    .groupBy(groupByColumns)
                    .agg(aggColumn);


        }

        return rowDataset;
    }

    private static Dataset<Row> getGroupByDS(SparkSession sparkSession) {

        StructType struct = new StructType(new StructField[]{
                new StructField("order", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("groupColumns", DataTypes.StringType, false, Metadata.empty()),
                new StructField("aggregateColumns", DataTypes.StringType, false, Metadata.empty()),
                new StructField("struct", DataTypes.BooleanType, false, Metadata.empty()),
                new StructField("alias", DataTypes.StringType, true, Metadata.empty())
        });

        List<Row> groupByAggColList = Arrays.asList(
                RowFactory.create(1, "id,name,education.school,education.school_rank", "subjects", false, "subjects"),
                RowFactory.create(2, "id,name", "school,school_rank,subjects", true, "education")
        );

        return sparkSession.createDataFrame(groupByAggColList, struct);
    }

    private static Dataset<Row> getSelectColumn(SparkSession sparkSession) {
        StructType struct = new StructType(new StructField[]{
                new StructField("columnToSelect", DataTypes.StringType, false, Metadata.empty()),
                new StructField("groupColumns", DataTypes.StringType, false, Metadata.empty())
        });

        List<Row> columnsToSelectList = List.of(
                RowFactory.create("subjects", "subjects.name:subjects.name;subjects.subject_rank:subjects.subject_rank;subjects.grade:subjects.grade;rating:rating")
        );

        return sparkSession.createDataFrame(columnsToSelectList, struct);


    }

    private static Dataset<Row> getReferenceDataset(SparkSession sparkSession) {
        StructType struct = new StructType(new StructField[]{
                new StructField("school_rank", DataTypes.StringType, false, Metadata.empty()),
                new StructField("subject_rank", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("rating", DataTypes.StringType, false, Metadata.empty())

        });

        List<Row> cityCodeList = Arrays.asList(
                RowFactory.create("A", 1, "Excellent"),
                RowFactory.create("A", 2, "Above Average"),
                RowFactory.create("A", 3, "Poor"),
                RowFactory.create("B", 1, "Good"),
                RowFactory.create("B", 2, "Average"),
                RowFactory.create("B", 3, "Poor"));

        return sparkSession.createDataFrame(cityCodeList, struct);
    }

    private static Dataset<Row> amendNewValueAndConstruct(Dataset<Row> dataset, List<AmendConfiguration> amendConfigurationList) {

        for (AmendConfiguration amendConfiguration : amendConfigurationList) {

            String structColumn = amendConfiguration.getColumnToSelect();

            Column[] groupByColumns = Arrays.stream(amendConfiguration.getGroupColumns().split(";"))
                    .map(e -> {

                        String[] colAndAlias = e.split(":");
                        String col = colAndAlias[0];
                        String alias = colAndAlias[1];

                        return functions.col(col).alias(alias);

                    })
                    .toArray(Column[]::new);

            dataset = dataset.withColumn(structColumn, struct(groupByColumns));

        }

        return dataset;
    }
}
