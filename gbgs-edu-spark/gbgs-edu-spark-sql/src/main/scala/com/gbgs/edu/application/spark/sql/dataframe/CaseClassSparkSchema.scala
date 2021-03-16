package com.gbgs.edu.application.spark.sql.dataframe

import org.apache.spark.sql.Encoders
import org.apache.spark.sql.types.StructType

object CaseClassSparkSchema extends App {

  val encoderSchema = Encoders.product[Employee].schema
  val schema = ScalaReflection.schemaFor[Employee].dataType.asInstanceOf[StructType]

  case class Name(first: String, last: String, middle: String)
  encoderSchema.printTreeString()

  import org.apache.spark.sql.catalyst.ScalaReflection

  case class Employee(fullName: Name, age: Integer, gender: String)

}
