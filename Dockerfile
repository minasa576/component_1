FROM openjdk:latest
WORKDIR /app
COPY src /app
RUN javac Compnent1.java
ENV READ_BATCH=/app/users_data \
    WRITE_BATCH=/app/data/batch/
VOLUME /app/data/batch
CMD ["java", "Component1"]