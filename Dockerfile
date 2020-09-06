FROM openjdk:8
WORKDIR /usr/src/app
COPY rosie.jar .
COPY .env .
ENTRYPOINT ["java", "-jar", "rosie.jar"]