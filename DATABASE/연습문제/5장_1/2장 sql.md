```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema car
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema car
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `car` DEFAULT CHARACTER SET utf8 ;
USE `car` ;

-- -----------------------------------------------------
-- Table `car`.`주차장`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car`.`주차장` (
  `주차일련번호` INT NOT NULL,
  `이름` VARCHAR(45) NULL,
  `대수` INT NULL,
  `주차층` INT NULL,
  PRIMARY KEY (`주차일련번호`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `car`.`직원`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car`.`직원` (
  `사원번호` INT NOT NULL,
  `이름` VARCHAR(45) NULL,
  `구내전화번호` VARCHAR(45) NULL,
  `운전면허증` VARCHAR(45) NULL,
  PRIMARY KEY (`사원번호`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `car`.`주차배정`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car`.`주차배정` (
  `일련번호` INT NOT NULL,
  `주차장이름` VARCHAR(45) NULL,
  `주차장_주차일련번호` INT NOT NULL,
  `직원_사원번호` INT NOT NULL,
  PRIMARY KEY (`일련번호`),
  INDEX `fk_주차배정_주차장_idx` (`주차장_주차일련번호` ASC) VISIBLE,
  INDEX `fk_주차배정_직원1_idx` (`직원_사원번호` ASC) VISIBLE,
  CONSTRAINT `fk_주차배정_주차장`
    FOREIGN KEY (`주차장_주차일련번호`)
    REFERENCES `car`.`주차장` (`주차일련번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_주차배정_직원1`
    FOREIGN KEY (`직원_사원번호`)
    REFERENCES `car`.`직원` (`사원번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```
