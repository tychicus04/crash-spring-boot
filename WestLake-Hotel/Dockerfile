# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim


# Set the working directory in the container
RUN mkdir /app
WORKDIR /app

COPY target/WestLake-Hotel-0.0.1-SNAPSHOT.jar /app/

ENV spring.datasource.username=root
ENV spring.datasource.password=root
ENV spring.datasource.url=jdbc:mysql://mysql:3306/westLake_hotel_db
ENV spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
ENV spring.jpa.show-sql=true
ENV spring.jpa.hibernate.ddl-auto=update
ENV spring.jpa.hibernate.format_sql=true
ENV spring.servlet.multipart.max-file-size=5MB
ENV spring.servlet.multipart.max-request-size=5MB
ENV spring.servlet.multipart.file-size-threshold=2KB
ENV server.port=3000
ENV management.security.enabled=false


# EXPOSE 3000


CMD ["java", "-Duser.timezone=GMT+7", "-Djava.security.egd=file:/dev/./urandom", "-jar", "WestLake-Hotel-0.0.1-SNAPSHOT.jar"]


