
INSERT INTO PRODUCTS (id, name, price) values (1, "Mouse Noganet", 10000);
INSERT INTO PRODUCTS (id, name, price) values (2, "Monitor", 200000);
INSERT INTO PRODUCTS (id, name, price) values (3, "CPU AMD", 500000);


INSERT INTO INVOICE (id) values (1);
INSERT INTO INVOICE (id) values (2);



INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (1,1,1, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (2,1,2, 2 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (3,1,3, 1 );

INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (1,2,1, 1 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (2,2,2, 3 );
INSERT INTO INVOICE_DETAILS (id, INVOICE_ID , PRODUCT_ID, QUANTITY) values (3,2,3, 1 );
