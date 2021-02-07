Spring Boot application to connect to different types of database using Spring-Data. Here we connect to postgres (RDBMS)
and Mongo (NoSQL)

Refer to SpringBootConsole and SpringBootWeb Modules to know about the spring boot configurations (in that order)

We will get into reactive spring boot applications in: SpringBootReactive and reactive spring boot data in the next
module:  SpringDataReactive

# Postgres docker download

Reference: https://hub.docker.com/_/postgres

1. docker run -d --name postgres -p 5432:5432 -e POSTGRES_USER=pgadmin -e POSTGRES_PASSWORD=pgpwd postgres
   (NOTE: password is required for connections, username is not mandatory)
1. brew install --cask pgadmin4 After that create a new server connecting to localhost 5432 with user/pwd as above to
   see the data from this app

## Mongo docker download

1. docker pull mongo
1. docker run -d --name mongodb -p 27017:27017 mongo
1. docker run -d --link mongodb:mongo -p 8081:8081 --name mongo-express mongo-express
