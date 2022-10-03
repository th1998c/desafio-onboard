FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} payment-scheduler-1.0.0.jar
ENTRYPOINT ["java","-Xmx512m","-jar","/payment-scheduler-1.0.0.jar"]