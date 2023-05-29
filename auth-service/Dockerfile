FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/auth-service

ADD target/auth-service.jar /opt/auth-service/auth-service.jar
ENTRYPOINT ["java", "-jar", "/opt/auth-service/auth-service.jar"]

EXPOSE 8082