CREATE DATABASE  IF NOT EXISTS `amnesiadatabase`;
USE `amnesiadatabase`;

DROP TABLE IF EXISTS `users`;

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username`varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf32;

INSERT INTO `users` (`username`,`password`) VALUES ('user1','user1');
INSERT INTO `users` (`username`,`password`) VALUES ('user2','user2');

CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender`varchar(45) DEFAULT NULL,
  `reciver`varchar(45) DEFAULT NULL,
  `message` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf32;

-- INSERT INTO `messages` (`sender`,`reciver`,`message`) VALUES ('user1','user2','Cześć user2');
-- INSERT INTO `messages` (`sender`,`reciver`,`message`) VALUES ('user2','user1','Cześć user1');

select * from `users`;

select * from `messages`;