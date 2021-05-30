# Step : Test and package
FROM maven:3.5.3-jdk-8-alpine as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

# Step : Package image
FROM openjdk:8-jre-alpine
EXPOSE 8855
CMD exec java -jar /app/simplecrypto-server-1.0.0.jar
COPY --from=target /build/target/*jar-with-dependencies.jar /app/simplecrypto-server-1.0.0.jar