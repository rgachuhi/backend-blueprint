# backend-blueprint
A Java Maven template project to use as a starting point in standing up a new OLI Microservice backend project

This includes and sets up:
* Maven for build system
* JUnit for unit testing
* Arquillian for Integration Testing
* Dockerized development and testing 
* Automated Jenkins testing and distribution building

## Dependencies
Maven
Docker
Docker Compose (Linux).

## How to Use

First, clone this repository:

```
$ git clone https://github.com/Simon-Initiative/backend-blueprint
```

Then delete the local git repo.

```
$ rm -rf backend-blueprint/.git
```

Next, customize the `package.json`, adding your project name and description
and rename the directory to something project specific. 

Also customize the
file `docker-compose.yaml` to specify the exact container_name that you wish
to use.  That setting would typically just match the project name. You may 
also need to specify additional services (such as a back-end REST API provider), 
depending on what your application requires to run.

Next customize the travis build file and the travis build indicator link that 
is embedded in this README. 

Then, run the application in the containerized development mode. This will
build a container, install necessary packages and finally run the application
in development mode inside the container yet accessible to your localhost via
port mapping.

```
$ docker-compose up
```

Then open a browser window and
hit [http://localhost:9000/index.html](http://localhost:9000/index.html).

Finally, go change source code and implement your code.

## Updating dependencies

If you add or change npm dependencies, stop, rebuild and restart the container:

```
$ docker-compose down

$ docker-compose up --build
```

## Running unit tests

With the container running:

```
$ docker exec -it starter jest --watchAll
```

Where `starter` is whatever you left defined in the `container_name` field of the compose file.
