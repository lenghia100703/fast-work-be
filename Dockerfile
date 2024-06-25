FROM maven:3.9.7-eclipse-temurin-17-alpine as builder

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN mvn clean package -DskipTests

FROM openjdk:17.0.2-jdk-oracle

COPY --from=builder /usr/src/app/target/*.jar /app.jar

EXPOSE 8888

ENTRYPOINT ["java"]

CMD ["-jar", "/app.jar"]