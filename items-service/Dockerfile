FROM openjdk:12
VOLUME /tmp
EXPOSE 8888
ADD ./build/libs/items-service-0.0.1-SNAPSHOT.jar items-service.jar
ENTRYPOINT ["java", "-jar","/items-service.jar"]
