# User Microservice

## Overview
The User Microservice manages user accounts and profiles. It communicates with the Order Microservice to retrieve order information for users.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Running Locally
```bash
cd user
mvn spring-boot:run
```

The service will start on port 8083.

## Running with Podman

### Building the Container Image
```bash
cd user
podman build -t user-service .
```

### Running the Container
```bash
podman run --network=host --name user-service \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/user \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  user-service
```

### Database Connectivity
When running in a container, you can connect to your MySQL database in several ways:

1. **Using host network mode**:
   ```bash
   podman run --network=host --name user-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/user \
     user-service
   ```

2. **Using your host's actual IP address**:
   ```bash
   podman run -p 8083:8083 --name user-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://192.168.1.100:3306/user \
     user-service
   ```

### Configuration Options
You can customize the container using environment variables:
- `SERVER_PORT`: The port the service runs on (default: 8083)
- `SPRING_DATASOURCE_URL`: The JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME`: The database username
- `SPRING_DATASOURCE_PASSWORD`: The database password

### Notes
- The Dockerfile uses fully qualified image names for Podman compatibility
- For Docker, replace `podman` with `docker` in the commands