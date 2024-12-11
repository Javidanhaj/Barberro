# Barbershop Project Structure

## 1. Application Structure Overview

- **Controller Layer**  
  Handles incoming HTTP requests and maps them to service methods.
- **Service Layer**  
  Contains business logic for each feature.
- **Repository Layer**  
  Interacts with the database using JPA or similar.
- **Model Layer**  
  Defines data structures and database entities.
- **DTO Layer**  
  Contains objects for transferring data between client and backend.
- **Config Layer**  
  Contains configuration classes for setting up application properties like security, database connections, and other Spring Boot-specific configurations.

## 2. Entity Design (Models) and Relationships

- **Customer**  
  - Fields: `id`, `name`, `contactInfo`, `preferences` (e.g., main hairstyle, common order).  
  - Relationships: One-to-Many with `Appointment`.

- **Barber**  
  - Fields: `id`, `name`, `schedule`.  
  - Relationships: One-to-Many with `Appointment`.

- **Appointment**  
  - Fields: `id`, `customerId`, `barberId`, `date`, `timeSlot`, `status` (pending, accepted, declined).  
  - Relationships: Many-to-One with `Customer` and `Barber`.

## 3. API Endpoints

**Customer Endpoints**  
- `GET /customers`: Get all customers.
- `POST /customers`: Add a new customer.
- `GET /customers/{id}`: Get a specific customer.

**Barber Endpoints**  
- `GET /barbers`: Get all barbers.
- `POST /barbers`: Add a new barber.

**Appointment Endpoints**  
- `GET /appointments`: Get all appointments.
- `POST /appointments`: Add a new appointment.
- `PUT /appointments/{id}`: Update an appointment (accept/decline).

## 4. Dual Apps Structure (Web App + JavaFX Desktop App)

- ### Backend (Spring Boot)
  - This layer will be responsible for managing business logic and connecting to the database. It will expose REST APIs that both the web and desktop apps can consume.

---

- ### Frontend for Web App
  - Build a responsive web interface (using HTML, CSS, JavaScript). The web app will call the backend APIs to retrieve and display data (appointments, customer profiles, etc.).

---

- ### Desktop App with JavaFX
  - Use JavaFX to create a GUI for managing appointments, customers, and profiles.
  - The JavaFX app will interact with the backend via REST APIs (using HttpClient or libraries like Retrofit to call API endpoints).
  - The JavaFX app can:
    - Display a list of available appointments and schedules.
    - Handle customer profiles and barber information.
    - Update or manage appointments.

## 5. Additional Steps for JavaFX Integration

1. ### Create a Separate Module for JavaFX
    - You can create a separate module for the JavaFX desktop app, or add it as a new Maven project. Both apps will consume the same backend REST API.

---

2. ### Connect JavaFX to Backend
    - Use HttpClient or Retrofit to make HTTP requests to the Spring Boot backend. Here's an example for fetching customer data:

    ```java
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/customers"))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    ```
3. ### JavaFX UI Design
   - Use Scene Builder for drag-and-drop UI design or write FXML manually.
   - Link the UI components (buttons, tables, forms) with controller classes to handle user interactions and backend data fetching.
4. ### Testing Both Apps
    - Make sure both the web and JavaFX apps interact with the backend in the same way. Test APIs, handle errors, and ensure smooth communication.

## 6. Spring Boot Specific Folder Structure
This is how you can structure your Spring Boot project for maintainability:
```
src/
 ├── main/
 │    ├── java/
 │    │    ├── com.progressapp.barbershop
 │    │    │    ├── controller/         # Handles HTTP requests
 │    │    │    ├── service/            # Contains business logic
 │    │    │    ├── repository/         # Repository layer for data access
 │    │    │    ├── model/              # Entity classes (Customer, Barber, etc.)
 │    │    │    ├── dto/                # Data Transfer Objects (CustomerDTO, AppointmentDTO)
 │    │    │    ├── config/             # Configuration classes (Database, Security)
 │    │    │    └── BarbershopApplication.java  # Main Spring Boot application class
 │    └── resources/
 │         ├── application.properties   # Spring Boot config file (e.g., DB connection)
 └── test/
      └── java/
           ├── com.progressapp.barbershop
                └── BarbershopApplicationTests.java  # Test cases for your app
```