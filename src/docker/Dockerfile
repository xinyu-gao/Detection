FROM openjdk:11-alpine
VOLUME /tmp
COPY target/detection-1.0.0.jar detection.jar
ENTRYPOINT ["java","-jar","/app.jar"]