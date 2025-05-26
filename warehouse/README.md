# Warehouse Microservice

## Overview
The Warehouse Microservice is part of a microservices-based e-commerce application. It is responsible for managing inventory items, including their details and pricing information. This microservice provides a RESTful API for creating, retrieving, and listing items in the warehouse.

## Architecture
The Warehouse Microservice follows a hexagonal architecture (ports and adapters) pattern:

- **Domain Layer**: Contains the core business entities (Item, Warehouse)
- **Application Layer**: Contains use cases and input/output ports
- **Framework Layer**: Contains adapters for interacting with external systems
- **Infrastructure Layer**: Contains configuration and persistence implementations

## Technologies Used
- **Java 17**: Programming language
- **Spring Boot 2.7.15**: Application framework
- **Spring Data JPA**: Data access layer
- **MySQL**: Database for storing item information
- **Swagger/Springfox**: API documentation
- **Kafka**: Messaging system (configured but not actively used in current implementation)
- **Maven**: Build and dependency management
- **Lombok**: Reduces boilerplate code

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- MySQL 5.7 or higher
- Kafka (if using messaging features)

## Setup and Installation

### Database Setup
1. Create a MySQL database named `warehouse`:
   ```sql
   CREATE DATABASE warehouse;
   ```
2. Configure the database credentials in `application.yml` if different from the defaults.

### Building the Application
```bash
cd warehouse
mvn clean install
```

### Running the Application
```bash
mvn spring-boot:run
```

The application will start on port 8081 by default.

### Running with Podman/Docker
The warehouse microservice includes a Dockerfile that can be used to run the application in a container using Podman or Docker.

#### About the Dockerfile
The Dockerfile uses a multi-stage build approach with a JDK image for building and a smaller JRE image for running the application. This creates a smaller, more efficient container and prevents common issues like permission errors.

#### Building the Container Image
```bash
cd warehouse
podman build -t warehouse-service .
```

#### Running the Container
```bash
podman run --network=host --name warehouse-service \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/warehouse \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  warehouse-service
```

#### Database Connectivity from Container

When running in a container, you can connect to your MySQL database in several ways:

1. **Using host.containers.internal** (as shown in the example above)

2. **Using host network mode**:
   ```bash
   podman run --network=host --name warehouse-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/warehouse \
     warehouse-service
   ```

3. **Using your host's actual IP address** (most reliable):
   ```bash
   podman run -p 8081:8081 --name warehouse-service \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://192.168.1.100:3306/warehouse \
     warehouse-service
   ```

4. **Using a pod with MySQL container**:
   ```bash
   podman pod create --name warehouse-pod -p 8081:8081 -p 3306:3306
   podman run --pod warehouse-pod --name mysql-db -e MYSQL_DATABASE=warehouse -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0
   podman run --pod warehouse-pod --name warehouse-service -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/warehouse warehouse-service
   ```

#### Troubleshooting Connection Issues

If you encounter connection issues:
- Check that MySQL is running and accessible
- Verify network connectivity with `ping` or `nc` commands
- Try a different connection approach from above
- Check firewall settings and MySQL configuration

Note: 
- Adjust database parameters as needed for your environment
- For Docker, replace `podman` with `docker` in the commands
- The Dockerfile uses fully qualified image names for Podman compatibility

## API Documentation
Once the application is running, you can access the Swagger UI at:
```
http://localhost:8081/swagger-ui/
```

### Available Endpoints

#### Add a New Item
- **URL**: `/api/item`
- **Method**: POST
- **Parameters**:
  - `title` (string): The title of the item
  - `description` (string): The description of the item
  - `price` (decimal): The price of the item
- **Response**: The created item with its ID

#### Get Item by ID
- **URL**: `/api/item/{itemId}`
- **Method**: GET
- **Path Parameters**:
  - `itemId` (long): The ID of the item to retrieve
- **Response**: The item with the specified ID

#### Get All Items
- **URL**: `/api/item/all`
- **Method**: GET
- **Response**: A list of all items in the warehouse

## Configuration
The application configuration is in `src/main/resources/application.yml`:

```yaml
server:
  port: 8081
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/warehouse}
    username: ${SPRING_DATASOURCE_USERNAME:root}
    password: ${SPRING_DATASOURCE_PASSWORD:root}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database: mysql
    hibernate:
      ddl-auto: update
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```

The configuration uses environment variables with default values, making it easy to run the application in different environments without code changes.

## Integration with Other Microservices
The Warehouse Microservice is used by the Order Microservice to retrieve item information when processing orders. The Order service calls the Warehouse service's API to get item details by ID.

## Data Model
### Item
- `id` (Long): Unique identifier for the item
- `title` (String): The title/name of the item
- `description` (String): A description of the item
- `price` (BigDecimal): The price of the item

### Warehouse
- `id` (UUID): Unique identifier for the warehouse
- `content` (List<Item>): List of items in the warehouse

## Testing
To run the tests:
```bash
mvn test
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License
This project is licensed under the terms of the license included in the repository.
