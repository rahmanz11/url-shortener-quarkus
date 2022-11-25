# Project

## Run everything at once
You can run redpanda, elasticsearch and the java app all along by running the docker-compose.yml file from the project parent directory:
```shell script
docker-compose up
```
Do make everything down:
```shell script
docker-compose down
```
## Test in postman
    Create short url Request:
    POST http://localhost:8080/urls
    {
        "longForm": "http://www.z.com"
    }
    Create short url Response:
    {
        "shortForm": "http://tiny.me/jVqoud2Xt",
        "longForm": "http://www.z.com"
    }

    Get the full url request:
    GET http://localhost:8080/urls/jVqoud2Xt
    Response:
    {
        "shortForm": "jVqoud2Xt",
        "longForm": "http://www.z.com"
    }

    Get all from database:
    GET http://localhost:8080/urls
    {
        "shortForm": "http://tiny.me/jVqoud2Xt",
        "longForm": "http://www.z.com"
    }