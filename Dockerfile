FROM openjdk:17.0
ARG JAR_FILE=target/*.jar
EXPOSE 8088
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "/app.jar"]