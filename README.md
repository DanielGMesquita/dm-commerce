# DM Commerce

## Overview

DM Commerce is a modern e-commerce backend application built with Spring Boot. It provides a robust API for managing
products, orders, categories, and user authentication.

## Features

- User authentication and authorization with OAuth2
- Product management
- Category management
- Order processing
- Role-based access control
- Secure API endpoints

## Domain Model (UML)

The domain model of DM Commerce is represented in the following UML diagram:
![UML Domain Model](img.png)

## Technologies

- Java 17
- Spring Boot 3
- Spring Security with OAuth2
- JPA/Hibernate
- Maven
- H2 Database (test)
- PostgreSQL (dev/prod)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL (for development/production)

### Setup

1. Clone the repository

```bash
git clone https://github.com/yourusername/dmcommerce.git
cd dmcommerce
```

2. Configure the database

- Update `application-dev.properties` with your PostgreSQL credentials

3. Build the project

```bash
./mvnw clean install
```

4. Run the application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

- Authentication endpoints: `/oauth2/token`
- Products: `/products`
- Categories: `/categories`
- Orders: `/orders`
- Users: `/users`

## Testing

Run the automated tests:

```bash
./mvnw test
```

## Security

The application uses OAuth2 for authentication and authorization with the following features:

- Token-based authentication
- Role-based access control
- Secure endpoint protection
