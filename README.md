# Smart House Device Manager

<p>This system exposes a rest API to a cleaning device - can be extended to multiple devices. Given instructions interpretable by the device and an environment in which it runs and understands, the result of the tasks ran will be centralized in a database and exposed to the caller. This API also exposes an endpoint, giving access to the historical task execution of the device</p>

# Requirements to build and run
## Install Maven

<p>For installation on Linux or Mac OS X, please follow this guide: https://www.baeldung.com/install-maven-on-windows-linux-mac </p>

## Install Docker
<p>For Linux, follow this link: https://runnable.com/docker/install-docker-on-linux </p>
<p>For Linux, you will also have to install docker-compose: https://docs.docker.com/compose/install/
<p>For Mac, follow: https://docs.docker.com/docker-for-mac/install/ </p>

## Build the project with maven
<p>After cloning the git repository, cd to the root project folder and execute the following command:</p>

```
mvn install
```

<p>or</p>

```
mvn install -Dmaven.test.skip=true
```

<p>if you want to skip test execution phase.</p>

## Running the project

<p>In the root folder of the project, execute:</p>

```
docker-compose up
```

<p>This will build and start the services: springboot and springboot-mongo which will run each a single container:</p>
* springboot exposes port 8081 for the web application
* springboot-mongo exposes port 27018 for MongoDB

# Technologies used

<p>This project exposes a rest API built using Spring Boot 2 and documented using Springfox Swagger 2 (after starting the server you can access at
http://localhost:8081/swagger-ui.html the swagger interface which can also be used for hitting the endpoints).</p>
<p>For the data access layer, I employ the ODM Spring Data Mongo and for the data persistence, MongoDB 3.8.
To improve clarity especially at the domain models and rest models level, I'm using Lombok.</p>

# Structure of the project

<p>I've applied a commonly used layered architecture in Spring - the Controller/Service/Repository stack.</p>
<p>I have sepparated the integration tests from the unit tests: unit tests are located in src/test, integration tests are located in src/it.</p>

# Problems encountered during development

<p>Apparently javax.validation @Size(min,max) used for validating array length does not take into account the min size.
When I use double annotation @Size(min) @Size(max) it works as expected but in case of error, the field error messages will contain
[max, Integer.maxValue] or [0, max] values.
In order to overcome this, I've implemented a custom validator with custom defined messages. An improvement there would be
to centralize the messages in a properties file - or a resource bundle for i18n.</p>

<p>Integration tests - I chose to use OffsetDateTime to represent database dates as they always represent the same instances in time
and have stable ordering. I had to defined custom converters for OffsetDateTime-Date and Date-OffsetDateTime. For the main application they worked. For the testing environment, I used an embedded MongoDB with embededmongo-spring which provides a factory bean for the embedded mongo. I could not get the MongoTemplate to register the converters properly. The configuration can be found at src/it/java../smarthousemanager/TestConfig.java.</p>

<p>Swagger - swagger builds its json objects from the models using reflection. I have not found any way of customizing this. I was not
happy with 






