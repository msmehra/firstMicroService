FROM openjdk:8-jdk-alpine
COPY ./media-app.war app.war
ENTRYPOINT ["java","-jar","/app.war"]