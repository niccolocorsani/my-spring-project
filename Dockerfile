FROM openjdk:11-jre
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD echo "sto eseguendo il docker fileeeeeeeee"
ENTRYPOINT ["java","-jar","/app.jar"]