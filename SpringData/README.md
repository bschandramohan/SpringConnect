
# Postgres docker download
### docker run -d --name postgres -p 5432:5432 -e POSTGRES_USER=pgadmin -e POSTGRES_PASSWORD=pgpwd postgres
(note password is required for connections, username is not mandatory)
### brew install --cask pgadmin4
After that create a new server connecting to localhost 5432 with user/pwd as above to see the data from this app

## Mongo docker download
### docker pull mongo
### docker run -d --name mongodb mongo
### docker run -d --link mongodb:mongo -p 8081:8081 --name mongo-express mongo-express
