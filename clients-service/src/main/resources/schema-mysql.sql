USE `clients-db`;

drop table if exists clients;
drop table if exists client_phonenumbers;

CREATE TABLE if not exists clients (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       client_id VARCHAR(36) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    street_address VARCHAR(255),
    city VARCHAR(255),
    province VARCHAR(255),
    country VARCHAR(255),
    postal_code VARCHAR(20)
    );

create table if not exists client_phonenumbers (
                                                   client_id INTEGER,
                                                   type VARCHAR(50),
    number VARCHAR(50)
    );