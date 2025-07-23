
-- db structure
create table `springsecurity_db`.`users`(username varchar(50) not null primary key,password varchar(1000) not null,enabled boolean not null);
create table `springsecurity_db`.`authorities`(username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on `springsecurity_db`.`authorities` (username,authority);


-- inserts queryies
INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('user', '{noop}User_12345@@', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('user', 'read');

INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('admin', '{bcrypt}$2a$12$UrZPFAis5fQB0fnX16Mm5OM76lXX2IjXTXX/4ZxlKiydgH1JJk6yK', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('admin', 'admin');


create table `springsecurity_db`.`customer`(
    id int not null AUTO_INCREMENT primary key,
    email varchar(45) not null,
    pwd varchar(1000) not null,
    role varchar(45) not null
);

INSERT INTO `springsecurity_db`.`customer` 
    (email, pwd, role) 
    VALUES('user1@gmail.com', '{noop}User_12345@@', 'read');
    
INSERT INTO `springsecurity_db`.`customer` 
    (email, pwd, role) 
    VALUES('admin1@gmail.com', '{bcrypt}$2a$12$UrZPFAis5fQB0fnX16Mm5OM76lXX2IjXTXX/4ZxlKiydgH1JJk6yK', 'admin');
