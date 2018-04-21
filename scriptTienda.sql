-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tienda` DEFAULT CHARACTER SET utf8 ;
USE `tienda` ;

-- -----------------------------------------------------
-- Table `tienda`.`Estado_civil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Estado_civil` (
  `idEstado_civil` INT NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idEstado_civil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Cliente` (
  `idCliente` INT NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `genero` VARCHAR(1) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `estado_civil` INT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `estado_civil_idx` (`estado_civil` ASC),
  CONSTRAINT `estado_civil`
    FOREIGN KEY (`estado_civil`)
    REFERENCES `tienda`.`Estado_civil` (`idEstado_civil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Factura` (
  `idFactura` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `cliente` INT NOT NULL,
  `total` DOUBLE NOT NULL,
  `estado` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idFactura`),
  INDEX `cliente_idx` (`cliente` ASC),
  CONSTRAINT `cliente`
    FOREIGN KEY (`cliente`)
    REFERENCES `tienda`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`Tipo_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Tipo_item` (
  `idTipo_item` INT NOT NULL,
  `descripcion` VARCHAR(125) NOT NULL,
  PRIMARY KEY (`idTipo_item`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Item` (
  `idItem` INT NOT NULL,
  `tipo_item` INT NOT NULL,
  `descripcion` VARCHAR(125) NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`idItem`),
  INDEX `tipo_item_idx` (`tipo_item` ASC),
  CONSTRAINT `tipo_item`
    FOREIGN KEY (`tipo_item`)
    REFERENCES `tienda`.`Tipo_item` (`idTipo_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`Detalle_factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`Detalle_factura` (
  `idDetalle_factura` INT NOT NULL,
  `id_item` INT NOT NULL,
  `id_factura` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`idDetalle_factura`),
  INDEX `id_item_idx` (`id_item` ASC),
  INDEX `id_factura_idx` (`id_factura` ASC),
  CONSTRAINT `id_item`
    FOREIGN KEY (`id_item`)
    REFERENCES `tienda`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_factura`
    FOREIGN KEY (`id_factura`)
    REFERENCES `tienda`.`Factura` (`idFactura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Poblar tabla Estado_civil
-- -----------------------------------------------------
INSERT INTO Estado_civil VALUES (0,"Casado");
INSERT INTO Estado_civil VALUES (1,"Soltero");
INSERT INTO Estado_civil VALUES (2,"Otro");

-- -----------------------------------------------------
-- Poblar tabla Tipo_item
-- -----------------------------------------------------
INSERT INTO Tipo_item VALUES (0, "Lacteo");
INSERT INTO Tipo_item VALUES (1, "Carne");
INSERT INTO Tipo_item VALUES (2, "Otro");
