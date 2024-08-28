FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/notifications-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
