FROM gradle:7.6.4-jdk17 AS builder
WORKDIR /workspace
COPY . /workspace
RUN gradle build

FROM openjdk:17.0.2
COPY --from=builder /workspace/build/libs/hobigon-user-box-0.0.2.jar /hobigon-kotlin-api-server.jar
ENTRYPOINT ["java", "-jar", "hobigon-kotlin-api-server.jar"]
EXPOSE 8080
