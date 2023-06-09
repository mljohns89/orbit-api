FROM amazoncorretto:17

VOLUME /orbit-api-volume

COPY . .

RUN chmod +x ./gradlew && \
    ./gradlew build && \
    mv ./app/build/libs/app-all.jar app.jar && \
    rm -rf build && \
    chmod +x ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]