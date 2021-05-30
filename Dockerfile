FROM openjdk:8-alpine
EXPOSE 8855
MAINTAINER mirkodecillis
RUN apk add maven
WORKDIR /app
COPY . /app/
RUN mvn -f /app/pom.xml clean install -DskipTests
WORKDIR /app
COPY target/simplecrypto-server-1.0.0.jar /simplecrypto-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/simplecrypto-server-1.0.0.jar"]