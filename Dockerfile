FROM maven:latest as builder
RUN mvn  install


FROM openjdk:11-jre
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]