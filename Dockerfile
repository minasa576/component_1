FROM openjdk
WORKDIR /app
COPY src /app
RUN javac component1.java
CMD ["java", "main"]