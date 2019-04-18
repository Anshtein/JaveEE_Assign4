INSERT INTO public.category (name, version, created_date, updated_date) VALUES('clothes', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.category (name, version, created_date, updated_date) VALUES('toys', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.category (name, version, created_date, updated_date) VALUES('tools', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');

INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('shirt', 10.00, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('doll', 3.00, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');
INSERT INTO public.product (name, price, version, created_date, updated_date) VALUES('teddy', 1.50, 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');

INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(1, 1);
INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(2, 2);
INSERT INTO public.category_prod
(category_id, prod_id)
VALUES(2, 3);
