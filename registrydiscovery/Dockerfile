FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/registrydiscovery-0.0.1-SNAPSHOT.jar /app/registrydiscovery.jar
EXPOSE 8761
CMD ["java", "-jar", "registrydiscovery.jar"]
