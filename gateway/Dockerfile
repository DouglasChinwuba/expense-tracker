FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/gateway

ADD target/gateway.jar /opt/gateway/gateway.jar
ENTRYPOINT ["java", "-jar", "/opt/gateway/gateway.jar"]

EXPOSE 8081