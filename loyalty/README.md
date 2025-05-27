# Loyalty Microservice

## Overview
The Loyalty Microservice is responsible for managing customer loyalty points and rewards. It consumes order events from Kafka and updates customer loyalty status accordingly.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Kafka
- Maven

## Running Locally
```bash
cd loyalty
mvn spring-boot:run
```

The service will start on port 8084.

## Running with Podman

### Building the Container Image
```bash
cd loyalty
podman build -t loyalty-service .
```

### Running the Container
```bash
podman run --network=host --name loyalty-service \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/loyalty \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  -e KAFKA_BOOTSTRAP_SERVERS=host.containers.internal:9092 \
  loyalty-service
```

### Database Connectivity
When running in a container, you can connect to your MySQL database in several ways:

1. **Using host network mode**:
   ```bash
   podman run --network=host --name loyalty-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/loyalty \
     -e KAFKA_BOOTSTRAP_SERVERS=localhost:9092 \
     loyalty-service
   ```

2. **Using your host's actual IP address**:
   ```bash
   podman run -p 8082:8082 --name loyalty-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://192.168.1.100:3306/loyalty \
     -e KAFKA_BOOTSTRAP_SERVERS=192.168.1.100:9092 \
     loyalty-service
   ```

### Configuration Options
You can customize the container using environment variables:
- `SERVER_PORT`: The port the service runs on (default: 8082)
- `SPRING_DATASOURCE_URL`: The JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME`: The database username
- `SPRING_DATASOURCE_PASSWORD`: The database password
- `KAFKA_BOOTSTRAP_SERVERS`: The Kafka bootstrap servers
- `KAFKA_CONSUMER_GROUP_ID`: The Kafka consumer group ID (default: loyalty-group)

### Notes
- The Dockerfile uses fully qualified image names for Podman compatibility
- For Docker, replace `podman` with `docker` in the commands