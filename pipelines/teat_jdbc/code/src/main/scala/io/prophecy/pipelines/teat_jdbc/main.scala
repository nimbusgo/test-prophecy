package io.prophecy.pipelines.teat_jdbc

import io.prophecy.libs._
import io.prophecy.pipelines.teat_jdbc.config._
import io.prophecy.pipelines.teat_jdbc.udfs.UDFs._
import io.prophecy.pipelines.teat_jdbc.udfs.PipelineInitCode._
import io.prophecy.pipelines.teat_jdbc.graph._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Main {

  def apply(context: Context): Unit = {
    val df_mock_jdbc = mock_jdbc(context)
  }

  def main(args: Array[String]): Unit = {
    val config = ConfigurationFactoryImpl.getConfig(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("teat_jdbc")
      .config("spark.default.parallelism",             "4")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()
    val context = Context(spark, config)
    spark.conf.set("prophecy.metadata.pipeline.uri", "pipelines/teat_jdbc")
    registerUDFs(spark)
    MetricsCollector.instrument(spark, "pipelines/teat_jdbc") {
      apply(context)
    }
  }

}
