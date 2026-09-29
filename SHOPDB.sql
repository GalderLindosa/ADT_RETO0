CREATE DATABASE SHOPDB;

USE SHOPDB;

CREATE TABLE PRODUCT(
ID INTEGER PRIMARY KEY,
NAME_P varchar(30),
PRICE DOUBLE,
CATEGORY ENUM('FOOTWEAR','TEXTILE','ACCESSORY'),
STOCK INT,
ROOT VARCHAR(30)
);

CREATE TABLE CLIENT_S(
ID INTEGER PRIMARY KEY,
NAME_C VARCHAR(30),
EMAIL VARCHAR(30),
PHONE CHAR(9),
ADDRESS VARCHAR(30)
);
INSERT INTO PRODUCT (ID, NAME_P, PRICE, CATEGORY, STOCK, ROOT)
VALUES
(1, 'Nike Air Max', 129.99, 'FOOTWEAR', 15, 'Warehouse A'),
(2, 'Adidas Hoodie', 59.90, 'TEXTILE', 30, 'Warehouse B'),
(3, 'Leather Belt', 24.50, 'ACCESSORY', 50, 'Warehouse A'),
(4, 'Puma Running Shoes', 89.99, 'FOOTWEAR', 20, 'Warehouse C'),
(5, 'Winter Scarf', 19.99, 'TEXTILE', 40, 'Warehouse B'),
(6, 'Sunglasses Ray-Ban', 149.00, 'ACCESSORY', 10, 'Warehouse A');

INSERT INTO CLIENT_S (ID, NAME_C, EMAIL, PHONE, ADDRESS)
VALUES
(1, 'Laura Gómez', 'laura.gomez@mail.com', '612345678', 'Bilbao'),
(2, 'Carlos Ruiz', 'carlos.ruiz@mail.com', '698765432', 'Barakaldo'),
(3, 'Ana Torres', 'ana.torres@mail.com', '634567890', 'Getxo'),
(4, 'Jon Etxeberria', 'jon.etxe@mail.com', '645789012', 'Santurtzi'),
(5, 'Marta López', 'marta.lopez@mail.com', '678901234', 'Leioa');
