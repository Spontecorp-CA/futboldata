SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `futbol_data_balonazos` ;
CREATE SCHEMA IF NOT EXISTS `futbol_data_balonazos` DEFAULT CHARACTER SET utf8 ;
USE `futbol_data_balonazos` ;

-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`pais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`pais` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`pais` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 249
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`ciudad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`ciudad` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`ciudad` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ciudad` VARCHAR(45) NOT NULL,
  `pais_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ciudad_pais1_idx` (`pais_id` ASC),
  CONSTRAINT `fk_ciudad_pais1`
    FOREIGN KEY (`pais_id`)
    REFERENCES `futbol_data_balonazos`.`pais` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`direccion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`direccion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `direccion` TEXT NULL DEFAULT NULL,
  `pagina_web` VARCHAR(80) NULL DEFAULT NULL,
  `latitud` INT(11) NULL DEFAULT NULL,
  `longitud` INT(11) NULL DEFAULT NULL,
  `ciudad_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_direccion_ciudad1_idx` (`ciudad_id` ASC),
  CONSTRAINT `fk_direccion_ciudad1`
    FOREIGN KEY (`ciudad_id`)
    REFERENCES `futbol_data_balonazos`.`ciudad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`persona` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`persona` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido` VARCHAR(45) NULL DEFAULT NULL,
  `sexo` VARCHAR(1) NULL DEFAULT NULL,
  `apodo` VARCHAR(45) NULL DEFAULT NULL,
  `documento_identidad` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_nacimiento` DATE NULL DEFAULT NULL,
  `nacionalidad` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `celular` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(80) NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `foto` TEXT NULL DEFAULT NULL,
  `direccion_id` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_persona_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_persona_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`agente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`agente` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`agente` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `persona_id` INT(10) UNSIGNED NOT NULL,
  `fecha_inicio` DATE NULL DEFAULT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_agente_persona1_idx` (`persona_id` ASC),
  CONSTRAINT `fk_agente_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`asociacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`asociacion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`asociacion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `direccion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `logo` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asociacion_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_asociacion_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`arbitro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`arbitro` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`arbitro` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `persona_id` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `asociacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_arbitro_persona1_idx` (`persona_id` ASC),
  INDEX `fk_arbitro_asociacion1_idx` (`asociacion_id` ASC),
  CONSTRAINT `fk_arbitro_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_arbitro_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`premio` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`arbitro_premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`arbitro_premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`arbitro_premio` (
  `arbitro_id` INT(10) UNSIGNED NOT NULL,
  `premio_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`arbitro_id`, `premio_id`),
  INDEX `fk_arbitro_has_premio_premio1_idx` (`premio_id` ASC),
  INDEX `fk_arbitro_has_premio_arbitro1_idx` (`arbitro_id` ASC),
  CONSTRAINT `fk_arbitro_has_premio_arbitro1`
    FOREIGN KEY (`arbitro_id`)
    REFERENCES `futbol_data_balonazos`.`arbitro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_arbitro_has_premio_premio1`
    FOREIGN KEY (`premio_id`)
    REFERENCES `futbol_data_balonazos`.`premio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`club` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`club` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `year_fundacion` VARCHAR(4) NULL DEFAULT NULL,
  `historia` LONGTEXT NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `direccion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `logo` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_club_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_club_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`audio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`audio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`audio` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `is_himno` INT(11) NULL DEFAULT NULL,
  `club_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_audio_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_audio_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`cancha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`cancha` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`cancha` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `capacidad` INT(11) NULL DEFAULT NULL,
  `superficie` VARCHAR(45) NULL DEFAULT NULL,
  `medidas` VARCHAR(45) NULL DEFAULT NULL,
  `foto` TEXT NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `direccion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `coordenada_long` VARCHAR(12) NULL DEFAULT NULL,
  `coordenada_lat` VARCHAR(12) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cancha_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_cancha_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`cargo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`cargo` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`categoria` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`categoria` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `tiempo_juego` INT NULL DEFAULT 0,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`competicion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`competicion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`competicion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `asociacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `direccion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `logo` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_competicion_asociacion1_idx` (`asociacion_id` ASC),
  INDEX `fk_competicion_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_competicion_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_competicion_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`temporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`temporada` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`temporada` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_inicio` DATE NULL DEFAULT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `competicion_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_temporada_competicion1_idx` (`competicion_id` ASC),
  CONSTRAINT `fk_temporada_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`fase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`fase` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`fase` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `tipo_fase` INT NULL,
  `status` INT NULL,
  `temporada_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fase_temporada1_idx` (`temporada_id` ASC),
  CONSTRAINT `fk_fase_temporada1`
    FOREIGN KEY (`temporada_id`)
    REFERENCES `futbol_data_balonazos`.`temporada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`grupo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`grupo` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT NULL,
  `fase_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_fase1_idx` (`fase_id` ASC),
  CONSTRAINT `fk_grupo_fase1`
    FOREIGN KEY (`fase_id`)
    REFERENCES `futbol_data_balonazos`.`fase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`jornada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`jornada` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`jornada` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` INT(11) NULL DEFAULT NULL,
  `alias` VARCHAR(45) NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `grupo_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jornada_grupo1_idx` (`grupo_id` ASC),
  CONSTRAINT `fk_jornada_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `futbol_data_balonazos`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `nombre_alterno` VARCHAR(45) NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `abreviacion` VARCHAR(10) NULL,
  `logo` TEXT NULL DEFAULT NULL,
  `club_id` INT(10) UNSIGNED NOT NULL,
  `categoria_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_equipo_club1_idx` (`club_id` ASC),
  INDEX `fk_equipo_categoria1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_equipo_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `futbol_data_balonazos`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`status_partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`status_partido` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`status_partido` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` INT(11) NULL DEFAULT NULL COMMENT '0: no comenzado\n1: primer tiempo\n2: segundo tiempo\n3: finalizado' /* comment truncated */ /*etc*/,
  `nombre` VARCHAR(45) NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`llave`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`llave` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`llave` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT NULL,
  `fase_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_llave_fase1_idx` (`fase_id` ASC),
  CONSTRAINT `fk_llave_fase1`
    FOREIGN KEY (`fase_id`)
    REFERENCES `futbol_data_balonazos`.`fase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`partido` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`partido` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `hora_inicio` TIME NULL DEFAULT NULL,
  `hora_fin` TIME NULL DEFAULT NULL,
  `observaciones` TEXT NULL DEFAULT NULL,
  `equipo_local_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `equipo_visitante_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `equipo_local_text` VARCHAR(45) NULL DEFAULT NULL,
  `equipo_visitante_text` VARCHAR(45) NULL DEFAULT NULL,
  `cancha_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `tx_tv` INT(11) NULL DEFAULT NULL,
  `tx_radio` INT(11) NULL DEFAULT NULL,
  `asistencia` INT(11) NULL DEFAULT NULL,
  `status_partido_id` INT(10) UNSIGNED NOT NULL,
  `llave_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `jornada_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `goles_equipo_local` INT(11) NULL DEFAULT NULL,
  `goles_equipo_visitante` INT(11) NULL DEFAULT NULL,
  `goles_local_prorroga` INT NULL DEFAULT NULL,
  `goles_visitante_prorroga` INT NULL DEFAULT NULL,
  `goles_local_penalties` INT NULL DEFAULT NULL,
  `goles_visitante_penalties` INT NULL DEFAULT NULL,
  `numero` INT NULL,
  `categoria_id` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partido_equipo1_idx` (`equipo_local_id` ASC),
  INDEX `fk_partido_equipo2_idx` (`equipo_visitante_id` ASC),
  INDEX `fk_partido_cancha1_idx` (`cancha_id` ASC),
  INDEX `fk_partido_status_partido1_idx` (`status_partido_id` ASC),
  INDEX `fk_partido_llave1_idx` (`llave_id` ASC),
  INDEX `fk_partido_jornada1_idx` (`jornada_id` ASC),
  INDEX `fk_partido_categoria1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_partido_equipo1`
    FOREIGN KEY (`equipo_local_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_equipo2`
    FOREIGN KEY (`equipo_visitante_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_cancha1`
    FOREIGN KEY (`cancha_id`)
    REFERENCES `futbol_data_balonazos`.`cancha` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_status_partido1`
    FOREIGN KEY (`status_partido_id`)
    REFERENCES `futbol_data_balonazos`.`status_partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_llave1`
    FOREIGN KEY (`llave_id`)
    REFERENCES `futbol_data_balonazos`.`llave` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_jornada1`
    FOREIGN KEY (`jornada_id`)
    REFERENCES `futbol_data_balonazos`.`jornada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `futbol_data_balonazos`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`clasificacion_grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`clasificacion_grupo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`clasificacion_grupo` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `grupo_id` INT(10) UNSIGNED NOT NULL,
  `j_jugados` INT(11) NULL DEFAULT NULL,
  `j_ganados` INT(11) NULL DEFAULT NULL,
  `j_empatados` INT(11) NULL DEFAULT NULL,
  `j_perdidos` INT(11) NULL DEFAULT NULL,
  `goles_favor` INT(11) NULL DEFAULT NULL,
  `goles_contra` INT(11) NULL DEFAULT NULL,
  `diferencia` INT(11) NULL DEFAULT NULL,
  `puntos` INT(11) NULL DEFAULT NULL,
  `is_local` INT(11) NULL DEFAULT NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clasificacion_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_clasificacion_grupo_grupo1_idx` (`grupo_id` ASC),
  CONSTRAINT `fk_clasificacion_equipo10`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_grupo_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `futbol_data_balonazos`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`clasificacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`clasificacion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`clasificacion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `jornada_id` INT(10) UNSIGNED NOT NULL,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `partido_id` INT(10) UNSIGNED NOT NULL,
  `clasificacion_grupo_id` INT(10) UNSIGNED NOT NULL,
  `j_jugados` INT(11) NULL DEFAULT NULL,
  `j_ganados` INT(11) NULL DEFAULT NULL,
  `j_empatados` INT(11) NULL DEFAULT NULL,
  `j_perdidos` INT(11) NULL DEFAULT NULL,
  `goles_favor` INT(11) NULL DEFAULT NULL,
  `goles_contra` INT(11) NULL DEFAULT NULL,
  `diferencia` INT(11) NULL DEFAULT NULL,
  `puntos` INT(11) NULL DEFAULT NULL,
  `is_local` INT(11) NULL DEFAULT NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clasificacion_jornada1_idx` (`jornada_id` ASC),
  INDEX `fk_clasificacion_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_clasificacion_partido1_idx` (`partido_id` ASC),
  INDEX `fk_clasificacion_clasificacion_grupo1_idx` (`clasificacion_grupo_id` ASC),
  CONSTRAINT `fk_clasificacion_jornada1`
    FOREIGN KEY (`jornada_id`)
    REFERENCES `futbol_data_balonazos`.`jornada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `futbol_data_balonazos`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_clasificacion_grupo1`
    FOREIGN KEY (`clasificacion_grupo_id`)
    REFERENCES `futbol_data_balonazos`.`clasificacion_grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`club_cancha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`club_cancha` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`club_cancha` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `club_id` INT(10) UNSIGNED NOT NULL,
  `cancha_id` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_club_has_cancha_cancha1_idx` (`cancha_id` ASC),
  INDEX `fk_club_has_cancha_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_club_has_cancha_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_club_has_cancha_cancha1`
    FOREIGN KEY (`cancha_id`)
    REFERENCES `futbol_data_balonazos`.`cancha` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`club_premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`club_premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`club_premio` (
  `club_id` INT(10) UNSIGNED NOT NULL,
  `premio_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`club_id`, `premio_id`),
  INDEX `fk_club_has_premio_premio1_idx` (`premio_id` ASC),
  INDEX `fk_club_has_premio_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_club_has_premio_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_club_has_premio_premio1`
    FOREIGN KEY (`premio_id`)
    REFERENCES `futbol_data_balonazos`.`premio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`competicion_arbitro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`competicion_arbitro` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`competicion_arbitro` (
  `competicion_id` INT(10) UNSIGNED NOT NULL,
  `arbitro_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`competicion_id`, `arbitro_id`),
  INDEX `fk_competicion_has_arbitro_arbitro1_idx` (`arbitro_id` ASC),
  INDEX `fk_competicion_has_arbitro_competicion1_idx` (`competicion_id` ASC),
  CONSTRAINT `fk_competicion_has_arbitro_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_competicion_has_arbitro_arbitro1`
    FOREIGN KEY (`arbitro_id`)
    REFERENCES `futbol_data_balonazos`.`arbitro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`patrocinante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`patrocinante` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`patrocinante` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `is_marca_equipacion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`posicion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`posicion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`posicion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`jugador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`jugador` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`jugador` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `persona_id` INT(10) UNSIGNED NOT NULL,
  `posicion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `camiseta` INT(11) NULL DEFAULT NULL,
  `zurdo` INT(11) NULL DEFAULT NULL COMMENT 'zurdo: 0, para derecho; 1; para zurdo; 2 ambidiestro',
  `altura` DOUBLE NULL DEFAULT NULL,
  `peso` DOUBLE NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `agente_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `categoria_id` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jugador_persona1_idx` (`persona_id` ASC),
  INDEX `fk_jugador_posicion1_idx` (`posicion_id` ASC),
  INDEX `fk_jugador_agente1_idx` (`agente_id` ASC),
  INDEX `fk_jugador_categoria1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_jugador_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_posicion1`
    FOREIGN KEY (`posicion_id`)
    REFERENCES `futbol_data_balonazos`.`posicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_agente1`
    FOREIGN KEY (`agente_id`)
    REFERENCES `futbol_data_balonazos`.`agente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `futbol_data_balonazos`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`contrato` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`contrato` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATE NULL DEFAULT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `patrocinante_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `asociacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `competicion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `club_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `equipo_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `jugador_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contrato_patrocinante1_idx` (`patrocinante_id` ASC),
  INDEX `fk_contrato_asociacion1_idx` (`asociacion_id` ASC),
  INDEX `fk_contrato_club1_idx` (`club_id` ASC),
  INDEX `fk_contrato_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_contrato_jugador1_idx` (`jugador_id` ASC),
  INDEX `fk_contrato_competicion1_idx` (`competicion_id` ASC),
  CONSTRAINT `fk_contrato_patrocinante1`
    FOREIGN KEY (`patrocinante_id`)
    REFERENCES `futbol_data_balonazos`.`patrocinante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`formacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`formacion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`formacion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(15) NOT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`convocatoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`convocatoria` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`convocatoria` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `partido_id` INT(10) UNSIGNED NOT NULL,
  `formacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_convocatoria_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_convocatoria_partido1_idx` (`partido_id` ASC),
  INDEX `fk_convocatoria_formacion1_idx` (`formacion_id` ASC),
  CONSTRAINT `fk_convocatoria_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `futbol_data_balonazos`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_formacion1`
    FOREIGN KEY (`formacion_id`)
    REFERENCES `futbol_data_balonazos`.`formacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`convocado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`convocado` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`convocado` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `jugador_id` INT(10) UNSIGNED NOT NULL,
  `camiseta` INT(11) NULL DEFAULT NULL,
  `titular` INT(11) NULL DEFAULT NULL COMMENT '0: suplente',
  `tiempo_jugado` TIME NULL,
  `posicion_id` INT(10) UNSIGNED NOT NULL,
  `convocatoria_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_convocado_jugador1_idx` (`jugador_id` ASC),
  INDEX `fk_convocado_posicion1_idx` (`posicion_id` ASC),
  INDEX `fk_convocado_convocatoria1_idx` (`convocatoria_id` ASC),
  CONSTRAINT `fk_convocado_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_convocado_posicion1`
    FOREIGN KEY (`posicion_id`)
    REFERENCES `futbol_data_balonazos`.`posicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_convocado_convocatoria1`
    FOREIGN KEY (`convocatoria_id`)
    REFERENCES `futbol_data_balonazos`.`convocatoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`email`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`email` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`email` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(80) NOT NULL,
  `direccion_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_email_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_email_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_cancha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_cancha` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_cancha` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `cancha_id` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_equipo_has_cancha_cancha1_idx` (`cancha_id` ASC),
  INDEX `fk_equipo_has_cancha_equipo1_idx` (`equipo_id` ASC),
  CONSTRAINT `fk_equipo_has_cancha_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_cancha_cancha1`
    FOREIGN KEY (`cancha_id`)
    REFERENCES `futbol_data_balonazos`.`cancha` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_premio` (
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `premio_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`equipo_id`, `premio_id`),
  INDEX `fk_equipo_has_premio_premio1_idx` (`premio_id` ASC),
  INDEX `fk_equipo_has_premio_equipo1_idx` (`equipo_id` ASC),
  CONSTRAINT `fk_equipo_has_premio_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_premio_premio1`
    FOREIGN KEY (`premio_id`)
    REFERENCES `futbol_data_balonazos`.`premio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`titulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`titulo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`titulo` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `id_temporada` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_titulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_titulo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_titulo` (
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `titulo_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`equipo_id`, `titulo_id`),
  INDEX `fk_equipo_has_titulo_titulo1_idx` (`titulo_id` ASC),
  INDEX `fk_equipo_has_titulo_equipo1_idx` (`equipo_id` ASC),
  CONSTRAINT `fk_equipo_has_titulo_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_titulo_titulo1`
    FOREIGN KEY (`titulo_id`)
    REFERENCES `futbol_data_balonazos`.`titulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`tipo_evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`tipo_evento` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`tipo_evento` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`evento` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`evento` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `status` INT NULL,
  `tipo_evento_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evento_tipo_evento1_idx` (`tipo_evento_id` ASC),
  CONSTRAINT `fk_evento_tipo_evento1`
    FOREIGN KEY (`tipo_evento_id`)
    REFERENCES `futbol_data_balonazos`.`tipo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`imagen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`imagen` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`imagen` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `nombre` VARCHAR(150) NULL DEFAULT NULL,
  `is_logo` INT(11) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `asociacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `cancha_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `patrocinante_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `equipo_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `persona_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `club_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `competicion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_imagen_asociacion1_idx` (`asociacion_id` ASC),
  INDEX `fk_imagen_cancha1_idx` (`cancha_id` ASC),
  INDEX `fk_imagen_patrocinante1_idx` (`patrocinante_id` ASC),
  INDEX `fk_imagen_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_imagen_persona1_idx` (`persona_id` ASC),
  INDEX `fk_imagen_club1_idx` (`club_id` ASC),
  INDEX `fk_imagen_competicion1_idx` (`competicion_id` ASC),
  CONSTRAINT `fk_imagen_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_cancha1`
    FOREIGN KEY (`cancha_id`)
    REFERENCES `futbol_data_balonazos`.`cancha` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_patrocinante1`
    FOREIGN KEY (`patrocinante_id`)
    REFERENCES `futbol_data_balonazos`.`patrocinante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`jugador_premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`jugador_premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`jugador_premio` (
  `jugador_id` INT(10) UNSIGNED NOT NULL,
  `premio_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`jugador_id`, `premio_id`),
  INDEX `fk_jugador_has_premio_premio1_idx` (`premio_id` ASC),
  INDEX `fk_jugador_has_premio_jugador1_idx` (`jugador_id` ASC),
  CONSTRAINT `fk_jugador_has_premio_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_premio_premio1`
    FOREIGN KEY (`premio_id`)
    REFERENCES `futbol_data_balonazos`.`premio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`jugador_titulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`jugador_titulo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`jugador_titulo` (
  `jugador_id` INT(10) UNSIGNED NOT NULL,
  `titulo_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`jugador_id`, `titulo_id`),
  INDEX `fk_jugador_has_titulo_titulo1_idx` (`titulo_id` ASC),
  INDEX `fk_jugador_has_titulo_jugador1_idx` (`jugador_id` ASC),
  CONSTRAINT `fk_jugador_has_titulo_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_titulo_titulo1`
    FOREIGN KEY (`titulo_id`)
    REFERENCES `futbol_data_balonazos`.`titulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`tipo_arbitro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`tipo_arbitro` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`tipo_arbitro` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`partido_arbitro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`partido_arbitro` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`partido_arbitro` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `partido_id` INT(10) UNSIGNED NOT NULL,
  `arbitro_id` INT(10) UNSIGNED NOT NULL,
  `tipo_arbitro_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partido_has_arbitro_arbitro1_idx` (`arbitro_id` ASC),
  INDEX `fk_partido_has_arbitro_partido1_idx` (`partido_id` ASC),
  INDEX `fk_partido_has_arbitro_tipo_arbitro1_idx` (`tipo_arbitro_id` ASC),
  CONSTRAINT `fk_partido_has_arbitro_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `futbol_data_balonazos`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_arbitro_arbitro1`
    FOREIGN KEY (`arbitro_id`)
    REFERENCES `futbol_data_balonazos`.`arbitro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_arbitro_tipo_arbitro1`
    FOREIGN KEY (`tipo_arbitro_id`)
    REFERENCES `futbol_data_balonazos`.`tipo_arbitro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`staff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`staff` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`staff` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` INT(11) NULL DEFAULT NULL,
  `fecha_desde` DATE NULL DEFAULT NULL,
  `fecha_hasta` DATE NULL DEFAULT NULL,
  `persona_id` INT(10) UNSIGNED NOT NULL,
  `cargo_id` INT(10) UNSIGNED NULL,
  `asociacion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `equipo_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `competicion_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `club_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `partido_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `agente_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_staff_asociacion1_idx` (`asociacion_id` ASC),
  INDEX `fk_staff_persona1_idx` (`persona_id` ASC),
  INDEX `fk_staff_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_staff_competicion1_idx` (`competicion_id` ASC),
  INDEX `fk_staff_club1_idx` (`club_id` ASC),
  INDEX `fk_staff_cargo1_idx` (`cargo_id` ASC),
  INDEX `fk_staff_partido1_idx` (`partido_id` ASC),
  INDEX `fk_staff_agente1_idx` (`agente_id` ASC),
  CONSTRAINT `fk_staff_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_cargo1`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `futbol_data_balonazos`.`cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `futbol_data_balonazos`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_agente1`
    FOREIGN KEY (`agente_id`)
    REFERENCES `futbol_data_balonazos`.`agente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`partido_evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`partido_evento` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`partido_evento` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `partido_id` INT(10) UNSIGNED NOT NULL,
  `evento_id` INT(10) UNSIGNED NOT NULL,
  `minuto` INT(11) NULL DEFAULT NULL,
  `jugador1_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `jugador2_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `staff_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partido_has_evento_evento1_idx` (`evento_id` ASC),
  INDEX `fk_partido_has_evento_partido1_idx` (`partido_id` ASC),
  INDEX `fk_partido_evento_jugador1_idx` (`jugador1_id` ASC),
  INDEX `fk_partido_evento_jugador2_idx` (`jugador2_id` ASC),
  INDEX `fk_partido_evento_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_partido_has_evento_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `futbol_data_balonazos`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_evento_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `futbol_data_balonazos`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_jugador1`
    FOREIGN KEY (`jugador1_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_jugador2`
    FOREIGN KEY (`jugador2_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `futbol_data_balonazos`.`staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`perfil` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`perfil` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`tipo_red_social`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`tipo_red_social` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`tipo_red_social` (
  `id` INT(10) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`red_social`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`red_social` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`red_social` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(80) NULL DEFAULT NULL,
  `persona_id` INT(10) UNSIGNED NULL,
  `asociacion_id` INT(10) UNSIGNED NULL,
  `club_id` INT(10) UNSIGNED NULL,
  `competicion_id` INT(10) UNSIGNED NULL,
  `equipo_id` INT(10) UNSIGNED NULL,
  `tipo_red_social_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_red_social_persona1_idx` (`persona_id` ASC),
  INDEX `fk_red_social_asociacion1_idx` (`asociacion_id` ASC),
  INDEX `fk_red_social_club1_idx` (`club_id` ASC),
  INDEX `fk_red_social_competicion1_idx` (`competicion_id` ASC),
  INDEX `fk_red_social_equipo1_idx` (`equipo_id` ASC),
  INDEX `fk_red_social_tipo_red_social1_idx` (`tipo_red_social_id` ASC),
  CONSTRAINT `fk_red_social_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `futbol_data_balonazos`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `futbol_data_balonazos`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_tipo_red_social1`
    FOREIGN KEY (`tipo_red_social_id`)
    REFERENCES `futbol_data_balonazos`.`tipo_red_social` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`staff_has_premio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`staff_has_premio` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`staff_has_premio` (
  `staff_id` INT(10) UNSIGNED NOT NULL,
  `premio_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`staff_id`, `premio_id`),
  INDEX `fk_staff_has_premio_premio1_idx` (`premio_id` ASC),
  INDEX `fk_staff_has_premio_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_staff_has_premio_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `futbol_data_balonazos`.`staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_has_premio_premio1`
    FOREIGN KEY (`premio_id`)
    REFERENCES `futbol_data_balonazos`.`premio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`staff_has_titulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`staff_has_titulo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`staff_has_titulo` (
  `staff_id` INT(10) UNSIGNED NOT NULL,
  `titulo_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`staff_id`, `titulo_id`),
  INDEX `fk_staff_has_titulo_titulo1_idx` (`titulo_id` ASC),
  INDEX `fk_staff_has_titulo_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_staff_has_titulo_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `futbol_data_balonazos`.`staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_has_titulo_titulo1`
    FOREIGN KEY (`titulo_id`)
    REFERENCES `futbol_data_balonazos`.`titulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`telefono`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`telefono` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`telefono` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefono_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_telefono_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `futbol_data_balonazos`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`temporada_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`temporada_categoria` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`temporada_categoria` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `temporada_id` INT(10) UNSIGNED NOT NULL,
  `categoria_id` INT(10) UNSIGNED NOT NULL,
  `alias` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_temporada_has_categoria_categoria1_idx` (`categoria_id` ASC),
  INDEX `fk_temporada_has_categoria_temporada1_idx` (`temporada_id` ASC),
  CONSTRAINT `fk_temporada_has_categoria_temporada1`
    FOREIGN KEY (`temporada_id`)
    REFERENCES `futbol_data_balonazos`.`temporada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_temporada_has_categoria_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `futbol_data_balonazos`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`transferencia` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`transferencia` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `jugador_id` INT(10) UNSIGNED NOT NULL,
  `equipo_provedor_id` INT(10) UNSIGNED NOT NULL,
  `equipo_receptor_id` INT(10) UNSIGNED NOT NULL,
  `fecha_ingreso` DATE NULL DEFAULT NULL,
  `fecha_egreso` DATE NULL DEFAULT NULL,
  `alta_baja` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jugador_has_equipo_equipo1_idx` (`equipo_provedor_id` ASC),
  INDEX `fk_jugador_has_equipo_jugador1_idx` (`jugador_id` ASC),
  INDEX `fk_transferencia_equipo1_idx` (`equipo_receptor_id` ASC),
  CONSTRAINT `fk_jugador_has_equipo_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_equipo_equipo1`
    FOREIGN KEY (`equipo_provedor_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_transferencia_equipo1`
    FOREIGN KEY (`equipo_receptor_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`user` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`user` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `status` INT(11) NOT NULL,
  `perfil_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_perfil1_idx` (`perfil_id` ASC),
  CONSTRAINT `fk_user_perfil1`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `futbol_data_balonazos`.`perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_has_jugador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_has_jugador` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_has_jugador` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `jugador_id` INT(10) UNSIGNED NOT NULL,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `fecha_entrada` DATE NULL,
  `fecha_salida` DATE NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jugador_has_equipo_equipo2_idx` (`equipo_id` ASC),
  INDEX `fk_jugador_has_equipo_jugador2_idx` (`jugador_id` ASC),
  CONSTRAINT `fk_jugador_has_equipo_jugador2`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `futbol_data_balonazos`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_has_equipo_equipo2`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`afiliacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`afiliacion` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`afiliacion` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `asociacion_id` INT(10) UNSIGNED NULL,
  `asociacion_super_id` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asociacion_asociacion_super_idx` (`asociacion_super_id` ASC),
  INDEX `fk_asociacion_has_asociacion_1_idx` (`asociacion_id` ASC),
  CONSTRAINT `fk_asociacion_has_asociacion_asociacion1`
    FOREIGN KEY (`asociacion_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_asociacion_has_asociacion_asociacion2`
    FOREIGN KEY (`asociacion_super_id`)
    REFERENCES `futbol_data_balonazos`.`asociacion` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_in_liga`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_in_liga` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_in_liga` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `competicion_id` INT(10) UNSIGNED NOT NULL,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `status` INT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_equipo_in_liga_competicion1_idx` (`competicion_id` ASC),
  INDEX `fk_equipo_in_liga_equipo1_idx` (`equipo_id` ASC),
  CONSTRAINT `fk_equipo_in_liga_competicion1`
    FOREIGN KEY (`competicion_id`)
    REFERENCES `futbol_data_balonazos`.`competicion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_in_liga_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `futbol_data_balonazos`.`equipo_en_grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `futbol_data_balonazos`.`equipo_en_grupo` ;

CREATE TABLE IF NOT EXISTS `futbol_data_balonazos`.`equipo_en_grupo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `equipo_id` INT(10) UNSIGNED NOT NULL,
  `grupo_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_equipo_has_grupo_grupo1_idx` (`grupo_id` ASC),
  INDEX `fk_equipo_has_grupo_equipo1_idx` (`equipo_id` ASC),
  CONSTRAINT `fk_equipo_has_grupo_equipo1`
    FOREIGN KEY (`equipo_id`)
    REFERENCES `futbol_data_balonazos`.`equipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_has_grupo_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `futbol_data_balonazos`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
