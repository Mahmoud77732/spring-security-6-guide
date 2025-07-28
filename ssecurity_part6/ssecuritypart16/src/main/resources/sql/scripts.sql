
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

-- Drop existing tables if they exist
DROP TABLE springsecurity_db.authorities;
DROP TABLE springsecurity_db.users;
DROP TABLE springsecurity_db.customer;

CREATE TABLE springsecurity_db.`customer` (
  `customer_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(1000) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
);

INSERT INTO `springsecurity_db`.`customer` 
(`email`, `pwd`, `name`, `mobile_number`, `create_at`, `role`) 
VALUES 
('user1@gmail.com', '{bcrypt}$2a$12$UrZPFAis5fQB0fnX16Mm5OM76lXX2IjXTXX/4ZxlKiydgH1JJk6yK', 'user1', '01001775021', CURDATE(), 'admin');

CREATE TABLE springsecurity_db.`accounts` (
  `customer_id` BIGINT NOT NULL,
  `account_number` BIGINT NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO springsecurity_db.`accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_at`)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURDATE());

CREATE TABLE springsecurity_db.`account_transactions` (
  `transaction_id` varchar(200) NOT NULL,
  `account_number` BIGINT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `transaction_dt` date NOT NULL,
  `transaction_summary` varchar(200) NOT NULL,
  `transaction_type` varchar(100) NOT NULL,
  `transaction_amt` int NOT NULL,
  `closing_balance` int NOT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `customer_id` (`customer_id`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
  CONSTRAINT `acct_user_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

-- Insert sample transactions
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`, `transaction_amt`, `closing_balance`, `create_at`)
VALUES 
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 7 DAY, 'Coffee Shop', 'Withdrawal', 30, 34500, CURDATE() - INTERVAL 7 DAY),
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 6 DAY, 'Uber', 'Withdrawal', 100, 34400, CURDATE() - INTERVAL 6 DAY),
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 5 DAY, 'Self Deposit', 'Deposit', 500, 34900, CURDATE() - INTERVAL 5 DAY),
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 4 DAY, 'Ebay', 'Withdrawal', 600, 34300, CURDATE() - INTERVAL 4 DAY),
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 2 DAY, 'OnlineTransfer', 'Deposit', 700, 35000, CURDATE() - INTERVAL 2 DAY),
(UUID(), 1865764534, 1, CURDATE() - INTERVAL 1 DAY, 'Amazon.com', 'Withdrawal', 100, 34900, CURDATE() - INTERVAL 1 DAY);

CREATE TABLE springsecurity_db.`loans` (
  `loan_number` INT NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT NOT NULL,
  `start_dt` date NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`loan_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

-- Insert sample loans
INSERT INTO `loans` (`customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_at`)
VALUES 
(1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13'),
(1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06'),
(1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14'),
(1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');


CREATE TABLE springsecurity_db.`cards` (
  `card_id` INT NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` INT NOT NULL,
  `amount_used` INT NOT NULL,
  `available_amount` INT NOT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

-- Insert sample cards
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_at`)
VALUES 
('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE()),
('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE()),
('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

-- Step 7: Create `notice_details` (no changes needed)
CREATE TABLE `notice_details` (
  `notice_id` INT NOT NULL AUTO_INCREMENT,
  `notice_summary` varchar(200) NOT NULL,
  `notice_details` varchar(500) NOT NULL,
  `notic_beg_dt` date NOT NULL,
  `notic_end_dt` date DEFAULT NULL,
  `create_dt` date DEFAULT NULL,
  `update_dt` date DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
);

-- Insert sample notices
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES 
('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL),
('Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL),
('Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL),
('E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL),
('Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL),
('COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details', CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), NULL);

-- Step 8: Create `contact_messages` (no changes needed)
CREATE TABLE `contact_messages` (
  `contact_id` varchar(50) NOT NULL,
  `contact_name` varchar(50) NOT NULL,
  `contact_email` varchar(100) NOT NULL,
  `subject` varchar(500) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
);