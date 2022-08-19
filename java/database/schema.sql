BEGIN TRANSACTION;

DROP TABLE IF EXISTS repair_items, work_orders, requests, vehicles, users;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 2000
  NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	email_address varchar(50) NOT NULL,
	phone_number varchar(12) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE vehicles (
    vehicle_id SERIAL,
    customer_id int NOT NULL,
    make varchar(100) NOT NULL,
    model varchar(100) NOT NULL,
    year int NOT NULL,
    color varchar(50) NOT NULL,
    CONSTRAINT PK_vehicle_vehicle_id PRIMARY KEY (vehicle_id),
    CONSTRAINT FK_vehicle_customer_id FOREIGN KEY (customer_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_request_id
  INCREMENT BY 1
  START WITH 1000
  NO MAXVALUE;

CREATE TABLE requests (
    request_id int NOT NULL DEFAULT nextval('seq_request_id'),
    customer_id int NOT NULL,
    vehicle_id int NOT NULL,
    description varchar(150) NOT NULL,
    CONSTRAINT PK_request_request_id PRIMARY KEY (request_id),
    CONSTRAINT FK_request_customer_id FOREIGN KEY (customer_id) REFERENCES users(user_id),
    CONSTRAINT FK_request_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);
CREATE SEQUENCE seq_work_orders_id
  INCREMENT BY 1
  START WITH 1000
  NO MAXVALUE;

CREATE TABLE work_orders (
    work_order_id  int NOT NULL DEFAULT nextval('seq_work_orders_id'),
    request_id int NOT NULL,
    all_completed boolean NOT NULL,
    time_completed timestamp NULL,
    paid boolean NOT NULL,
    approved boolean NOT NULL,
    CONSTRAINT PK_work_order_work_order_id PRIMARY KEY (work_order_id),
    CONSTRAINT FK_work_orders_request_id FOREIGN KEY (request_id) REFERENCES requests(request_id)
);

CREATE TABLE repair_items (
    repair_id SERIAL,
    work_order_id int NOT NULL,
    repair_name varchar(150) NOT NULL,
    parts_cost decimal(13, 2) NOT NULL,
    labor_cost decimal(13, 2) NOT NULL,
    completed boolean NOT NULL,
    CONSTRAINT PK_repair_item_repair_id PRIMARY KEY (repair_id),
    CONSTRAINT FK_repair_item_work_order_id FOREIGN KEY (work_order_id) REFERENCES work_orders(work_order_id)
);

COMMIT TRANSACTION;
