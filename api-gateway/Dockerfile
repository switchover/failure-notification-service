FROM maven:3.8-openjdk-11 as builder
MAINTAINER swithover@gmail.com

WORKDIR /opt/app
COPY . .

RUN mvn clean package spring-boot:repackage -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre

ENV SPRING_PROFILES_ACTIVE docker

COPY --from=builder /opt/app/api-gateway/target/*.jar /opt/app/api-gateway.jar
ENTRYPOINT  ["java", "-jar", "/opt/app/api-gateway.jar"]
