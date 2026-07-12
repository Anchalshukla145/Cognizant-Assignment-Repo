CREATE DATABASE IF NOT EXISTS ormlearn;
USE ormlearn;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS skill;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS department (
  dp_id INT AUTO_INCREMENT PRIMARY KEY,
  dp_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
  em_id INT AUTO_INCREMENT PRIMARY KEY,
  em_name VARCHAR(50) NOT NULL,
  em_salary DOUBLE NOT NULL,
  em_permanent BOOLEAN NOT NULL,
  em_date_of_birth DATE NOT NULL,
  em_dp_id INT,
  FOREIGN KEY (em_dp_id) REFERENCES department(dp_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS skill (
  sk_id INT AUTO_INCREMENT PRIMARY KEY,
  sk_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_skill (
  es_em_id INT NOT NULL,
  es_sk_id INT NOT NULL,
  PRIMARY KEY (es_em_id, es_sk_id),
  FOREIGN KEY (es_em_id) REFERENCES employee(em_id) ON DELETE CASCADE,
  FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id) ON DELETE CASCADE
);

TRUNCATE TABLE employee_skill;
-- Temporarily disable foreign keys to truncate tables with relationships
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE employee;
TRUNCATE TABLE department;
TRUNCATE TABLE skill;
SET FOREIGN_KEY_CHECKS = 1;

-- Insert initial departments
INSERT INTO department (dp_id, dp_name) VALUES (1, 'IT');
INSERT INTO department (dp_id, dp_name) VALUES (2, 'HR');
INSERT INTO department (dp_id, dp_name) VALUES (3, 'Finance');

-- Insert initial employees
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) 
VALUES (1, 'John Doe', 50000.00, 1, '1995-10-12', 1);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) 
VALUES (2, 'Jane Smith', 60000.00, 0, '1998-05-15', 2);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) 
VALUES (3, 'Bob Johnson', 45000.00, 1, '1992-03-24', 1);

-- Insert initial skills
INSERT INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT INTO skill (sk_id, sk_name) VALUES (2, 'Spring Boot');
INSERT INTO skill (sk_id, sk_name) VALUES (3, 'MySQL');

-- Associate employee and skill
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 3);
