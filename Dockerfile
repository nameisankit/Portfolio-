# -------- Build stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Work directory
WORKDIR /app

# POM & source copy
COPY pom.xml .
COPY src ./src

# Build JAR (tests skip)
RUN mvn clean package -DskipTests

# -------- Run stage --------
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Build stage se jar copy
COPY --from=build /app/target/*.jar app.jar

# Spring Boot default port
EXPOSE 8080

# Container start command
ENTRYPOINT ["java", "-jar", "app.jar"]
