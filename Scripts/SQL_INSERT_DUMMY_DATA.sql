INSERT INTO public.category (name, version, created_date, updated_date) VALUES('clothes', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.category (name, version, created_date, updated_date) VALUES('toys', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.category (name, version, created_date, updated_date) VALUES('tools', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');

INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('shirt', 10.00, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('doll', 3.00, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('teddy', 1.50, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');

INSERT INTO public.contact(city, email, phone, postalcode, province, street, version, created_date, updated_date)
VALUES('Ottawa', '16131234567', 'a@b.c', 'K2K2K2', 'ON', '1 Main str.', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.contact(city, email, phone, postalcode, province, street, version, created_date, updated_date)
VALUES('Ottawa', '16137654921', 'vb@m.co', 'A1A1A1', 'ON', '2 King str.', 0, TIMESTAMP '2019-04-10 07:15:31.123456789', TIMESTAMP '2019-04-10 07:15:31.123456789');
INSERT INTO public.contact(city, email, phone, postalcode, province, street, version, created_date, updated_date)
VALUES('Montreal', '18197894561', 'nb@bb.vv', 'B2B2B2', 'QC', '3 Queen str.', 0, TIMESTAMP '2019-04-11 07:15:31.123456789', TIMESTAMP '2019-04-11 07:15:31.123456789');

INSERT INTO public.platform_user (PWHASH,USERNAME,VERSION,CREATED_DATE,UPDATED_DATE) VALUES (
'PBKDF2WithHmacSHA256:2048:mwK6dUg0Y4vF14f0ClwSYZL/hVOhS/6TASlFgR9fT14=:JMzkoD4991qKGGlv0MUkvCdkFxETp5lTus0pUhZFJiM=','CRUDTEST1',1,'2019-04-13 18:47:34.777','2019-04-13 18:47:34.777');
INSERT INTO public.platform_user (PWHASH,USERNAME,VERSION,CREATED_DATE,UPDATED_DATE) VALUES (
'PBKDF2WithHmacSHA256:2048:Oim2LLI+EhRW2sHBX49a7Y9vZLCH2mbtSU2sxCOlLqQ=:+O+lojkYsS1cdhi4ZYpe00Tm2bJE0qXDQ5o7pXvVacI=','CRUDTEST2',1,'2019-04-13 18:47:38.031','2019-04-13 18:47:38.031');
INSERT INTO public.platform_user (PWHASH,USERNAME,VERSION,CREATED_DATE,UPDATED_DATE) VALUES (
'PBKDF2WithHmacSHA256:2048:82ZUC9N9Ba9Ja5s/20en4xAq3JJnRCDSj9GsETapBzw=:0cCAlBfDOE9atWa55f3o41zuFjCXuCL6hRl/f1T9A3s=','CRUDTEST3',1,'2019-04-13 18:47:40.614','2019-04-13 18:47:40.614');
INSERT INTO public.platform_user (PWHASH,USERNAME,VERSION,CREATED_DATE,UPDATED_DATE) VALUES (
'PBKDF2WithHmacSHA256:2048:CgDuDW3ObepIdUDZfCKEpd1VjLd0EowN41MeHHESnd0=:qjYbYKs1Slrvi1wteicvjQTiwzFIwpNnL2BsNEDZcbY=','CRUDTEST4',1,'2019-04-13 18:47:42.727','2019-04-13 18:47:42.727');


INSERT INTO public.customer (FIRSTNAME,LASTNAME,VERSION,CREATED_DATE,UPDATED_DATE,CONTACT_ID,USER_ID) VALUES (
'CRUD','TEST1',2,'2019-04-13 18:47:34.777','2019-04-13 18:48:13.049',1,2);
INSERT INTO public.customer (FIRSTNAME,LASTNAME,VERSION,CREATED_DATE,UPDATED_DATE,CONTACT_ID,USER_ID) VALUES (
'CRUD','TEST2',2,'2019-04-13 18:47:38.038','2019-04-13 18:48:22.858',2,3);
INSERT INTO public.customer (FIRSTNAME,LASTNAME,VERSION,CREATED_DATE,UPDATED_DATE,CONTACT_ID,USER_ID) VALUES (
'CRUD','TEST3',2,'2019-04-13 18:47:40.620','2019-04-13 18:48:26.982',3,4);
INSERT INTO public.customer (FIRSTNAME,LASTNAME,VERSION,CREATED_DATE,UPDATED_DATE,CONTACT_ID,USER_ID) VALUES (
'CRUD','TEST4',1,'2019-04-13 18:47:42.733','2019-04-13 18:47:42.733',NULL,5);


INSERT INTO public.platform_user_role (ROLE_ID,USER_ID) VALUES (
2,2);
INSERT INTO public.platform_user_role (ROLE_ID,USER_ID) VALUES (
2,3);
INSERT INTO public.platform_user_role (ROLE_ID,USER_ID) VALUES (
2,4);
INSERT INTO public.platform_user_role (ROLE_ID,USER_ID) VALUES (
2,5);

INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(1, 1);
INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(2, 2);
INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(2, 3);

INSERT INTO public.cart (version, created_date, updated_date, owning_cust_id) VALUES(0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 1);
INSERT INTO public.cart (version, created_date, updated_date, owning_cust_id) VALUES(0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 2);
INSERT INTO public.cart (version, created_date, updated_date, owning_cust_id) VALUES(0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 3);

INSERT INTO public.choice (quantity, version, created_date, updated_date, cart_id, product_id) VALUES(3, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 1, 1);
INSERT INTO public.choice (quantity, version, created_date, updated_date, cart_id, product_id) VALUES(100, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 2, 2);
INSERT INTO public.choice (quantity, version, created_date, updated_date, cart_id, product_id) VALUES(20, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789', 3, 3);



