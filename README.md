## Description
A demonstration project implementing a microservice and application to manipulate
simple user or customer information.
I documented the order in which I approached the task through the devlog.md file, on the off-chance that such would
prove interesting to someone.
## Requirements
The minimum viable product should meet the definition found in [Google Docs](https://docs.google.com/document/d/10p2Zo8YUmY_zQ218MfT02PXENH8OzRJaS_OFcPu4V5A/edit).
Since no particular database was required, I went with H2 for ease.
## Building
* Build with unit tests

        mvn clean package

* Build without unit tests

        mvn clean package -DskipTests
## Running
* Execute the output JAR with the Java JRE

        java -jar target/lifeway-demo-0.0.1-SNAPSHOT.jar
* Access the Swagger UI for the endpoints by pointing a browser at the following URI. The five API endpoints are found under the **persons-controller** subsection

        http://localhost:8080/api/swagger-ui/

## Next steps
1. I did not have time to both learn React and create my first app, so getting that part working would be great
2. The population of unit tests for the controller needs to be expanded
3. The data validation capabilities of the Model should be expanded
4. Review use of strings and constants to determine if any would be better located in application.properties
5. Add authentication and authorization capabilities