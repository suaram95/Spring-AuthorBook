/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.18-log : Database - spring_author_book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring_author_book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring_author_book`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `bio` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `author` */

insert  into `author`(`id`,`name`,`surname`,`email`,`gender`,`bio`) values 
(1,'Aram','Sukiasyan','suaram@mail.ru','MALE','hello'),
(8,'sdfsd','fsdfsdf','sdfsdf','FEMALE','sdfsdf'),
(9,'sdfsdfsd','sdfsdf','sdfsdfs','MALE','sdfsdfsdf'),
(10,'sdfsdfsdf','sdfsdf','sdfsdfsdf','MALE','sdfsf'),
(11,'sdfsdfsd','sdfsdf','sdfsdf','MALE','sdfsdfdaf'),
(12,'fghfhfg','dfgdfg','vbnvbnvbn','MALE','bnvndfg'),
(13,'dfghfgh','fghfg','vb','MALE','cvbgfhdfgh');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`description`,`price`,`author_id`) values 
(3,'dfsd','sdfsdf',121,8),
(4,'sdfsdf','dfsdf',32321,8),
(5,'dfgdfg','dfgdfg',2452,13);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
