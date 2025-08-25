FROM bellsoft/liberica-openjdk-alpine:21

ARG JAR_FILE=build/libs/facility-1.0.0.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]