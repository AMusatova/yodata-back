FROM openjdk:17-alpine
COPY target/yodata-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]