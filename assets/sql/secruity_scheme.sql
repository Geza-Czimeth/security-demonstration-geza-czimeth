create database spring_security;

use spring_security;


CREATE TABLE `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(45) NOT NULL,
                         `password` VARCHAR(145) NOT NULL,
                         `enabled` INT NOT NULL,
                         PRIMARY KEY (`id`));

CREATE TABLE `authorities` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `username` varchar(45) NOT NULL,
                               `authority` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`));

INSERT IGNORE INTO `users` VALUES (NULL, 'userdb', 'userdb', '1');
INSERT IGNORE INTO `authorities` VALUES (NULL, 'userdb', 'write');

CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `email` varchar(45) NOT NULL,
                            `pwd` varchar(200) NOT NULL,
                            `authority` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`, `pwd`, `authority`)
VALUES ('johndoe@example.com', '$2a$12$iPdOsL9S/bgMKUasRMRjX.t8Sn7Q1ZrPrz/PwV0vExvBoa/0MLKXi', 'view');

INSERT INTO `customer` (`email`, `pwd`, `authority`)
VALUES ('produer@example.com', '$2a$10$Qyw2hI1l5nEn0drq7ByYFOgwOHz0YwZ3FdNEqV2XtT63Mhl7pHo0e', 'user');
