CREATE DATABASE IF NOT EXISTS accounts;
USE accounts;

CREATE TABLE IF NOT EXISTS `accounts` (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `first_name` varchar(255) NOT NULL,
                                          `last_name` varchar(255) NOT NULL,
                                          `department` varchar(255) DEFAULT NULL,
                                          `password` varchar(255) NOT NULL,
                                          `salary` double NOT NULL DEFAULT '0',
                                          `emp_rank` varchar(255) NOT NULL DEFAULT 'Employee',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci