CREATE TABLE `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(45) NOT NULL,
  `customer_address` VARCHAR(100) NOT NULL,
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`));

  CREATE TABLE `order_item` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `order_item_name` VARCHAR(45) NULL,
  `order_item_count` VARCHAR(45) NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`order_item_id`),
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);