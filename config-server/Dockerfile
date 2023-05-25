FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/config-server

ADD target/config-server.jar /opt/config-server/config-server.jar
ENTRYPOINT ["java", "-jar", "/opt/config-server/config-server.jar"]

EXPOSE 8888