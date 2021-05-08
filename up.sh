#!/bin/bash
mvn clean spring-boot:run -Drun.arguments="--server.port=8855" -Dspring.profiles.active=local
