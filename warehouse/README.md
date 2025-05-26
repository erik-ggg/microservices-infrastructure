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
    url: jdbc:mysql://localhost:3306/warehouse
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database: mysql
    hibernate:
      ddl-auto: update
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```

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