
mc-connected-api
=============================
REST API to determine whether two cities are connected

Overview
--------
This is a starter project with Spring Boot and Java 1.8. 
Objective is to find whether there is a connection between the given two cities.
Two cities are connected if there's a series of roads that can be travelled from one city to another.

List of roads is available in a text file. 
The file contains a list of city pairs (one pair per line, comma separated), which indicates that there's a route between those cities.

Prerequisites
-------------
* Install JDK 1.8 or higher
* Install Git
* Install Maven
* Install an IDE or editor of your choice

Getting Started
---------------
* To build the project, run `mvn package`.
* To start the application, use one of the below mentioned approaches:
  * run the provided `startup.sh`.
  * run the project's jar file directly, `java -jar target/connectivity-0.0.1-SNAPSHOT.jar`.
  * run `mvn spring-boot:run` if the MAVEN_HOME is in system's PATH.
  * `Run as SpringBoot Application` If the IDE auto-detects as SrpingBoot App.
* The server will start on localhost on port 8080.

