FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/songservice-0.0.1-SNAPSHOT.jar /app/song-service.jar
CMD ["java", "-jar", "song-service.jar"]