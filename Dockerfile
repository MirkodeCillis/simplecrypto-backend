FROM openjdk:8-alpine
EXPOSE 8855
MAINTAINER mirkodecillis
COPY target/simplecrypto-server-1.0.0.jar simplecrypto-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/simplecrypto-server-1.0.0.jar"]