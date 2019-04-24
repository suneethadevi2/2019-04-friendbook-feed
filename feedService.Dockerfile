FROM maven:3.6.1-jdk-8-alpine AS buildstage
WORKDIR /feedservice
COPY . .
RUN mvn package

FROM openjdk:8-jre-alpine3.9
WORKDIR /feedserviceapp
COPY --from=buildstage /feedservice/target/Feeds-0.0.1-SNAPSHOT.jar .
CMD java -jar Feeds-0.0.1-SNAPSHOT.jar