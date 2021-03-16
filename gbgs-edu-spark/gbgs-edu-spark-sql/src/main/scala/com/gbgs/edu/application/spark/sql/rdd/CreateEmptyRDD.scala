package com.gbgs.edu.application.spark.sql.rdd

import org.apache.spark.sql.SparkSession

object CreateEmptyRDD extends App {

  type dataType = (String, Int)
  val spark: SparkSession = SparkSession.builder()
    .master("local[3]")
    .appName("SparkByExamples.com")
    .getOrCreate()
  val rdd = spark.sparkContext.emptyRDD

  println(rdd)
  println(rddString)
  println("Num of Partitions: " + rdd.getNumPartitions)

  //rddString.saveAsTextFile("test.txt") // returns error
  val rddString = spark.sparkContext.emptyRDD[String]
  println(rdd2)
  println("Num of Partitions: " + rdd2.getNumPartitions)

  //rdd2.saveAsTextFile("test3.txt")

  // Pair RDD
  val rdd2 = spark.sparkContext.parallelize(Seq.empty[String])
  var pairRDD = spark.sparkContext.emptyRDD[dataType]
  println(pairRDD)

}
