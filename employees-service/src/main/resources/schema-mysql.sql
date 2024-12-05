USE `employees-db`;

drop table if exists department_positions;
drop table if exists departments;

drop table if exists employees;
drop table if exists employee_phonenumbers;

create table if not exists department_positions (
    department_id INTEGER,
    title VARCHAR(50),
    code VARCHAR(50)
    );

create table if not exists departments (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    department_id VARCHAR(36),
    name VARCHAR(50),
    head_count INTEGER
    );

create table if not exists employee_phonenumbers (
                                                     employee_id INTEGER,
                                                     type VARCHAR(50),
    number VARCHAR(50)
    );

create table if not exists employees (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(36),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    salary DECIMAL(19,4) NOT NULL,
    commission_rate DECIMAL(3,1),
    department_id VARCHAR(36)
    );
