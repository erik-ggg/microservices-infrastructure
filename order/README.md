# Order Microservice

## Overview
The Order Microservice is responsible for managing customer orders. It communicates with the Warehouse Microservice to check item availability and processes orders.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Cloud Config Client
- Maven

## Running Locally
```bash
cd order
mvn spring-boot:run
```

The service will start on port 8082.

## Running with Podman

### Building the Container Image
```bash
cd order
podman build -t order-service .
```

### Running the Container
```bash
podman run --network=host --name order-service \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/order \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  -e CONFIG_SERVER_URI=http://localhost:8888 \
  order-service
```

### Database Connectivity
When running in a container, you can connect to your MySQL database in several ways:

1. **Using host network mode**:
   ```bash
   podman run --network=host --name order-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/order \
     -e CONFIG_SERVER_URI=http://localhost:8888 \
     order-service
   ```

2. **Using your host's actual IP address**:
   ```bash
   podman run -p 8082:8082 --name order-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://192.168.1.100:3306/order \
     -e CONFIG_SERVER_URI=http://192.168.1.100:8888 \
     order-service
   ```

### Configuration Options
You can customize the container using environment variables:
- `SERVER_PORT`: The port the service runs on (default: 8082)
- `SPRING_DATASOURCE_URL`: The JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME`: The database username
- `SPRING_DATASOURCE_PASSWORD`: The database password
- `CONFIG_SERVER_URI`: The URI of the Config Server
- `CONFIG_SERVER_USERNAME`: The Config Server username
- `CONFIG_SERVER_PASSWORD`: The Config Server password
- `SPRING_PROFILES_ACTIVE`: The active Spring profile (default: dev)

### Notes
- The Dockerfile uses fully qualified image names for Podman compatibility
- For Docker, replace `podman` with `docker` in the commands