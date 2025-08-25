FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY build/libs/gateway-ms-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]