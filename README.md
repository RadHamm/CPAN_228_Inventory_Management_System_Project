NorthPoint's Inventory Management System

Group Name: NorthPoint

Group Members:
1. Arwa Abdirahman
2. Zainab Aamir
3. Kerlan Augustine
4. Steven Hamm

Project Description:
This project is an Inventory Management System built using Spring Boot, Spring Security, Thymeleaf, and MySQL, with full Docker containerization, utilizing role-based permissions.

The system allows users to view and manage inventory items with different permission levels based on their assigned roles.
ADMIN -> Full control (create, edit, delete items, access admin dashboard)
STAFF -> Can add and edit inventory items
USER -> Read-only access to inventory

The application demonstrates full-stack development, authentication/authorization, and deployment via Docker.

Technologies Used:
Java 17+
Spring Boot, Security, Data JPA
Thymeleaf
Maven
MySQL
H2 Database
Docker & Docker Compose

System Architecture:
The application is structured using a layered archiecture:
Controller Layer -> to handle HTTP requests
Service Layer -> Logic
Repository Layer -> Database Access (JPA)
View Layer -> Thymeleaf Templates

Security Features:
Spring Security is implemented using a SecurityFilterChain

Role-Based Access Control

Authentication:
Custom login page
BCrypt password encryption
Role-based login redirect

Application Features
- User registration and login
- Role-based dashboard access
- Inventory CRUD operations
- Pagination for inventory list
- Search itesm by name
- Filter items by brand
- Sort items dynamically
- Admin management dashboard

Default Users:
On first run, the system automatically creates:
Username    Password    Role
admin       admin123    ADMIN
staff       staff123    STAFF
user        user123     USER

Docker Deployment:
The application runs using Docker Compose, which starts both:
Spring Boot application container
MySQL database container

How To Run The Project:
1. Clone the repository
git clone <repository-url>
cd <project-folder>
 
2. Start the application using Docker
docker compose up --build

3. Access the application
open in browser:
http://localhost:8080


Database Configuration:
QA Environment (Docker MySQL)
- Database: inventorydb
- Host: db
- Port: 3306
- Username: inventoryuser
- Password: inventorypassword

Configured via:
application-qa.yml
docker-compose.yml

Development Environment (H2):
- In-memory database
- Automatically created at runtime
- H2 console enabled at: /h2-console
Configured via: application-dev.ylm

Environment Profiles:
The application supports multiple environments:
Profile     Database        Purpose
dev         H2              Local development
qa          MySQL (Docker)  Production-like testing

Docker uses: SPRING_PROFILES_ACTIVE=qa

Docker Services:
App Container:
- Runs Spring Boot Application
- Uses port 8080

Database Container:
- MySQL 8.0
- Exposes port 3306
- Persistent volume for data storage