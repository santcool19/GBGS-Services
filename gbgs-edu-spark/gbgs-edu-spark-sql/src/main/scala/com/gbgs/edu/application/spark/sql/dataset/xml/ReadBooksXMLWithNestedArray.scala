package com.gbgs.edu.application.spark.sql.dataset.xml

import com.gbgs.edu.application.spark.sql.beans.BooksWithArray
import org.apache.spark.sql.SparkSession

object ReadBooksXMLWithNestedArray {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()

    import spark.implicits._
    val ds = spark.sqlContext.read
      .format("com.databricks.spark.xml")
      .option("rowTag", "book")
      .load("src/main/resources/books_withnested_array.xml").as[BooksWithArray]

    ds.printSchema()
    ds.show()

    ds.foreach(f => {
      println(f.author + "," + f.otherInfo.country + "," + f.otherInfo.address.addressline1)
      for (s <- f.stores.store) {
        println(s.name)
      }

    })

  }
}

