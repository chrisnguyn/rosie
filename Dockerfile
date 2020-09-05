FROM openjdk:8
WORKDIR /usr/src/app
COPY rosie-1.0-jar-with-dependencies.jar .
COPY .env .
ENTRYPOINT ["java", "-jar", "rosie-1.0-jar-with-dependencies.jar"]