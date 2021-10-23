# jumia-app
Prerequisites:
Requires Maven and JDK to be installed on the host machine

Run following commands to build and run the docker image inside the jumia-app directory:
```console
mvn clean package
docker build --tag=jumia-app:latest .
docker run -d -p 8080:8080 jumia-app:latest
```
There is a sample sqllite databse include in the project by name `sample.db`

Future plans:
- Use migrations to create tables.
- Add features like add/delete/update phone Numbers & countires.
- Add Search in table.
- Encrypt app.properties file.