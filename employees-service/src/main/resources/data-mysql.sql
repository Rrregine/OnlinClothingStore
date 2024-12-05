INSERT INTO departments (department_id, name, head_count) VALUES('a5913a79-9b1e-4516-9ffd-06578e7af261', 'Regine', '1');
INSERT INTO departments (department_id, name, head_count) VALUES ('b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84', 'Marketing', 5);
INSERT INTO departments (department_id, name, head_count) VALUES ('c49b1e4b-d41c-4b59-b6c3-2dca68d0322e', 'Finance', 8);
INSERT INTO departments (department_id, name, head_count) VALUES ('d8f6a577-03e5-463b-aa04-2d7a5a5af645', 'Human Resources', 4);
INSERT INTO departments (department_id, name, head_count) VALUES ('e592854f-8372-4d1c-b758-d488ef8e2d0f', 'IT', 10);
INSERT INTO departments (department_id, name, head_count) VALUES ('f1a52c24-6638-44cb-b362-6faa1df36877', 'Operations', 7);
INSERT INTO departments (department_id, name, head_count) VALUES ('g23445e8-1e3e-4b3b-bb3e-848891fa7f0a', 'Sales', 6);
INSERT INTO departments (department_id, name, head_count) VALUES ('h96d9f43-349d-4f53-a4d8-2f4b996a9cb0', 'Customer Service', 3);
INSERT INTO departments (department_id, name, head_count) VALUES ('i0c1d28b-e9ee-47b2-9ef4-72115e5a0e1d', 'Research and Development', 12);

INSERT INTO department_positions(department_id, title, code) VALUES  ('1', 'manager', 'hrm1');
INSERT INTO department_positions (department_id, title, code) VALUES ('2', 'Marketing Specialist', 'mktgsp1');
INSERT INTO department_positions (department_id, title, code) VALUES ('3', 'Financial Analyst', 'finanl1');
INSERT INTO department_positions (department_id, title, code) VALUES ('4', 'HR Coordinator', 'hrcoor1');
INSERT INTO department_positions (department_id, title, code) VALUES ('5', 'IT Technician', 'ittecht1');
INSERT INTO department_positions (department_id, title, code) VALUES ('6', 'Operations Manager', 'opmgr1');
INSERT INTO department_positions (department_id, title, code) VALUES ('7', 'Sales Representative', 'salerep1');
INSERT INTO department_positions (department_id, title, code) VALUES ('8', 'Customer Service Associate', 'csassoc1');
INSERT INTO department_positions (department_id, title, code) VALUES ('9', 'Research Scientist', 'ressci1');
INSERT INTO department_positions (department_id, title, code) VALUES ('10', 'Quality Assurance Engineer', 'qaeng1');

insert into employees(employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
values('e5913a79-9b1e-4516-9ffd-06578e7af261', 'Vilma', 'Chawner', 'vchawner0@phoca.cz', 34000, 0.0, 'a5913a79-9b1e-4516-9ffd-06578e7af261');
insert into employees(employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
values('e5913a79-9b1e-4516-9ffd-06578e7af262', 'Vilma2', 'Chawner2', 'vchawner0@phoca.cz', 34000, 0.0, 'b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('e7b7d14e-7d13-4f48-9b0c-2f01cf6b187e', 'John', 'Doe', 'johndoe@example.com', 50000, 0.1, 'b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('d8c0bfa8-2d17-4e1e-87bb-ebefad3e8043', 'Jane', 'Smith', 'janesmith@example.com', 45000, 0.08, 'b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('f9d1c8e3-0c2e-4d15-afab-3fd5360e9e5d', 'Michael', 'Johnson', 'michaeljohnson@example.com', 48000, 0.05, 'b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('b7c8cf3a-cf7a-42e1-a548-bf96fd45cb4d', 'Emily', 'Brown', 'emilybrown@example.com', 42000, 0.06, 'b7c827b8-c8c2-4e9f-bb19-1eb3b91fbf84');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('a5fc7b05-8a2f-43d4-93c1-bc0df7a5745c', 'Robert', 'Williams', 'robertwilliams@example.com', 55000, 0.12, 'c49b1e4b-d41c-4b59-b6c3-2dca68d0322e');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('f1c4ef4d-21ed-4e3b-80a0-7336604b5029', 'Amanda', 'Taylor', 'amandataylor@example.com', 40000, 0.04, 'c49b1e4b-d41c-4b59-b6c3-2dca68d0322e');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('f1c4ef4d-21ed-4e3b-80a0-7336604b5029', 'Amanda', 'Taylor', 'amandataylor@example.com', 40000, 0.04, 'c49b1e4b-d41c-4b59-b6c3-2dca68d0322e');
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, commission_rate, department_id)
VALUES ('e2f9d89e-4d7f-4a52-a1a9-3fd3f9806f65', 'Christopher', 'Martinez', 'christophermartinez@example.com', 47000, 0.07, 'c49b1e4b-d41c-4b59-b6c3-2dca68d0322e');

insert into employee_phonenumbers(employee_id, type, number) values('1', 'WORK', '515-555-5555');
insert into employee_phonenumbers(employee_id, type, number) values('2', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('3', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('4', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('5', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('6', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('7', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('8', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('9', 'MOBILE', '514-555-4444');
insert into employee_phonenumbers(employee_id, type, number) values('10', 'MOBILE', '514-555-4444');
