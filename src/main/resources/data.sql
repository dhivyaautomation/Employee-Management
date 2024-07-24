-- Insert Departments
INSERT INTO DEPARTMENT (name) VALUES ('HR');
INSERT INTO DEPARTMENT (name) VALUES ('Engineering');

-- Insert Employees
INSERT INTO EMPLOYEE (name, department_id) VALUES ('John', 1);
INSERT INTO EMPLOYEE (name, department_id) VALUES ('Elon', 2);

-- Insert Employee Skills
INSERT INTO EMPLOYEE_SKILLS (employee_id, skill) VALUES (1, 'Java');
INSERT INTO EMPLOYEE_SKILLS (employee_id, skill) VALUES (1, 'Spring Boot');
INSERT INTO EMPLOYEE_SKILLS (employee_id, skill) VALUES (2, 'Python');
INSERT INTO EMPLOYEE_SKILLS (employee_id, skill) VALUES (2, 'C#');
