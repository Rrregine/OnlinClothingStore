INSERT INTO departments(department_id, name, head_count) VALUES('a5913a79-9b1e-4516-9ffd-06578e7af261', 'Regine', '1');

INSERT INTO department_positions(department_id, title, code) VALUES('1', 'manager', 'hrm1');

insert into employees(employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
values('e5913a79-9b1e-4516-9ffd-06578e7af261', 'Vilma', 'Chawner', 'vchawner0@phoca.cz', 34000, 0.0, '1');

insert into employees(employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
values('e5913a79-9b1e-4516-9ffd-06578e7af262', 'Vilma2', 'Chawner2', 'vchawner0@phoca.cz', 34000, 0.0, '1');

insert into employee_phonenumbers(employee_id, type, number) values('1', 'WORK', '515-555-5555');

insert into employee_phonenumbers(employee_id, type, number) values('1', 'MOBILE', '514-555-4444');
