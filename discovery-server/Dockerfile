FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /opt/discovery-server

ADD target/discovery-server.jar /opt/discovery-server/discovery-server.jar
ENTRYPOINT ["java", "-jar", "/opt/discovery-server/discovery-server.jar"]
