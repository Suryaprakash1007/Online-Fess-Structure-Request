FROM maven:3.8.5-openjdk-17 As build
WORKDIR /app
COPY . .
RUN wvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/fees-0.0.1-SNAPSHAOT.jar fees.jar
EXPOSE 8081
ENTRYPOINT ["java",".jar","fees.jar"]