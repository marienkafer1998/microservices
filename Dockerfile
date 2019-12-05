FROM gradle:jdk11 as builder
COPY . /usr/src/warehouse/.
WORKDIR /usr/src/warehouse
RUN gradle build --no-daemon -x test

FROM openjdk:11
COPY --from=builder /usr/src/warehouse/build/libs/*.jar /usr/src/warehouse.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/usr/src/warehouse.jar"]

