# heycar

This project is written with Java 16

There is a Dockerfile to create a container.

Maven was used for dependency management.

In order to keep code simple, I used Lombok. Also for mapping, MapStruct was used.

Swagger added for testing.

Swagger URL:

http://localhost:8080/swagger-ui/

`mvn clean install`

Builds and create the latest image of the project.

`docker run -p 8080:8080 --name heycar yunuskilicdev/heycar`

Above command for running container.

`mvn test` for testing

For CSV mapping, I used Jackson Mapper.

For searching, I used Specification.

Model classes are not used directly as a best practice. DTO objects comes from out, I returned resource object to client.

For simplicity, I used H2 db.

**Improvements**

* Caching
* Code coverage tool integration

