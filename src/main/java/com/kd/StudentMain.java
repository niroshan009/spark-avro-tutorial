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
        String avroFile ="src/main/resources/student.avro";
        SparkConf conf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        SparkSession sparkSession = SparkSession.builder().appName("testsql").master("local[*]").config("spark.sql.warehouse.dir", "file:///~/tmp").getOrCreate();

        Schema avroSchema = new Parser().parse(new File(schemaFilePath));

        // Step 2: Convert the Avro schema to Spark StructType
        StructType sparkSchema = (StructType) SchemaConverters.toSqlType(avroSchema).dataType();



        System.out.println(Paths.get(schemaFilePath).toUri().getPath());

        Dataset<Row> avroData = sparkSession.read().format("avro")
                .schema(sparkSchema)
                 .load(avroFile);
//
        avroData.show(false);

        List<String> columnNames = Arrays.asList("education", "education.subjects");

        Map<String, Column> columns = new HashMap<>();
        columns.put("education", explode(col("education")));
        columns.put("subjects", explode(col("education.subjects")));

        for(Map.Entry<String, Column> entry : columns.entrySet()) {
            avroData = avroData.withColumn(entry.getKey(), entry.getValue());

        }


        avroData.show(false);














//        avroData = avroData.select(col("id"), col("name"), col("education.school"), col("education.school_rank"), col("subjects.name").alias("sub_name"), col("subjects.subject_rank"));
//
//        avroData.show(false);
//
//
//
//        avroData.groupBy("id")
//                                .pivot("school")
//                .pivot("sub_name")    // Pivot by school name
//                                .agg(
//                                        first("school_rank").alias("school_rank"), // Get the school rank
//                                        first("sub_name").alias("sub_name"), // Get the subject name
//                                        first("subject_rank").alias("subject_rank") // Get the subject rank
//                                )
//
//                .show(false);





//        avroData
//
//                .withColumn( )
//                .withColumn("education", explode(col("education")))  // First explode
//                .withColumn("subjects", explode(col("education.subjects"))) // Second explode
//
//                .selectExpr("id", "name", "education.school", "education.school_rank", "subjects.name as subject_name")
//                .show(false);




//        avroData.selectExpr("id","name","explode(education) as education")
//                .withColumn("school", col("education.school"))
//                .withColumn("rank", col("education.school_rank"))
//                .withColumn("subject", col("subjects.name"))
//                .withColumn("subject_name", col("subjects.name"))
//                .select("name", "school" , "rank", "subject_name")
//                .show(false);


    }
}
