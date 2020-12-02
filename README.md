# Spring Boot on Docker connecting to MySQL Docker container

1. Use MySQL Image published by Docker Hub (https://hub.docker.com/_/mysql/)
Command to run the mysql container
`docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:5.6`

2. In the Spring Boot Application, use the same container name of the mysql instance in the application.properties
`spring.datasource.url = jdbc:mysql://mysql-standalone:3306/test`

3. Create a `Dockerfile` for creating a docker image from the Spring Boot Application
`FROM openjdk:8
ADD target/users-mysql.jar users-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "users-mysql.jar"]`

4. Using the Dockerfile create the Docker image.
From the directory of Dockerfile - `docker build . -t users-mysql`

5. Run the Docker image (users-mysql) created in #4.
`docker build . -t users-mysql`

## Useful Docker commands
- `docker images`
- `docker container ls`
- `docker logs <container_name>`
- `docker container rm <container_name`
- `docker image rm <image_name`


-----------------------------------------------------------------------------------------------------------------------------------


https://www.youtube.com/watch?v=FlSup_eelYE     -Create docker image
https://www.youtube.com/watch?v=fvEWoy1xOvo		-Establish connection between two docker containers

#Steps to establish connection

# MYSQL Database
Pull mysql images     			: docker pull mysql
Create mysql container(Run image)
for 5.6 image					: docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:5.6

## Spring boot application

Create image 					: docker build . -t users-mysql
Create spring-boot container 
and link with mysql container	: docker run -p 8086:8086 --name users-mysql --link mysql-standalone:mysql -d users-mysql
       
# Test api on chrome 
Check running containers 
on terminals			        : docker container ps
Command to test conn establish   
(Hit command on chrome)         : http://localhost:8086/all/create 

# Check entry on mysql
docker exec -it <mysql id> bin/bash

- mysql -u root -p
- show databases;
- use test;
- show tables;
- select * from users;
- TRUNCATE TABLE users;

# Update image
docker stop users-mysql
docker container rm <>
docker image rm <Container id>
docker build . -t users-mysql
