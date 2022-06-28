FROM openjdk:11

LABEL maintainer="ecemnurnaz@gmail.com"

ENV MONGO_INITDB_ROOT_USERNAME=admin \ MONGO_INITDB_ROOT_PASSWORD=password

COPY target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]


