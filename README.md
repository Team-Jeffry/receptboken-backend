# receptboken-backend
För att köra en MongoDB container lokalt med Docker: <br>
```
docker run --rm -p "27017:27017" -e MONGO_INITDB_ROOT_PASSWORD=password -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_DATABASE=recipe-db -d mongo:latest
```

