FROM openjdk:17
EXPOSE 8080
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
