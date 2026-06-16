FROM eclipse-temurin:25-jdk-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD mvnw .
ADD .mvn .mvn
ADD . $HOME
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:25-jre-alpine
COPY --from=build /usr/app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]
