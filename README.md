# issue-tracker

A simple issue tracking application. 
Status: :sun_with_face: Infrastructure in place. :construction: Application features under construction.  

- H2
- Java
- Maven
- React
- REST
- Spring Boot
- TypeScript
- Webpack

## Packaging

Java and npm are required to build and run the application.

First transpile and bundle the TSX and CSS source files to JavaScript by running `npx webpack`. This will transpile the TypeScript JSX into JavaScript and create src/main/resources/static/dist/bundle.js.

After building bundle.js, create a .jar file (e.g. issuetracker.jar):

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

### Get all projects:

Endpoint: /projects<br/>
Parameters: None

Example:
http://localhost:8080/projects
