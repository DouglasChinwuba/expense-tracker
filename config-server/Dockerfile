FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/config-server

ADD target/config-server.jar /app/config-server/config-server.jar
ENTRYPOINT ["java", "-jar", "/app/config-server/config-server.jar"]

EXPOSE 8888