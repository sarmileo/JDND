-- write sql to create member table
CREATE TABLE IF NOT EXISTS `JDND_C3`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  `gender` CHAR(1) NOT NULL,
  `balance` DECIMAL UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
  )
ENGINE = InnoDB;
