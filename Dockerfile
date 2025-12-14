# ---------- deps ----------
FROM eclipse-temurin:21-jdk-jammy AS deps
WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
COPY pom.xml pom.xml

RUN --mount=type=cache,target=/root/.m2 \
  ./mvnw dependency:go-offline -DskipTests


# ---------- build ----------
FROM deps AS build
WORKDIR /build

COPY src src/

RUN --mount=type=cache,target=/root/.m2 \
  ./mvnw package -DskipTests && \
  mv target/fitapp-1.0-SNAPSHOT.jar target/app.jar


# ---------- runtime ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /build/target/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
