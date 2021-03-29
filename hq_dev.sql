DROP SCHEMA `hq`;
CREATE SCHEMA IF NOT EXISTS `hq`;
USE `hq`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
);

CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_id_UNIQUE` (`department_id`)
);

CREATE TABLE `technician` (
  `technician_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`technician_id`),
  UNIQUE KEY `technician_id_UNIQUE` (`technician_id`)
);

CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status_ticket` int NOT NULL DEFAULT '1',
  `status_queue` tinyint NOT NULL DEFAULT '0',
  `department` int NOT NULL,
  `solution` varchar(150) DEFAULT NULL,
  `asignee` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_id_UNIQUE` (`ticket_id`)
);

CREATE TABLE `queue_table` (
  `queue_id` int NOT NULL AUTO_INCREMENT,
  `ticket_id` int NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`queue_id`),
  KEY `ticket_fk_idx` (`ticket_id`),
  CONSTRAINT `ticket_fk` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`) ON DELETE CASCADE
);
