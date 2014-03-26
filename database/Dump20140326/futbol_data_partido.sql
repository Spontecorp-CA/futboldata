CREATE DATABASE  IF NOT EXISTS `futbol_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: spontedev32    Database: futbol_data
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `observaciones` text,
  `equipo_local_id` int(10) unsigned DEFAULT NULL,
  `equipo_visitante_id` int(10) unsigned DEFAULT NULL,
  `equipo_local_text` varchar(45) DEFAULT NULL,
  `equipo_visitante_text` varchar(45) DEFAULT NULL,
  `cancha_id` int(10) unsigned DEFAULT NULL,
  `tx_tv` int(11) DEFAULT NULL,
  `tx_radio` int(11) DEFAULT NULL,
  `asistencia` int(11) DEFAULT NULL,
  `status_partido_id` int(10) unsigned NOT NULL,
  `llave_id` int(10) unsigned DEFAULT NULL,
  `jornada_id` int(10) unsigned DEFAULT NULL,
  `goles_equipo_local` int(11) DEFAULT NULL,
  `goles_equipo_visitante` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_equipo1_idx` (`equipo_local_id`),
  KEY `fk_partido_equipo2_idx` (`equipo_visitante_id`),
  KEY `fk_partido_cancha1_idx` (`cancha_id`),
  KEY `fk_partido_status_partido1_idx` (`status_partido_id`),
  KEY `fk_partido_llave1_idx` (`llave_id`),
  KEY `fk_partido_jornada1_idx` (`jornada_id`),
  CONSTRAINT `fk_partido_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_equipo1` FOREIGN KEY (`equipo_local_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_equipo2` FOREIGN KEY (`equipo_visitante_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_jornada1` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_llave1` FOREIGN KEY (`llave_id`) REFERENCES `llave` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_status_partido1` FOREIGN KEY (`status_partido_id`) REFERENCES `status_partido` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-26  9:07:24
