FROM openjdk:8-alpine

COPY target/uberjar/monaco.jar /monaco/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/monaco/app.jar"]
