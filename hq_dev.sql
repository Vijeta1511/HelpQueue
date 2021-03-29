DROP SCHEMA `hq`;
CREATE SCHEMA IF NOT EXISTS `hq`;
USE `hq`;

CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status_complete` tinyint NOT NULL DEFAULT '0',
  `technician_id` int DEFAULT NULL,
  `department` varchar(45) NOT NULL,
  `asignee` varchar(45) DEFAULT NULL,
  `solution` varchar(150) DEFAULT NULL,
  `status_queue` tinyint NOT NULL DEFAULT '0',
  `status_assign` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_id_UNIQUE` (`ticket_id`),
  KEY `technician_id_idx` (`technician_id`),
  CONSTRAINT `technician_id` FOREIGN KEY (`technician_id`) REFERENCES `technician` (`technician_id`)
);

CREATE TABLE `technician` (
  `technician_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `job_title` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`technician_id`),
  UNIQUE KEY `technician_id_UNIQUE` (`technician_id`)
);