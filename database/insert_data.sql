-- insert_data.sql
INSERT INTO departments VALUES (1, 'HR', 'Delhi');
INSERT INTO departments VALUES (2, 'IT', 'Bangalore');c
INSERT INTO departments VALUES (3, 'Finance', 'Mumbai');

INSERT INTO jobs VALUES (1, 'Manager', 50000, 100000);
INSERT INTO jobs VALUES (2, 'Developer', 30000, 80000);
INSERT INTO jobs VALUES (3, 'Analyst', 25000, 70000);

INSERT INTO employees VALUES (101, 'Ravi', 'Kumar', 'ravi@xyz.com', SYSDATE-400, 2, 2, 45000);
INSERT INTO employees VALUES (102, 'Anita', 'Sharma', 'anita@xyz.com', SYSDATE-700, 1, 1, 95000);
INSERT INTO employees VALUES (103, 'Karan', 'Mehta', 'karan@xyz.com', SYSDATE-200, 3, 3, 50000);

COMMIT;
