FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine-slim

COPY build/libs/app-*-all.jar app.jar

CMD ["java", "-jar", "app.jar", "$@"]
