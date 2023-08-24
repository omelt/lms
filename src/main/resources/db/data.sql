DELETE FROM `user`;
INSERT INTO `user` (`user_name`, `user_email`, `user_age`,`gmt_creat`) VALUES
( 'Jone', 'test1@baomidou.com', 18,'2021-12-14'),
( 'Jack', 'test2@baomidou.com', 20,'2022-07-04'),
('Tom','test3@baomidou.com', 28,'2022-09-23'),
( 'Sandy', 'test4@baomidou.com', 21,'2023-02-01'),
( 'Billie', 'test5@baomidou.com', 24,'2023-07-22');

DELETE FROM `book`;
INSERT INTO `book` (`book_name`, `book_author`, `book_press`,`gmt_creat`) VALUES
( '幻象', '[美] 丹尼尔·布尔斯廷','南海出版公司','2021-11-11'),
( '我不想知道的事', '[英]德博拉·利维','湖南文艺出版社','2022-5-7'),
( '超越身体边界', '[意]西尔维娅·费代里奇','光启书局','2022-6-23'),
( '美剧编剧入行手册', '[英] 埃伦·桑德勒','中国友谊出版公司','2023-2-8'),
( '地下迷宫', '[韩]郑智渊','北京联合出版公司','2023-5-11');

-- password is 123
DELETE FROM `admin`;
INSERT INTO `admin` (`admin_name`, `admin_email`, `admin_age`,`admin_username`,`admin_password`,`admin_role`) VALUES
( 'TESTROOT', 'test1@TESTROOT.com', 28,'root','$2a$10$DlsATG76qxUoNH4kkZJO/.u3HXPkXczlIB/w9bNf.Lprzw0e0Ltou','ROLE_ROOT'),
( 'TESTADMIN', 'test1@ADMIN.com', 18,'admin','$2a$10$DlsATG76qxUoNH4kkZJO/.u3HXPkXczlIB/w9bNf.Lprzw0e0Ltou','ROLE_ADMIN');