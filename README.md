# Customer-Service Spring-boot Application

This is a REST Service application used to manage customer records..

### API Supported
•	INSERT a customer record into database

•	UPDATE a customer Record into database

•	DELETE a customer Record from Database

•	GET a customer record by Customer Number 

•	GET ALL Customers from database 

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org)
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- [Redis](https://redis.io/)
- [SwaggerOpenApi](http://localhost:8080/customer-service/swagger-ui)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
./gradlew bootRun
```

Start redi cache before running application. The current version is enabled basic auth with mongoDb. Once application started successfuly 
run [SwaggerOpenApi](http://localhost:8080/customer-service/swagger-ui) to see the api details.

