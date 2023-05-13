FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/auth-service

ADD target/auth-service.jar /app/auth-service/auth-service.jar
ENTRYPOINT ["java", "-jar", "/app/auth-service/auth-service.jar"]

EXPOSE 8082