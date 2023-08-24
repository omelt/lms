--USER
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
	`user_id` INT  AUTO_INCREMENT  ,
	`user_number` VARCHAR(64)  NOT NULL  DEFAULT UUID() ,
	`user_name` VARCHAR(30)  NOT NULL ,
    `user_email` VARCHAR(30)  NOT NULL ,
	`user_age`  INT NOT NULL  ,
	`user_limit`  INT NOT NULL  DEFAULT 3,
    `gmt_creat`  DATE NOT NULL  DEFAULT NOW(),
	PRIMARY KEY (`user_id`)

);
CREATE INDEX `idx_user_number`  ON `user`(`user_number` );

--BOOK
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`(
	`book_id` INT  AUTO_INCREMENT  ,
	`book_number` VARCHAR(64)  NOT NULL  DEFAULT UUID() ,
	`book_name`  VARCHAR(108)  NOT NULL ,
        `book_author` VARCHAR(108)  NOT NULL ,
	`book_press`   VARCHAR(108)  NOT NULL ,
       `gmt_creat`  DATE NOT NULL  DEFAULT NOW(),
	PRIMARY KEY (`book_id`)
);
CREATE INDEX `idx_book_number`  ON `book`(`book_number` );

--borrow
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`(
	`borrow_id` INT  AUTO_INCREMENT  ,
	`book_id` INT NOT NULL  ,
	`user_id`  INT  NOT NULL ,
    `renew` BOOLEAN NOT NULL DEFAULT FALSE,
     `borrow_date`  DATE NOT NULL  DEFAULT NOW(),
     `back_date`  DATE NOT NULL  DEFAULT NOW() ,
     `gmt_creat`  DATE NOT NULL  DEFAULT NOW(),
	PRIMARY KEY (`borrow_id`)
);

--ADMIN
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`(
	`admin_id` INT  AUTO_INCREMENT  ,
	`admin_number` VARCHAR(64)  NOT NULL  DEFAULT UUID() ,
	`admin_name` VARCHAR(30)  NOT NULL ,
        `admin_email` VARCHAR(30)  NOT NULL ,
	`admin_age`  INT NOT NULL  ,
        `admin_username` VARCHAR(30)  NOT NULL ,
        `admin_password` VARCHAR(68)  NOT NULL ,
	`admin_role` VARCHAR(10)  NOT NULL ,
       `gmt_creat`  DATE NOT NULL  DEFAULT NOW(),
	PRIMARY KEY (`admin_id`)

);
CREATE INDEX `idx_admin_number`  ON `admin`(`admin_number` );
CREATE INDEX `idx_admin_username`  ON `admin`(`admin_username` );
