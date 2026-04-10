🏥 Hospital Management System (HMS)

A production-ready RESTful backend API to manage hospital operations like Patients, Doctors, Appointments, and Medical Records.
Built using Spring Boot + MySQL.

🚀 Key Features

✅ CRUD for Patients, Doctors, Appointments, Medical Records

✅ Duplicate email validation (409 Conflict)

✅ Cross-entity validation (Patient & Doctor existence)

✅ Global exception handling (structured JSON responses)

✅ Filter data by Patient ID / Doctor ID

✅ Auto database creation & schema management

🛠️ Tech Stack

Backend: Java 17, Spring Boot, Spring Web, Spring Data JPA

Database: MySQL

Validation: Jakarta Validation

Tools: Lombok, Maven

⚙️ Configuration



Update application.properties:


spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db

spring.datasource.username=your_username

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

server.port=8080

⚠️ Never commit real credentials to GitHub

▶️ Run the Project
git clone <repo-url>
cd hms
./mvnw spring-boot:run

Server: 👉 http://localhost:8080

📡 API Overview

👤 Patients

/api/patients → CRUD operations

🩺 Doctors

/api/doctors → CRUD operations

📅 Appointments

/api/appointments → Manage bookings

🗂️ Medical Records

/api/medical-records → Diagnosis & prescriptions

🛡️ Error Handling

400 → Validation errors

404 → Resource not found

409 → Duplicate data

500 → Server error

🗄️ Database

Auto-created using Hibernate

Main tables:

Patients

Doctors

Appointments

Medical Records

📌 Highlights

Clean layered architecture

Production-ready API design

Optimized validations & relationships

🤝 Contribution


Feel free to fork and contribute 🚀
