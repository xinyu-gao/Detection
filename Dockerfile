FROM openjdk:11-alpine
VOLUME /tmp
COPY target/detection-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]