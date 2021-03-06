
MasterCard Coding Challenge
=============================
REST API to determine whether two cities are connected

Overview
--------
This is a starter project with Spring Boot and Java 1.8. 
Objective is to find whether there is a connection between the given two cities.
Two cities are connected if there's a series of roads that can be travelled from one city to another.

List of roads is available in a text file. 
The file contains a list of city pairs (one pair per line, comma separated), which indicates that there's a route between those cities.

It will be deployed as a `String Boot App` and exposes one endpoint:
http://localhost:8080/connected?origin=<cit1>&destination=<cit2>

This API will respond with `yes` if city1 is connected to city2,
`no` if city1 is not connected to city2.

Prerequisites
-------------
* Install JDK 1.8 or higher
* Install Git
* Install Maven
* Install an IDE or editor of your choice

Building and Running the App
----------------------------
* Application uses `city.txt` as roads/routes file from `src/main/resources` directory. We can update it, if we wish to test app on a different set of routes.
* To build the project, run `mvn package`.
* To start the application, use one of the below mentioned approaches:
  * run the provided `startup.sh`.
  * run the project's jar file directly, `java -jar target/connectivity-0.0.1-SNAPSHOT.jar`.
  * run `mvn spring-boot:run` if the MAVEN_HOME is in system's PATH.
  * `Run as SpringBoot Application` If the IDE auto-detects as SrpingBoot App.
* The server will start on localhost on port 8080.

Application Usage
------------------------------------
* Please use http://localhost:8080/swagger-ui.html link, to access API swagger dashboard as shown below.
  ![swagger ui dashboard](images/swagger-ui.png)
* API is fully public. We can use any other REST Client's or Browser to access the API.
  http://localhost:8080/connected?origin=Boston&destination=NewarkKapil 

Working with Code 
---------------------
* API developed using TDD approach and has exhaustive code coverage. We can use below commands:
  * `mvn clean test` for verifying unit test cases.
  * `mvn clean verify` for code coverage. The HTML coverage report can be found @`<project-home>target/site/jacoco/index.html`
    ![code-coverage](images/code-coverage.png)
  * `mvn clean site` for generating site documentation.
  
Feedback 
---------------------
  Thank you for giving me opportunity to build. Any kind of criticism or feedback is appreciated!
    

    
  