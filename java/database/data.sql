BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number)
    VALUES ('jim','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'Jim', 'Williams', 'jim@techmail.com', '555-5555');
INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number)
    VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin', 'User', 'admin@techelevator.com', '555-5555');
INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number)
    VALUES ('julie','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_EMPLOYEE', 'Julie', 'Jackson', 'employee@techelevator.com', '555-5555');
INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number)
    VALUES ('hank','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'Hank', 'Howard', 'hank@techmail.com', '555-5555');

INSERT INTO vehicles(customer_id, make, model, year, color) VALUES (2000, 'Honda', 'Accord', 2012, 'Blue');
INSERT INTO vehicles(customer_id, make, model, year, color) VALUES (2000, 'Ford', 'F150', 2018, 'Black');
INSERT INTO vehicles(customer_id, make, model, year, color) VALUES (2003, 'Toyota', 'Hilux', 1990, 'Tan');


INSERT INTO requests (customer_id, vehicle_id, description) VALUES (2000, 1, 'Oil Change');
INSERT INTO requests (customer_id, vehicle_id, description) VALUES (2000, 1, 'Wheels shaking');
INSERT INTO requests (customer_id, vehicle_id, description) VALUES (2000, 2, 'Shattered windshield');
INSERT INTO requests (customer_id, vehicle_id, description) VALUES (2003, 3, 'Truck will not start');


INSERT INTO work_orders (request_id, all_completed, time_completed, approved, paid) VALUES (1000, true, '2022-08-08 01:01:00', true, true);
INSERT INTO work_orders (request_id, all_completed, time_completed, approved, paid) VALUES (1001, false, null, false, false);
INSERT INTO work_orders (request_id, all_completed, time_completed, approved, paid) VALUES (1002, false, null, false, false);
INSERT INTO work_orders (request_id, all_completed, time_completed, approved, paid) VALUES (1003, false, null, false, false);


INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (1000, 'Oil Change', 20.00, 50.00, true);
INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (1001, 'Tire Rotation', 20.00, 50.00, false);
INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (1001, 'Replace Left Front Tire', 200.00, 100.00, false);
INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (1002, 'Windshield Replacement', 150.00, 100.00, false);
INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (1003, 'Alternator', 200.00, 100.00, false);


COMMIT TRANSACTION;
