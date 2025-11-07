FROM eclipse-temurin:21-jdk

WORKDIR /app

# Копіюємо зібраний jar-файл (збери його локально через mvn package)
COPY target/Notes-0.0.1-SNAPSHOT.jar app.jar

# Запускаємо
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
