--Categoria
INSERT INTO CATEGORIES (id, name) values (1, 'mouse');
INSERT INTO CATEGORIES (id, name) values (2, 'gabinete');
INSERT INTO CATEGORIES (id, name) values (3, 'laptop');
INSERT INTO CATEGORIES (id, name) values (4, 'teclado');


--Productos
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (1, 'Mouse Dragon xyz', 10000, 'mouse1.jpg', 1);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (2, 'Mouse Dragon buble', 200000, 'mouse2.jpg', 1);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (3, 'Mouse Dragon ', 500000, 'mouse3.png', 1);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (4, 'Mouse Dragon xyz', 10000, 'mouse4.png', 1);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (5, 'Gabinete Luxiry', 200000, 'gabinete1.png', 2);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (6, 'Gabinete Luxiry 2', 500000, 'gabinete2.png', 2);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (7, 'Laptop Xiaomi', 10000, 'laptop.png', 3);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (8, 'Laptop', 200000, 'laptop2.jpg', 3);
INSERT INTO PRODUCTS (id, name, price, image, category_id) values (9, 'teclado led retro iluminado', 500000, 'teclado.jpg', 4);


--Facturas
INSERT INTO INVOICE (id) values (1);
INSERT INTO INVOICE (id) values (2);
INSERT INTO INVOICE (id) values (3);
INSERT INTO INVOICE (id) values (4);
INSERT INTO INVOICE (id) values (5);
INSERT INTO INVOICE (id) values (6);
INSERT INTO INVOICE (id) values (7);

--Detalles
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (1,1,1, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (2,2,2, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (3,2,6, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (4,3,9, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (6,3,5, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (7,5,7, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (8,6,5, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (9,6,9, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (10,6,3, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (11,7,9, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (12,4,8, 1 );
