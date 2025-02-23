FROM maven:3-openjdk-17

WORKDIR /app

COPY pom.xml ./
COPY src ./

RUN mvn clean install
