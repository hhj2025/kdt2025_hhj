```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`부서`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`부서` (
  `부서번호` INT NOT NULL,
  `부서이름` VARCHAR(45) NULL,
  PRIMARY KEY (`부서번호`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`직원`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`직원` (
  `직원번호` INT NOT NULL,
  `직원이름` VARCHAR(45) NULL,
  `직책` VARCHAR(45) CHARACTER SET 'armscii8' NULL,
  `부서_부서번호` INT NOT NULL,
  PRIMARY KEY (`직원번호`),
  INDEX `fk_직원_부서_idx` (`부서_부서번호` ASC) VISIBLE,
  CONSTRAINT `fk_직원_부서`
    FOREIGN KEY (`부서_부서번호`)
    REFERENCES `mydb`.`부서` (`부서번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`부양가족`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`부양가족` (
  `일련번호` INT NOT NULL,
  `이름` VARCHAR(45) NULL,
  `관계` VARCHAR(45) NULL,
  `직원_직원번호` INT NOT NULL,
  PRIMARY KEY (`일련번호`),
  INDEX `fk_부양가족_직원1_idx` (`직원_직원번호` ASC) VISIBLE,
  CONSTRAINT `fk_부양가족_직원1`
    FOREIGN KEY (`직원_직원번호`)
    REFERENCES `mydb`.`직원` (`직원번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`근무기록`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`근무기록` (
  `기간` DATETIME NULL,
  `직책` VARCHAR(45) NULL,
  `일련번호` INT NULL,
  `부서_부서번호` INT NOT NULL,
  `직원_직원번호` INT NOT NULL,
  INDEX `fk_근무기록_부서1_idx` (`부서_부서번호` ASC) VISIBLE,
  INDEX `fk_근무기록_직원1_idx` (`직원_직원번호` ASC) VISIBLE,
  CONSTRAINT `fk_근무기록_부서1`
    FOREIGN KEY (`부서_부서번호`)
    REFERENCES `mydb`.`부서` (`부서번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_근무기록_직원1`
    FOREIGN KEY (`직원_직원번호`)
    REFERENCES `mydb`.`직원` (`직원번호`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



```
