FROM maven AS build
COPY . /usr/app/
WORKDIR /usr/app
RUN mvn clean install -DskipTests
FROM java:8-jdk-alpine AS run
COPY --from=build /usr/app/target/HelpQueue-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 9001
ENTRYPOINT ["java","-jar","app.jar"]
