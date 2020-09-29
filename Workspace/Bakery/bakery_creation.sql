#if need to delete
#DROP TABLE users;
CREATE TABLE IF NOT EXISTS users (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR (20),
last_name VARCHAR (20),
username VARCHAR (20) NOT NULL,
password VARCHAR (50) NOT NULL,
email VARCHAR (50),
PRIMARY KEY (id)
);

#if need to delete
#DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (
id INT NOT NULL AUTO_INCREMENT,
type enum ('cookie', 'cake') NOT NULL,
name VARCHAR (30),
price DOUBLE,
description VARCHAR (300),
PRIMARY KEY (id)
);

#if need to delete
#DROP TABLE shopping_cart;
CREATE TABLE IF NOT EXISTS shopping_cart (
id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
type enum ('cookie', 'cake') NOT NULL,
name VARCHAR (30),
price DOUBLE,
description VARCHAR (300),
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES users(id)
);