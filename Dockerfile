FROM maven:3.9.8 AS build
LABEL authors="seydina"
WORKDIR /app
ARG CONTAINER_PORT
COPY pom.xml /app
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:17
COPY  --from=build /app/target/*.jar app.jar
EXPOSE ${CONTAINER_PORT}
CMD ["java", "-jar", "app.jar" ]
