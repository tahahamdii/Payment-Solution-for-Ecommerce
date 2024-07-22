
# Payment Solution

This project sets up a microservices development environment using Docker Compose. It leverages several key services to provide a comprehensive and scalable architecture for application development.

# Prerequisites
Docker

Docker Compose
# Architecture
This project employs a microservices architecture to ensure scalability, maintainability, and flexibility. The architecture is designed to handle various services and their interactions seamlessly. Below is a high-level overview of the architecture:

# Components
API Gateway (API GW)


Serves as the entry point for all client requests.
Routes requests to appropriate services based on the endpoint.
Microservices

Customer Service: Manages customer data stored in MongoDB.
Product Service: Handles product information.
Order Service: Manages order processing and interacts with other services.
Payment Service: Processes payments and sends confirmations asynchronously.
Notification Service: Sends notifications using Kafka for message brokering.
Databases

PostgreSQL: Used for relational data storage.
MongoDB: Used for document-based storage, particularly for customer and notification data.
Message Broker

Kafka: Facilitates asynchronous communication between services, ensuring reliability and scalability.
Configuration and Discovery

Eureka Server: Handles service discovery.
Config Server: Manages centralized configuration for all services.
Monitoring and Tracing

Zipkin: Provides distributed tracing to monitor and troubleshoot the interactions between microservices.
Development Tools

pgAdmin: Web interface for managing PostgreSQL databases.
Mongo-Express: Web interface for MongoDB.
MailDev: Simulates an SMTP server for testing email functionality.
Keycloak: Manages authentication and authorization.
