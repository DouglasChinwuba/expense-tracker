FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/gateway

ADD target/gateway.jar /app/gateway/gateway.jar
ENTRYPOINT ["java", "-jar", "/app/gateway/gateway.jar"]

EXPOSE 8081