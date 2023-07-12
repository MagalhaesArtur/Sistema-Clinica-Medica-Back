FROM maven:3.9.2-openjdk-20 AS build
VOLUME /tmp
# COPY target/*.jar app.jar
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:20-jdk-slim
COPY --from=build /target/ demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]