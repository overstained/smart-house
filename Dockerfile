FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
ENV DEVICE_ID 12A53-EFDW4-ASD12-FSD34

COPY target/smart-house-manager-0.1.jar $PROJECT_HOME/smart-house-manager-0.1.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-Dspring.data.mongodb.uri=mongodb://springboot-mongo:27017/smarthome","-Djava.security.egd=file:/dev/./urandom","-jar","./smart-house-manager-0.1.jar"]