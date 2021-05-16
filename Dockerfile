FROM openjdk:8
ADD target/pizza-shop-0.0.1-SNAPSHOT.jar pizza-shop-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "pizza-shop-0.0.1-SNAPSHOT.jar"]