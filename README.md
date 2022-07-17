# Apache Spark Application using Splunk

This application is an example of apache spark using Splunk

Technologies:

- Maven/JDK11
- Apache Spark 
- Guice
- AWS SQS
- Splunk

### Run project

1.Download Apache Spark

```bash
wget https://dlcdn.apache.org/spark/spark-3.2.1/spark-3.2.1-bin-hadoop3.2.tgz
```

2.Unzip download

```bash
mkdir ~/hadoop/spark-3.2.1
tar -xvzf spark-3.2.1-bin-hadoop3.2.tgz -C ~/hadoop/spark-3.2.1 --strip 1
```

3.Export environment variables

```bash
export SPARK_HOME=~/hadoop/spark-3.2.1                                
export PATH=$SPARK_HOME/bin:$PATH
export HADOOP_HOME=~/hadoop/spark-3.2.1                                
export PATH=$HADOOP_HOME/bin:$PATH
# Configure Spark to use Hadoop classpath
export SPARK_DIST_CLASSPATH=$(hadoop classpath)
# Source the modified file to make it effective:
source  ~/.bashrc
```

4.Execute service:

```bash
$. docker compose up -d
```

5.Build Jar

```bash
$. mvn clean install
```