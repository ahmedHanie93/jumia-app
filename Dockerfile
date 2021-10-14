# we will use openjdk 8 with alpine as it is a very small linux distro
FROM openjdk:8-jre-alpine3.9

# copy the packaged jar file into our docker image
COPY target/jumia-app-0.0.1-SNAPSHOT.war jumia-app.war

# set the startup command to execute the war
CMD ["java", "-jar", "/jumia-app.war"]