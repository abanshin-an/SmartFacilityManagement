FROM bellsoft/liberica-openjdk-alpine:21
ARG JAR_FILE=build/libs/action-ms-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]