FROM openjdk:latest
WORKDIR /app
COPY src /app
RUN javac Compnent1.java
CMD ["java", "Component1"]