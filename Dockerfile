FROM amazoncorretto:11-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} my-new.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/my-new.jar"]