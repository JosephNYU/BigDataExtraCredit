import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object FirstCode {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Data Analysis and Cleaning")
      .getOrCreate()

    // Load data
    val filePath = "/home/jjh9518_nyu_edu/Transportation.csv"
    var data = spark.read.option("header", "true").csv(filePath)

    // Data cleaning operations
    // 1. Normalize text in 'Table 1-41' to lowercase
    data = data.withColumn("Table 1-41", lower(col("Table 1-41")))

    // 2. Create a binary column based on a condition from the '1989' column
    val median_1989 = data.stat.approxQuantile("1989", Array(0.5), 0.0).head
    data = data.withColumn("Above Median 1989", when(col("1989") > median_1989, 1).otherwise(0))

    // Show updated DataFrame (for debugging purposes)
    data.show()

    // Write the cleaned data to a new CSV (optional)
    data.write.option("header", "true").csv("/home/jjh9518_nyu_edu/cleaned_data.csv")
  }
}

// This reads a CSV file, performs tasks:
    // normalizing text fields
    // and creating a binary column based on median comparison.
