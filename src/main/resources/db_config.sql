CREATE SCHEMA `critter` ; -- Create the critter database
create user 'sa_critter'@'localhost' identified by 'sac1234'; -- Create the user
grant all on critter.* to 'sa_critter'@'localhost'; -- Gives all privileges to that user on new db