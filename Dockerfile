# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only what's needed
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy only the final JAR from build stage
COPY --from=build /app/target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "portfolio.jar"]
