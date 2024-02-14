# issue-tracker

A simple issue tracking application.

## Packaging

First transpile TypeScript source files to JavaScript by running `tsc --build`. The TypeScript compiler (tsc) is required.

After transpiling .ts to .js, create a .jar file (e.g. issuetracker.jar):

### Windows

`.\mvnw package`

### Linux

`./mvnw package`

## Running

Run the jar file:

`java -jar issuetracker.jar`

Access the application at http://localhost:8080.

## Viewing the sample database

An in-memory H2 database is used for development. When the application is running, you can view the H2 database console at http://localhost:8080/h2-console/. The connection settings are:

Driver Class: org.h2.Driver<br/>
JDBC URL: jdbc:h2:mem:testdb<br/>
Username: sa<br/>
Password: blank<br/>

## Using the REST service

### Get all issues in a project:

Endpoint: /issues<br/>
Parameters: projectAbbreviation - the abbreviation of the project. See the example below.

Example:
http://localhost:8080/issues?projectAbbreviation=SIA