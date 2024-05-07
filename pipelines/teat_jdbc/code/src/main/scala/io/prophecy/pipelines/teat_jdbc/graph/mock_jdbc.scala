package io.prophecy.pipelines.teat_jdbc.graph

import io.prophecy.libs._
import io.prophecy.pipelines.teat_jdbc.config.Context
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object mock_jdbc {

  def apply(context: Context): DataFrame = {
    val Config = context.config
    import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
    var reader = context.spark.read.format("jdbc")
    reader = reader
      .option("url",                "jdbc://jdbcurl.com")
      .option("user",               s"${Config.username}")
      .option("password",           s"${Config.pass}")
      .option("pushDownPredicate",  true)
      .option("driver",             "org.postgresql.Driver")
    reader = reader.option("query", "select a,b from table1 where a < b;")
    var df = reader.load()
    df
  }

}
