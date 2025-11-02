Project Title: Employee Management System
Database: Oracle SQL
Backend: Java (JDBC)
IDE Used: IntelliJ IDEA Ultimate

Description:
This project allows managing employees, jobs, and departments.
Users can add, update, delete, and view data through a menu-driven Java console interface.

Files:
- DBConnection.java → connects to Oracle Database
- DepartmentService.java, JobService.java, EmployeeService.java → handle database operations
- database/ → contains SQL scripts to create and populate tables

Database Setup Instructions:
1. Open Oracle SQL or IntelliJ Database Console.
2. Run the scripts inside the "database" folder in this order:
   a. create_tables.sql
   b. insert_data.sql
3. Update DBConnection.java with your Oracle username and password.
   Example:
   URL = "jdbc:oracle:thin:@localhost:1521:XE";
   USER = "system";
   PASS = "your_password";

How to Run:
1. Open the project in IntelliJ IDEA.
2. Run `create_tables.sql` and `insert_data.sql` in your Oracle SQL connection.
3. Update DBConnection.java with your Oracle username/password.
4. Run `MainMenu.java`.

Submitted by:
    1. S Rithika Saran (241001200)
    1. Sabnish Seetharaman (241001206)
    1. Sahana B (241001209)
Course: Database Management Systems , B.Tech IT - Semester 3

