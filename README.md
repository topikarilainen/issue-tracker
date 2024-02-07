# issue-tracker

A simple issue tracking application.

## Packaging

Create a .jar file (e.g. issuetracker.jar):

### Windows

`.\mvnw package`

### Linux

`./mvnw package`

## Running

Run the jar file:

`java -jar teapot.jar`

## Viewing the sample data

An in-memory H2 database is used for development. When the application is running, you can view the H2 database console at http://localhost:8080/h2-console/. The connection settings are:

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: blank

