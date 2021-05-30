FROM maven:3-jdk-8
FROM openjdk:8-alpine

ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

# 1. add pom.xml only here
ADD pom.xml $HOME

# 2. start downloading dependencies
RUN mvn verify clean --fail-never

# 3. add all source code and start compiling
ADD . $HOME
RUN ["mvn", "package"]
EXPOSE 8855

CMD ["java", "-jar", "./target/simplecrypto-server-1.0.0.jar"]
