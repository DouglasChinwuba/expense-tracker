FROM amazoncorretto:11
MAINTAINER Douglas Chinwuba <cemekadouglas@yahoo.com>

RUN mkdir /app/discovery-server

ADD target/discovery-server.jar /app/discovery-server/discovery-server.jar
ENTRYPOINT ["java", "-jar", "/app/discovery-server/discovery-server.jar"]

EXPOSE 8761