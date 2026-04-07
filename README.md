🏥 Hospital Management System (HMS)
A production-ready RESTful Backend API for managing core hospital operations — Patients, Doctors, Appointments, and Medical Records. Built with Spring Boot 4.0.3, Spring Data JPA, and MySQL.

📌 Features

✅ Full CRUD operations for Patients, Doctors, Appointments, and Medical Records
✅ Duplicate email detection for Patients and Doctors (409 Conflict)
✅ Cross-entity validation — Appointments and Medical Records verify that Patient and Doctor IDs exist before saving
✅ Bean Validation on all request bodies (Jakarta Validation)
✅ Global Exception Handling with structured JSON error responses
✅ Filter appointments and medical records by Patient ID or Doctor ID
✅ Auto-creates MySQL database on first run (createDatabaseIfNotExist=true)
✅ Schema auto-managed via Hibernate (ddl-auto=update)


🛠️ Tech Stack
LayerTechnologyLanguageJava 17FrameworkSpring Boot 4.0.3WebSpring Web MVCPersistenceSpring Data JPA / HibernateDatabaseMySQL 8+ValidationJakarta Bean ValidationBoilerplateLombokBuild ToolMaven (with Maven Wrapper)Dev ToolsSpring Boot DevTools

⚙️ Configuration
Edit src/main/resources/application.properties:
propertiesspring.application.name=hms

spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

⚠️ Security Note: Never commit real credentials to GitHub. Use environment variables and add application.properties to .gitignore.


🚀 Getting Started
Prerequisites

Java 17+
MySQL 8+
Maven 3.8+ (or use the included ./mvnw wrapper)

Steps
bash# 1. Clone the repository
git clone https://github.com/your-username/hms.git
cd hms

# 2. Update application.properties with your MySQL credentials

# 3. Run the application
./mvnw spring-boot:run
The server starts at: http://localhost:8080
The database hospital_db will be auto-created if it doesn't already exist.

📡 API Reference
👤 Patients — /api/patients
MethodEndpointDescriptionPOST/api/patientsCreate a new patientGET/api/patientsGet all patientsGET/api/patients/{id}Get patient by IDPUT/api/patients/{id}Update patientDELETE/api/patients/{id}Delete patient
Request Body:
json{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@email.com",
  "phone": "9876543210",
  "age": 35,
  "gender": "Male",
  "address": "123 Main Street",
  "bloodGroup": "O+"
}

Email must be unique. Duplicate email returns 409 Conflict.


🩺 Doctors — /api/doctors
MethodEndpointDescriptionPOST/api/doctorsCreate a new doctorGET/api/doctorsGet all doctorsGET/api/doctors/{id}Get doctor by IDPUT/api/doctors/{id}Update doctorDELETE/api/doctors/{id}Delete doctor
Request Body:
json{
  "firstName": "Sarah",
  "lastName": "Smith",
  "email": "dr.sarah@hospital.com",
  "phone": "9123456789",
  "specialization": "Cardiology",
  "department": "Heart Care",
  "experienceYears": 10
}

Email must be unique. Duplicate doctor email returns 409 Conflict.


📅 Appointments — /api/appointments
MethodEndpointDescriptionPOST/api/appointmentsCreate a new appointmentGET/api/appointmentsGet all appointmentsGET/api/appointments/{id}Get appointment by IDGET/api/appointments/patient/{patientId}Get appointments by patientGET/api/appointments/doctor/{doctorId}Get appointments by doctorPUT/api/appointments/{id}Update appointmentDELETE/api/appointments/{id}Delete appointment
Request Body:
json{
  "patient": { "id": 1 },
  "doctor": { "id": 2 },
  "appointmentDate": "2026-04-15",
  "appointmentTime": "10:30:00",
  "reason": "General check-up",
  "status": "SCHEDULED"
}

Patient and Doctor IDs are validated to exist before saving. Default status is SCHEDULED.


🗂️ Medical Records — /api/medical-records
MethodEndpointDescriptionPOST/api/medical-recordsCreate a new medical recordGET/api/medical-recordsGet all medical recordsGET/api/medical-records/{id}Get record by IDGET/api/medical-records/patient/{patientId}Get records by patientGET/api/medical-records/doctor/{doctorId}Get records by doctorPUT/api/medical-records/{id}Update medical recordDELETE/api/medical-records/{id}Delete medical record
Request Body:
json{
  "patient": { "id": 1 },
  "doctor": { "id": 2 },
  "recordDate": "2026-04-15",
  "diagnosis": "Hypertension Stage 1",
  "prescription": "Amlodipine 5mg once daily",
  "notes": "Follow up in 4 weeks"
}

🛡️ Error Handling
All errors return structured JSON via a @RestControllerAdvice Global Exception Handler.
ScenarioHTTP StatusTriggerResource Not Found404Entity not found by IDDuplicate Resource409Duplicate email for Patient or DoctorValidation Failed400Missing or invalid fields in requestInternal Server Error500Unexpected server-side exception
404 Example:
json{
  "timestamp": "2026-04-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Patient not found with id: 5"
}
400 Validation Example:
json{
  "timestamp": "2026-04-15T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "fieldErrors": {
    "email": "Invalid email format",
    "firstName": "First name required"
  }
}
409 Conflict Example:
json{
  "timestamp": "2026-04-15T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Patient with email john.doe@email.com already exists"
}

🗄️ Database Schema
Tables are auto-generated by Hibernate on startup:
TableDescriptionpatientsPatient personal and contact informationdoctorsDoctor profile, specialization, departmentAppointmentsLinks patients & doctors with date/time/statusmedical_recordsDiagnosis, prescription, notes per visit
Entity Relationships:

Appointment → @ManyToOne Patient, @ManyToOne Doctor
MedicalRecord → @ManyToOne Patient, @ManyToOne Doctor


