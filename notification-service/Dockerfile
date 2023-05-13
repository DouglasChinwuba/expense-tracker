FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/notification-service

ADD target/notification-service.jar /opt/notification-service/notification-service.jar
ENTRYPOINT ["java", "-jar", "/opt/notification-service/notification-service.jar"]
