FROM openjdk
WORKDIR /app
COPY src /app
RUN javac main.java
CMD ["java", "main"]