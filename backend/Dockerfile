FROM maven AS build
ARG rds_url
COPY . /usr/app/
WORKDIR /usr/app
RUN mvn clean install -Dspring.datasource.url=$rds_url -DskipTests
FROM java:8-jdk-alpine AS run
COPY --from=build /usr/app/target/HelpQueue-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","app.jar"]
