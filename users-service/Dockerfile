FROM openjdk:12
VOLUME /tmp
ADD ./target/users-service-0.0.1-SNAPSHOT.jar users-service.jar
ENTRYPOINT ["java", "-jar","/users-service.jar"]
