
FROM maven:3.6.3-jdk-8

# copy the source tree and the pom.xml to our new container
COPY ./ ./

# package our application code
RUN mvn clean package
#FROM openjdk:11-jre
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]