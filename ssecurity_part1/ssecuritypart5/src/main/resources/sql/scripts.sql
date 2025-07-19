
-- db structure
create table `springsecurity_db`.`users`(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table `springsecurity_db`.`authorities`(username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on `springsecurity_db`.`authorities` (username,authority);


-- inserts queryies
INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('user', '{noop}User_12345@@', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('user', 'read');

INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('admin', '{noop}Admin_12345@@', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('admin', 'admin');