FROM eclipse-temurin:21-jre-jammy
ARG JAR_FILE=build/libs/person-ms-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]