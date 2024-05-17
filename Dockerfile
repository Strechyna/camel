FROM openjdk:17-alpine

LABEL authors="ValeryiaStrechyna"
LABEL version="0.0.1-SNAPSHOT"

WORKDIR /app
VOLUME /app/data

COPY target/camel-app-0.0.1-SNAPSHOT.jar /app/camel-app.jar
COPY /data /app/data

EXPOSE 8080

ENTRYPOINT exec java -jar camel-app.jar