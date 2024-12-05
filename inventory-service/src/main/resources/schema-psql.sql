drop table if exists inventories;

drop table if exists products;
drop table if exists product_options;


create table if not exists inventories(
    id SERIAL,
    inventory_id VARCHAR(36),
    type VARCHAR(50),
    PRIMARY KEY(id)
);

create table if not exists products(
    id SERIAL,
    product_id VARCHAR(36),
    inventory_id VARCHAR(36),
    size VARCHAR(5),
    model VARCHAR(36),
    image VARCHAR(255),
    status VARCHAR(20),
    brand VARCHAR(20),
    country VARCHAR(20),
    PRIMARY KEY(id)
);

create table if not exists product_options(
    product_id INTEGER,
    name VARCHAR(36),
    description VARCHAR(255),
    price DECIMAL(19,4)
);
