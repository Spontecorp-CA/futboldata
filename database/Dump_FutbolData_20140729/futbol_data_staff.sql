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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `persona_id` int(10) unsigned NOT NULL,
  `cargo_id` int(10) unsigned DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `partido_id` int(10) unsigned DEFAULT NULL,
  `agente_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_staff_asociacion1_idx` (`asociacion_id`),
  KEY `fk_staff_persona1_idx` (`persona_id`),
  KEY `fk_staff_equipo1_idx` (`equipo_id`),
  KEY `fk_staff_competicion1_idx` (`competicion_id`),
  KEY `fk_staff_club1_idx` (`club_id`),
  KEY `fk_staff_cargo1_idx` (`cargo_id`),
  KEY `fk_staff_partido1_idx` (`partido_id`),
  KEY `fk_staff_agente1_idx` (`agente_id`),
  CONSTRAINT `fk_staff_agente1` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (6,1,NULL,NULL,43,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,0,NULL,'2014-01-10',43,1,NULL,NULL,NULL,1,NULL,NULL),(10,0,NULL,'2014-01-06',43,2,NULL,NULL,NULL,3,NULL,NULL),(11,1,NULL,NULL,45,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,0,NULL,'2014-01-06',45,1,NULL,NULL,NULL,5,NULL,NULL),(13,1,NULL,NULL,46,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,0,NULL,'2014-01-11',46,4,NULL,NULL,NULL,1,NULL,NULL),(15,1,NULL,NULL,61,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,0,NULL,'2014-01-11',61,6,NULL,NULL,NULL,1,NULL,NULL),(17,0,NULL,'2014-01-11',43,1,NULL,1,NULL,NULL,NULL,NULL),(18,0,NULL,'2014-01-04',45,3,NULL,NULL,1,NULL,NULL,NULL),(19,0,NULL,'2014-01-11',43,2,NULL,NULL,1,NULL,NULL,NULL),(20,0,NULL,'2014-01-11',45,4,1,NULL,NULL,NULL,NULL,NULL),(22,1,NULL,NULL,63,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,1,NULL,NULL,61,6,NULL,1,NULL,NULL,NULL,NULL),(24,1,NULL,NULL,68,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,1,NULL,NULL,43,5,NULL,1,NULL,NULL,NULL,NULL),(26,1,NULL,NULL,68,3,NULL,1,NULL,NULL,NULL,NULL),(27,1,NULL,NULL,45,4,9,NULL,NULL,NULL,NULL,NULL),(28,1,NULL,NULL,46,8,NULL,NULL,NULL,19,NULL,NULL),(29,1,NULL,NULL,78,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,1,NULL,NULL,78,6,NULL,12,NULL,NULL,NULL,NULL),(31,1,NULL,NULL,79,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,1,NULL,NULL,63,4,NULL,5,NULL,NULL,NULL,NULL),(33,1,NULL,NULL,80,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,1,NULL,NULL,81,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,1,NULL,NULL,82,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,1,NULL,NULL,83,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,1,NULL,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,1,NULL,NULL,85,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,1,NULL,NULL,86,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,1,NULL,NULL,87,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,1,NULL,NULL,86,4,NULL,NULL,1,NULL,NULL,NULL),(42,1,NULL,NULL,87,1,NULL,NULL,1,NULL,NULL,NULL),(43,1,NULL,NULL,115,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,1,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,1,NULL,NULL,117,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,1,NULL,NULL,118,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,1,NULL,NULL,119,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,1,NULL,NULL,117,4,NULL,NULL,6,NULL,NULL,NULL),(49,1,NULL,NULL,118,11,NULL,NULL,6,NULL,NULL,NULL),(50,1,NULL,NULL,119,12,NULL,NULL,6,NULL,NULL,NULL),(51,1,NULL,NULL,120,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,0,NULL,'2014-01-28',120,10,NULL,NULL,6,NULL,NULL,NULL),(53,1,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,1,NULL,NULL,121,13,NULL,NULL,6,NULL,NULL,NULL),(55,1,NULL,NULL,120,10,NULL,NULL,6,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:31