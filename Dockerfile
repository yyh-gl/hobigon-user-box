FROM gradle:8.14.0-jdk17 AS builder
WORKDIR /workspace
COPY . /workspace
RUN gradle build

FROM openjdk:21
COPY --from=builder /workspace/build/libs/hobigon-user-box-0.0.2.jar /hobigon-kotlin-api-server.jar
ENTRYPOINT ["java", "-jar", "hobigon-kotlin-api-server.jar"]
EXPOSE 8080
