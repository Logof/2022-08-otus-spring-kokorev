FROM maven:3.8.6-openjdk-11-slim as build

ENV PROJECT_DIR=/opt/homework

RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve

ADD ./src/ $PROJECT_DIR/src
RUN mvn -B clean install

#собираем
FROM openjdk:11-jre-slim

ENV PROJECT_DIR=/opt/homework
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=build $PROJECT_DIR/target/application.jar $PROJECT_DIR/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/homework/application.jar"]