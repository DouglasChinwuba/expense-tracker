FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/account-service

ADD target/account-service.jar /opt/account-service/account-service.jar
ENTRYPOINT ["java", "-jar", "/opt/account-service/account-service.jar"]

EXPOSE 8083