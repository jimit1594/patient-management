#builder
FROM maven:3.9.9-eclipse-temurin-21 AS builder

#working directory
WORKDIR /app

#copying pom.xml into working directory /app
COPY pom.xml .

#adding the dependency and source code into docker image
#dependency-mentioned in pom.xml. Caching it with go-offline for faster startup
RUN mvn dependency:go-offline -B

COPY src ./src

#creating a docker package
RUN mvn clean package

FROM openjdk:17-jdk AS runner

WORKDIR /app

#Builder will create the snapshot which will be copied as app.jar
COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

#Expose on port 4000
EXPOSE 4000

#command to run our app
ENTRYPOINT ["java", "-jar", "app.jar"]