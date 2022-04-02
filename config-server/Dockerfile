FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

ADD target/config-server.jar config-server.jar
ENTRYPOINT ["java", "-jar", "/config-server.jar"]

EXPOSE 8888