FROM maven:3.9.8-eclipse-temurin-21 AS maven-builder
WORKDIR /app

COPY pom.xml /app
COPY src /app/src

RUN mvn clean install -U -DskipTests

FROM openjdk:21
WORKDIR /app

COPY --from=maven-builder /app/target/risk-management-user-service-*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/risk-management-policy-service-*.jar policy-service.jar

ENV JAVA_OPTS="-Xms128m -Xmx256m"
EXPOSE 8081

CMD ["sh", "-c", "java $JAVA_OPTS -jar policy-service.jar"]

FROM node:18-alpine as build

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install

COPY . ./

RUN npm run build

FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
