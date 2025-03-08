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

        Map<String, Column> columns = new HashMap<>();
        columns.put("education", explode(col("education")));
        columns.put("subjects", explode(col("education.subjects")));

        for (Map.Entry<String, Column> entry : columns.entrySet()) {
            studentDataset = studentDataset.withColumn(entry.getKey(), entry.getValue());

        }

        referenceDataset.show(false);


        studentDataset = studentDataset
                .join(
                        referenceDataset,
                        functions.expr("education.school_rank = school_rank and subjects.subject_rank = subject_rank").cast("boolean"),
                        "left"
                )

                .select(
                        col("*")
                );

        studentDataset.show(false);

        Dataset<Row> modifiedData = studentDataset
                .withColumn("subjects", struct(
                        col("subjects.name"),
                        col("subjects.subject_rank"),
                        col("subjects.grade"),
                        col("rating").alias("rating")
                ))
                .groupBy("id", "name", "education.school")
                .agg(collect_list("subjects").alias("subjects"))
                .groupBy("id", "name")
                .agg(collect_list(struct(col("school"), col("subjects")))).alias("education");


        modifiedData.show(false);


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

        Dataset<Row> cityCode = sparkSession.createDataFrame(cityCodeList, struct);
        return cityCode;
    }
}
