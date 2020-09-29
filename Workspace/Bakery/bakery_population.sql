# this query is like a reset, all old values will be removed and replaced with what you see here
USE bakery;
#insert 5 users of default values into user database 
#remove all old values
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE users; 
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (username, password, email, first_name, last_name)
VALUES ('user1', 'password', 'user1@email.com', 'Barney', 'Stinson');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES ('user2', 'password', 'user2@email.com', 'Ted', 'Mosby');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES ('user3', 'password', 'user3@email.com', 'Marshall', 'Erickson');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES ('user4', 'password', 'user4@email.com', 'Robin', 'Sherbatsky');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES ('user5', 'password', 'user5@email.com', 'Lily', 'Aldrin');

#----------------------------------------------#

/*populate the product database with assortment of random products
NOTE: when creating product table entries, "type" must be 'cookie' or 'cake' 
*/

#remove all values from products
TRUNCATE TABLE products;

INSERT INTO products (type, name, price, description)
VALUES ('cookie', 'Chocolate Chip', 5, 'These classic cookies are guranteed to be a home-run');

INSERT INTO products (type, name, price, description)
VALUES ('cookie', 'Apple Raisin', 5, 'You got apples, you got raisins');

INSERT INTO products (type, name, price, description)
VALUES ('cookie', 'Sugar Cookie', 5, 'You know what a sugar cookie is');

INSERT INTO products (type, name, price, description)
VALUES ('cake', 'Red Velvet', 20, 'Classic with cream cheese icing');

INSERT INTO products (type, name, price, description)
VALUES ('cake', 'Pound Cake', 20, 'A timeless classic');

INSERT INTO products (type, name, price, description)
VALUES ('cake', 'Chocolate', 20, 'What normal people like');

INSERT INTO products (type, name, price, description)
VALUES ('cake', 'Cookie Cake', 20, 'Everyone had it for their birthday at least once');

##insert shopping cart items##
TRUNCATE table shopping_cart;
#user 1 has sugar cookie & red velvet cake
INSERT INTO shopping_cart (user_id, type, name, price, description)
VALUES (1, 'cookie', 'Sugar Cookie', 5, 'You know what a sugar cookie is');

INSERT INTO shopping_cart (user_id, type, name, price, description)
VALUES (1, 'cake', 'Red Velvet', 20, 'Classic with cream cheese icing');

#check that the update worked
SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM shopping_cart;
