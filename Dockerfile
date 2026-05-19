# Step 1: Use an official lightweight OpenJDK image as the base environment
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the cloud container
WORKDIR /app

# Step 3: Copy your successful local .jar build from target/ into the container
COPY target/ecommerce_project-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Define the launch instruction to fire up the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
