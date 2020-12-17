download and install "MYSQL" and "mongodb"

download and copy "servlet-api.jar", "jsp-api.jar", "el-api.jar", "commons-beanutils-1.8.3.jar", "mysql-connector-java-8.0.21.jar", "mongo-java-driver-3.2.2.jar", "gson-2.3.1.jar" and "gson-2.6.2.jar" into lib folder of apache-tomcat and also include the paths of these external libraries as classpath variable

create "database" and necessory "tables" in MYSQL or import database from MYSQL_DATABASE folder that contains all MYSQL data required for this assignment

create "database" and "collection" in mongodb
databse name : "CustomerReviews"
collection name : "myReviews"

start "MYSQL", "mongodb" and "apache" server
type "localhost/BestDeal/" in browser to open and use an application

to create CSV files of all MYSQL tables and MONGODB reviews:
-create "CSVs" folder in same directory of "dump_db_tables_to_csv_files.ipynb" file
-run "dump_db_tables_to_csv_files.ipynb" file in anaconda jupyter notebook

to create NEO4J graph databas:
-download and install NEO4J software
-create project with name "BestDeal"
-install plugins "APOC" and "Graph Data Science Library"
-create local database named "Graph"
-start this "Graph" database and then open NEO4J browser
-to create a graph copy cyper code of graph creation part from "cyper_script.txt" and past in terminal of browser and then run it
-to run queries on this graph database copy cyper code of query part from "cyper_script.txt" and past in terminal of browser and then run it