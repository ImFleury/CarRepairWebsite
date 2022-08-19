BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number) VALUES ('user1','user1','ROLE_USER', 'User1', 'User1', 'user1@test.com', '555-5555');
INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number) VALUES ('user2','user2','ROLE_USER', 'User2', 'User2', 'user2@test.com', '555-5555');
INSERT INTO users (username,password_hash,role, first_name, last_name, email_address, phone_number) VALUES ('user3','user3','ROLE_USER', 'User3', 'User3', 'user3@test.com', '555-5555');

COMMIT TRANSACTION;
