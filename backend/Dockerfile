FROM maven AS build
COPY . /usr/app/
WORKDIR /usr/app
ARG rds_url
ENV endpoint = ${rds_url}
RUN mvn clean install -Dspring.datasource.url=${endpoint} -DskipTests
FROM java:8-jdk-alpine AS run
COPY --from=build /usr/app/target/HelpQueue-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","app.jar"]
