FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
ADD src ./src
RUN mvn clean package

FROM openjdk:17.0.2-jdk
WORKDIR  /app
COPY --from=builder /app/target/smartparking-1.0.0.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]