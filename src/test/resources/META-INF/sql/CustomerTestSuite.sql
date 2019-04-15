-- any sql that is to be pre-loaded before testsuite runs goes in here
-- statements must be a single line without returns
INSERT INTO public.contact(city, email, phone, postalcode, province, street, version, created_date, updated_date)
VALUES('Ottawa', '16131234567', 'a@b.c', 'K2K2K2', 'ON', '1 Main str.', 0, TIMESTAMP '2019-04-09 07:15:31.123456789', TIMESTAMP '2019-04-09 07:15:31.123456789');