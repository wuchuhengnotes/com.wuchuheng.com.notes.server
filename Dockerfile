FROM openjdk:17-jdk-alpine AS build

WORKDIR /springBoot

COPY . /springBoot

RUN ./gradlew clean build -x test && cd build/libs && ls -ahl

FROM openjdk:17-jdk-alpine

VOLUME /tmp

WORKDIR /springBoot

ARG DEPENDENCY=/springBoot

COPY --from=build ${DEPENDENCY}/build/libs/server-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","server-0.0.1-SNAPSHOT.jar"]
