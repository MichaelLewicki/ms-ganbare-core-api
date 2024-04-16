# Use a GraalVM base image for building the native image
FROM ghcr.io/graalvm/graalvm-ce:22.3.2 as builder

# Set the working directory
WORKDIR /app

# Copy the Gradle project files
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/
COPY src/ src/

# Build the native image with GraalVM
RUN ./gradlew clean nativeCompile

# Use a small base image for the runtime
FROM gcr.io/distroless/java17-debian11
WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/build/native/nativeCompile/application ./application

# Expose the port that your Spring Boot application is listening on
EXPOSE 8080

# Run the native application
CMD ["./application"]