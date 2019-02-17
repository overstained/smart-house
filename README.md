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

In the root folder of the project, execute:

```
docker-compose up
```
