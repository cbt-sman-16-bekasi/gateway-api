# Gunakan image Maven dengan JDK 21 sebagai base build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory di dalam container
WORKDIR /app

# Copy file pom.xml dan download dependencies dulu (agar cache efektif)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy semua source code ke dalam container
COPY src ./src

# Build aplikasi menggunakan Maven
RUN mvn clean package -DskipTests

# Gunakan image OpenJDK 21 untuk menjalankan aplikasi
FROM eclipse-temurin:21-jdk-alpine

# Set working directory di dalam container
WORKDIR /app

# Copy hasil build dari tahap sebelumnya ke dalam container
COPY --from=build /app/target/*.jar app.jar

# Expose port untuk API Gateway
EXPOSE 8080

# Jalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]