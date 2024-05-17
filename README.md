// code for data ingestion
hdfs dfs -mkdir /home/jjh9518_nyu_edu/data_ingest
hdfs dfs -put /home/jjh9518_nyu_edu/Transportation.csv data_ingest/data/Transportation.csv



// ETL cleaning code
javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java
jar -cvf Clean.jar *.class
hadoop jar Clean.jar Clean /home/jjh9518_nyu_edu/Transportation.csv /data_ingest/data/



// Data Profiling Code
javac -classpath `yarn classpath` -d . UniqueRecsMapper.java
javac -classpath `yarn classpath` -d . UniqueRecsReducer.java
javac -classpath `yarn classpath`:. -d . UniqueRecs.java
jar -cvf UniqueRecs.jar *.class
hadoop jar UniqueRecs.jar UniqueRecs /home/jjh9518_nyu_edu/Transportation.csv /data_ingest/data/




// Running the Code
hdfs dfs -put /home/jjh9518_nyu_edu/Transportation.csv /data_ingest/data/
hdfs dfs jar Clean.jar Clean /data_ingest/data/Transportation.csv /home/jjh9518_nyu_edu/
hdfs dfs jar UniqueRecs.jar UniqueRecs /data_ingest/data/Transportation.csv /home/jjh9518_nyu_edu/



// Results
The input data `Transportation.csv` is located at `/home/jjh9518_nyu_edu/Transportation.csv` after ingestion.