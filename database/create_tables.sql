-- create_tables.sql
CREATE TABLE departments (
                             dept_id NUMBER PRIMARY KEY,
                             dept_name VARCHAR2(50),
                             location VARCHAR2(50)
);

CREATE TABLE jobs (
                      job_id NUMBER PRIMARY KEY,
                      job_title VARCHAR2(50),
                      min_salary NUMBER,
                      max_salary NUMBER
);

CREATE TABLE employees (
                           emp_id NUMBER PRIMARY KEY,
                           first_name VARCHAR2(30),
                           last_name VARCHAR2(30),
                           email VARCHAR2(50),
                           hire_date DATE,
                           job_id NUMBER REFERENCES jobs(job_id),
                           dept_id NUMBER REFERENCES departments(dept_id),
                           salary NUMBER
);
