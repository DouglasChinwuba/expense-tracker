FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/account-service

ADD target/account-service.jar /app/account-service/account-service.jar
ENTRYPOINT ["java", "-jar", "/app/account-service/account-service.jar"]

EXPOSE 8083
