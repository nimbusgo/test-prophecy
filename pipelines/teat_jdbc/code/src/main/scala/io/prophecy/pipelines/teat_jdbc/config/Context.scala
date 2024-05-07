package io.prophecy.pipelines.teat_jdbc.config

import org.apache.spark.sql.SparkSession
case class Context(spark: SparkSession, config: Config)
