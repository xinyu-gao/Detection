FROM openjdk:8-alpine
VOLUME /tmp
COPY target/detection-1.0.0.jar detection.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]