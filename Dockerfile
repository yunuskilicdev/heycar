FROM openjdk:16

ARG JAR_FILE
ADD target/${JAR_FILE} /HeycarApplication.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar","/HeycarApplication.jar"]