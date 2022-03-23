FROM openjdk:12
VOLUME /tmp
#EXPOSE 8888
ADD ./build/libs/service-product-0.0.1-SNAPSHOT.jar service-product.jar
ENTRYPOINT ["java", "-jar","/service-product.jar"]
