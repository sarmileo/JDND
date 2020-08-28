-- Create Database "reviews" and Tables product, review, comment
-- Using MySQLWorkbench

CREATE DATABASE IF NOT EXISTS `reviews`;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS product;

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`productId`)
);

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `title` varchar(50) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`)
);

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `reviewId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reviewId` (`reviewId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`reviewId`) REFERENCES `review` (`id`)
);

