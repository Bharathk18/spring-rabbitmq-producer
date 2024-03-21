FROM openjdk:8
EXPOSE 8080
ADD target/spring-rabbitmq-producer.jar spring-rabbitmq-producer.jar
ENTRYPOINT ["java","-jar","/spring-rabbitmq-producer.jar"]