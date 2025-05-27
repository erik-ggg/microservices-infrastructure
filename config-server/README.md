# Config Server Microservice

## Overview
The Config Server is a centralized configuration service that provides configuration properties to all microservices in the system. It uses Spring Cloud Config Server to fetch configuration from a Git repository.

## Technologies Used
- Java 17
- Spring Boot
- Spring Cloud Config Server
- Maven

## Running Locally
```bash
cd config-server
mvn spring-boot:run
```

The server will start on port 8888.

## Running with Podman

### Building the Container Image
```bash
cd config-server
podman build -t config-server .
```

### Running the Container
```bash
podman run --network=host --name config-server \
  -e CONFIG_SERVER_GIT_URI=https://github.com/erik-ggg/micros-config-repo \
  config-server
```

### Configuration Options
You can customize the container using environment variables:
- `SERVER_PORT`: The port the server runs on (default: 8888)
- `CONFIG_SERVER_GIT_URI`: The Git repository URI (default: https://github.com/erik-ggg/micros-config-repo)
- `CONFIG_SERVER_GIT_LABEL`: The Git branch to use (default: main)

### Notes
- The Dockerfile uses fully qualified image names for Podman compatibility
- For Docker, replace `podman` with `docker` in the commands