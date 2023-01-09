FROM openjdk:17-alpine

ADD target/appsick-0.1.2-SNAPSHOT.jar appsick.jar

ENTRYPOINT ["java", "-jar", "appsick.jar"]

EXPOSE 8080