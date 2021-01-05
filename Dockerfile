FROM openjdk:11
ADD detection-1.0.0.jar /detection.jar
RUN bash -c 'touch /detection.jar'
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "/detection.jar"]