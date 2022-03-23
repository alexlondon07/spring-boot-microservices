FROM openjdk:12
VOLUME /tmp
EXPOSE 9090
ADD ./build/libs/gateway-server-service-0.0.1-SNAPSHOT.jar gateway-server-service.jar
ENTRYPOINT ["java", "-jar","/gateway-server-service.jar"]
