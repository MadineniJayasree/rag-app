FROM eclipse-temurin:21

WORKDIR /app

COPY target/rag-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "app.jar"]