FROM openjdk:8
ADD target/rosie-1.0-jar-with-dependencies.jar rosie-1.0-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "target/rosie-1.0-jar-with-dependencies.jar"]