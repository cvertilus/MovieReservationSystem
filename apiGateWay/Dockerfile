FROM ubuntu:latest
LABEL author="Colby"

FROM openjdk:17

ARG JAR_FILE=target/apiGateWay.jar
COPY ${JAR_FILE} apiGateWay.jar
ENTRYPOINT ["java","-jar","/apiGateWay.jar"]
EXPOSE 8081

#docker build -t apiGateWay .
#docker run -p 8081:8081 apiGateWay