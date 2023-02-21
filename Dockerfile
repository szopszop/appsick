FROM openjdk:17-alpine

ADD target/appsick-0.2.1-SNAPSHOT.jar appsick.jar

ENTRYPOINT ["java", "-jar", "appsick.jar"]

EXPOSE 8080