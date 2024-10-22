FROM gradle:8.3-jdk17 AS build

WORKDIR /app

COPY build/libs/BirthdayBot-0.0.1-SNAPSHOT.jar /app/birthday.jar

EXPOSE 8037

ENTRYPOINT ["java", "-jar", "birthday.jar"]

