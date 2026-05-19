# Stage 1: Build the Maven application inside Render's cloud container
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final lightweight production runtime environment
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ecommerce_project-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
