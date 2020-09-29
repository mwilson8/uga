DROP DATABASE IF EXISTS cs4370_bakery;
CREATE DATABASE cs4370_bakery;
USE cs4370_bakery;

DROP TABLE IF EXISTS Bakeries;
CREATE TABLE Bakeries(
	id int NOT NULL,
    name VARCHAR (50),
	address VARCHAR (50),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Products;
CREATE TABLE Products(
	id int NOT NULL,
    type VARCHAR(30),
    name VARCHAR (100),
    price FLOAT,
	description VARCHAR (300),
	img_src VARCHAR (300) DEFAULT 'default_img',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Menus;
CREATE TABLE Menus(
	bakery_id int,
    product_id int,
	FOREIGN KEY (bakery_id) REFERENCES Bakeries(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
	id int NOT NULL AUTO_INCREMENT,
    username VARCHAR (20),
    password VARCHAR (20),
    first_name VARCHAR (20),
    last_name VARCHAR (20),
    email VARCHAR (30),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS User_Allergy;
CREATE TABLE User_Allergy(
	user_id int,
    ingredient_name VARCHAR(20) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES Users(id)
);

DROP TABLE IF EXISTS Product_Allergy;
CREATE TABLE Product_Allergy(
	product_id int,
    ingredient_name VARCHAR(20) NOT NULL,
	FOREIGN KEY (product_id) REFERENCES Products(id)
);

DROP TABLE IF EXISTS Shopping_Carts;
CREATE TABLE Shopping_Carts(
	id int NOT NULL,
    user_id int,
    product_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

