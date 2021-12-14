FROM openjdk:11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

COPY . .
RUN ./gradlew clean build

FROM openjdk:11-ea-9-jre-slim
ENV ARTIFACT_NAME=rabbitmq-subscribed-0.1.0-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod ${ARTIFACT_NAME}