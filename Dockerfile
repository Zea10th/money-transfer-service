FROM openjdk:17-alpine
EXPOSE 9000
ADD target/money-transfer-app-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]