FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/notification-service

ADD target/notification-service.jar /app/notification-service/notification-service.jar
ENTRYPOINT ["java", "-jar", "/app/notification-service/notification-service.jar"]

EXPOSE 8084