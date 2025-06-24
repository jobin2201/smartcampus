# 🎓 Smart Campus Management System 

## 📌 Sprint 1: Foundational Development

This is the foundational sprint for a Smart Campus Management System (SCMS) built using Core Java, JDBC, and SQL. The system supports basic operations like student registration, course management, timetable structuring, faculty assignment, and input/output handling.

## 🛠️ Features Implemented

- Student and faculty registration
- Course and timetable allocation
- Exception handling for validation
- Thread-based simulation of concurrent enrollments
- Input validation with utility classes
- Complete JDBC CRUD operations
  
## 🛠️ WorkFlow
![Alt text](images/3.png)

## 📁 Project Structure
```plaintext
src/
├── campus/
│   ├── Main.java
│   ├── Faculty.java
│   ├── Students.java
│   └── Management.java
├── exception/
│   ├── FacultynotfoundException.java
│   └── InvalidEmailFormatException.java
├── pojos/
│   ├── StudentPOJO.java
│   └── FacultyPOJO.java
├── validator/
│   └── InputValidator.java
└── module-info.java

📄 External Files
├── database.sql                   # Full database schema and sample data (DDL + DML)
├── Database Designer.sql          # Indexes and views for optimized design and access
├── Database Developer.sql         # SQL scripts for queries and operations

```

## 🗄️ Database Setup

1. **Create your MySQL database**:
   ```sql
   CREATE DATABASE smart_campus;
   USE smart_campus;

2. Import the schema:
Import the full schema and sample data using the provided database.sql file. This includes all tables like:
    - students : Stores personal and academic details of each student.
    - faculty : Contains information about faculty members and their roles.
    - courses : Defines course details, including department, credits, and assigned faculty.
    - timetable : Schedules classes with specific timings, rooms, and instructors.
    - enrollments : Tracks which students are enrolled in which courses.
    - notification : Stores alerts and messages sent to students with timestamps.
  
    The database.sql file contains all DDL (table creation) and DML (inserts) required for the project.

3. Configure DB credentials in your code (in Main.java or utility class):
    ```
    String url = "jdbc:mysql://localhost:3306/smart_campus";
    String user = "your_username";
    String password = "your_password";
## 🚀 How to Clone and Run the Project

Follow these steps to clone and run the Smart Campus Management System project in Eclipse:

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/jobin2201/smartcampus.git
```
### 2️⃣ Open in Eclipse
  - Open Eclipse IDE.
  - Go to File > Import.
  - Select Existing Projects into Workspace under General.
  - Browse to the cloned folder and select the project.
  - Click Finish.
### 3️⃣ Set Up Database
  - Ensure you have MySQL installed and running.
  - Create the required database and tables using the provided SQL schema.
  - Update the JDBC connection URL, username, and password in your code (if not already set via a config).
### 4️⃣ Build and Run
  - Right-click on Main.java.
  - Select Run As > Java Application.
