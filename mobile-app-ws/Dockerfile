# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file to the container
COPY target/mobile-app-ws-0.0.1-SNAPSHOT.jar /app/mobile-app-ws.jar

# Expose the port your app runs on
EXPOSE 8200

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/mobile-app-ws.jar"]
