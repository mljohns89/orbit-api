FROM amazoncorretto:17
#FROM registry.access.redhat.com/ubi8/openjdk-17

VOLUME /orbit-api-volume

ARG JAR_FILE="./app/build/libs/app-all.jar"
COPY ${JAR_FILE} "/app.jar"
#COPY ${JAR_FILE} "/home/jboss/app.jar"


EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]

#CMD ["java", "-jar", "/home/jboss/app.jar"]