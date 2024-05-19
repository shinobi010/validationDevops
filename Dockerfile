#FROM openjdk:8-jre-slim
FROM openjdk:17-slim
WORKDIR /app
RUN apt-get update && apt-get install -y curl && curl -u admin:nexus -O http://192.168.56.2:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar
RUN apt-get remove -y curl && apt-get clean
EXPOSE 8082
CMD ["java", "-jar", "achat-1.0.jar"]
