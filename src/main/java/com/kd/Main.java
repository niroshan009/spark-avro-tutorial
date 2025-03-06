package com.kd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kd.generated.avro.schema.GeneratedAvroSchemaTest;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Encoder;
import org.apache.hadoop.yarn.webapp.ToJSON;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkConf$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.avro.SchemaConverters;
import org.apache.spark.sql.types.*;
import scala.Function1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.avro.SchemaConverters;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        String schemaFilePath = "src/main/resources/avro/user.avsc";
        String avroFile ="src/main/resources/sample.avro";
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

        avroData.show(false);


        avroData.selectExpr("id","first_name","last_name","explode(address) as address")
                .withColumn("city", col("address.city"))
                .withColumn("living_cost", col("address.living_cost"))
                .show(false);

        System.out.println("=================================");


        StructType struct = new StructType(new StructField[]{
                new StructField("city", DataTypes.StringType, false, Metadata.empty()),
                new StructField("city_code", DataTypes.StringType, false, Metadata.empty()),

        });


        List<Row> cityCodeList = Arrays.asList(
                RowFactory.create("Sidem", "SD"),
                RowFactory.create("Taverny", "TV"),
                RowFactory.create("Nizhniye Vyazovyye", "NV"),
                RowFactory.create("Ta‘izz", "TZ"),
                RowFactory.create("Baliang", "BG"),
                RowFactory.create("Houston", "HZ"),
                RowFactory.create("Cube", "CB"),
                RowFactory.create("Topchikha", "TP"),
                RowFactory.create("Xijiang ", "XJ"),
                RowFactory.create("Rengat", "RG"),
                RowFactory.create("Raga", "RA"),
                RowFactory.create("Kota Kinabalu ", "KK"),
                RowFactory.create("San Diego ", "SD"),
                RowFactory.create("Qishui ", "QI"),
                RowFactory.create("Pskov ", "PS"),
                RowFactory.create("Caseros ", "CSR"),
                RowFactory.create("Lengshui ", "LG"),
                RowFactory.create("Żabieniec ", "ZB"),
                RowFactory.create("Fenkeng ", "FK"));

        Dataset<Row> cityCode = sparkSession.createDataFrame(cityCodeList, struct);

        cityCode.show(false);

        avroData.selectExpr("id","first_name","last_name","explode(address) as address")
                .withColumn("city", col("address.city"))
                .withColumn("living_cost", col("address.living_cost"))
                .where("address.living_cost > 1000")
                .select("first_name", "last_name", "address", "city" , "living_cost")
                .show(false);

//        avroData.selectExpr("id","first_name","last_name","explode(address) as address")
//                .withColumn("city", col("address.city"))
//                .withColumn("living_cost", col("address.living_cost"))
//                .where("address.living_cost > 1000")
//                .join(cityCode, avroData.col("address.city").equalTo(cityCode.col("city")),"left")
//                .select("first_name", "last_name", "address", "city" , "living_cost", "city_code")
//                .show(false);


//                .join(cityCode, functions.col("address.city").like(cityCodeDF.col("city").concat("%")), "left") // Use like for starts with
//        .join(cityCode, functions.col("address.city").like("%" + cityCodeDF.col("city")), "left") // Use like for ends with

        List<String> columnList = Arrays.asList("first_name","last_name", "address.city", "address.living_cost", "city_code");




        Column[] columnsToSelect = columnList.stream().map(functions::col).toArray(Column[]::new);


        avroData
                .withColumn("address", functions.expr("explode(address)"))
                .join(
                        cityCode,
                        functions.expr("address.city like concat('%', city)").cast("boolean"),  // Ends with logic using expr
                        "left"
                )

                .select(
                       columnsToSelect
                )
                .show(false);


//                .withColumn("living_cost", col("address.living_cost"))
//                .where("address.living_cost > 1000")
//                .join(cityCode, avroData.col("address.city").equalTo(cityCode.col("city")),"left")
//                .select("first_name", "last_name", "address", "city" , "living_cost", "city_code")
//                .show(false);




    }
}
