

use `ssecurity-db`;

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

INSERT INTO `ssecurity-db`.`users` (`username`, `password`, `enabled`) VALUES ('user','{noop}User-1@12345','1');
INSERT INTO `ssecurity-db`.`authorities` (`username`, `authority`) VALUES ('user','read');
INSERT INTO `ssecurity-db`.`users` (`username`, `password`, `enabled`) VALUES ('admin','{bcrypt}$2a$12$t5Jpc0mATH3FRoOWlsuQeOB3Rrf9T0UozKTRX1WOsjD9CRXczKhN2','1');
INSERT INTO `ssecurity-db`.`authorities` (`username`, `authority`) VALUES ('admin','admin');

DROP TABLE `ssecurity-db`.`users`;
DROP TABLE `ssecurity-db`.`authorities`;

CREATE TABLE `ssecurity-db`.`customer`(
`id` INT NOT NULL AUTO_INCREMENT,
`email` VARCHAR(45) NOT NULL,
`pwd` VARCHAR(200) NOT NULL,
`role` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`)
);