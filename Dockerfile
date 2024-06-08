FROM openjdk:8-jdk-alpine

EXPOSE 8079

ARG admin
ARG sonatype

RUN apk add --no-cache curl

RUN curl -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} -o achat-1.0.jar http://192.168.56.2:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar

ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
