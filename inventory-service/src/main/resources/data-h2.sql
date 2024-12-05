insert into inventories (inventory_id, type) values ('5e6f3151-3a68-4b2c-ba12-c49e2d60e81f', 'cloth storage');
INSERT INTO inventories (inventory_id, type) VALUES ('7a4e7c9f-1e18-4e3e-90f0-5ffae0ff8b14', 'bookshelf');
INSERT INTO inventories (inventory_id, type) VALUES ('3b0c90d7-f55b-41bb-a670-bf0c153f09b3', 'electronics');
INSERT INTO inventories (inventory_id, type) VALUES ('f153b723-8f68-41bc-9814-fb11e4dbbf14', 'kitchenware');
INSERT INTO inventories (inventory_id, type) VALUES ('e50b8d8f-9139-49fa-b31d-2cf1fa837229', 'office supplies');
INSERT INTO inventories (inventory_id, type) VALUES ('b46a81b9-6b4f-4426-b5a0-c55b61d76d9e', 'tools');
INSERT INTO inventories (inventory_id, type) VALUES ('4c6f3c80-2c8f-459f-8a1b-cf48952d3b8f', 'sporting goods');
INSERT INTO inventories (inventory_id, type) VALUES ('a23b31ff-2a3f-4311-baa3-68d28fb28d02', 'toys');
INSERT INTO inventories (inventory_id, type) VALUES ('d623b2e1-7d04-41d9-832e-1baf7d2f08a2', 'furniture');
INSERT INTO inventories (inventory_id, type) VALUES ('8e7a1530-c9ae-4674-8e85-199457cc5c62', 'clothing');

insert into products(product_id, inventory_id, size, model, image, status, brand, country)
values('product01', '5e6f3151-3a68-4b2c-ba12-c49e2d60e81f', 'S', 'female adult', 'imageURL', 'SALE_PENDING', 'Uniqlo', 'Canada');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product02', '7a4e7c9f-1e18-4e3e-90f0-5ffae0ff8b14', 'M', 'male adult', 'imageURL2', 'SALE_PENDING', 'H&M', 'USA');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product03', '3b0c90d7-f55b-41bb-a670-bf0c153f09b3', 'L', 'unisex', 'imageURL3', 'SALE_PENDING', 'Zara', 'Spain');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product04', 'f153b723-8f68-41bc-9814-fb11e4dbbf14', 'XL', 'kids', 'imageURL4', 'SALE_PENDING', 'Gap', 'Canada');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product05', 'e50b8d8f-9139-49fa-b31d-2cf1fa837229', 'M', 'female adult', 'imageURL5', 'SALE_PENDING', 'Forever 21', 'USA');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product06', 'b46a81b9-6b4f-4426-b5a0-c55b61d76d9e', 'S', 'male adult', 'imageURL6', 'SALE_PENDING', 'Nike', 'USA');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product07', '4c6f3c80-2c8f-459f-8a1b-cf48952d3b8f', 'L', 'kids', 'imageURL7', 'SALE_PENDING', 'Adidas', 'Germany');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product08', 'a23b31ff-2a3f-4311-baa3-68d28fb28d02', 'M', 'female adult', 'imageURL8', 'SALE_PENDING', 'Puma', 'Germany');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product09', 'd623b2e1-7d04-41d9-832e-1baf7d2f08a2', 'XL', 'male adult', 'imageURL9', 'SALE_PENDING', 'Calvin Klein', 'USA');
INSERT INTO products (product_id, inventory_id, size, model, image, status, brand, country)
VALUES ('product10', '8e7a1530-c9ae-4674-8e85-199457cc5c62', 'S', 'unisex', 'imageURL10', 'SALE_PENDING', 'Levis', 'USA');


insert into product_options(product_id, name, description, price) values(1, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(2, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(3, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(4, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(5, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(6, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(7, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(8, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(9, 'pink', 'pink color', 34000);
insert into product_options(product_id, name, description, price) values(10, 'pink', 'pink color', 34000);
