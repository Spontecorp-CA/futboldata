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
-- Table structure for table `convocatoria`
--

DROP TABLE IF EXISTS `convocatoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `convocatoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equipo_id` int(10) unsigned NOT NULL,
  `partido_id` int(10) unsigned NOT NULL,
  `formacion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_convocatoria_equipo1_idx` (`equipo_id`),
  KEY `fk_convocatoria_partido1_idx` (`partido_id`),
  KEY `fk_convocatoria_formacion1_idx` (`formacion_id`),
  CONSTRAINT `fk_convocatoria_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_formacion1` FOREIGN KEY (`formacion_id`) REFERENCES `formacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convocatoria`
--

LOCK TABLES `convocatoria` WRITE;
/*!40000 ALTER TABLE `convocatoria` DISABLE KEYS */;
INSERT INTO `convocatoria` VALUES (1,1,1,NULL),(2,7,4,NULL),(3,8,1,NULL),(4,8,4,NULL),(5,1,11,NULL),(6,21,12,NULL),(7,38,20,NULL),(8,52,33,NULL),(9,1,32,NULL),(10,21,37,NULL),(11,21,7,NULL),(12,4,73,NULL),(13,23,53,NULL),(14,41,71,NULL),(15,37,69,NULL),(16,42,65,NULL),(17,6,73,NULL),(18,5,32,NULL),(19,14,11,NULL),(20,8,24,NULL),(21,5,24,NULL),(22,8,24,NULL),(23,8,24,NULL),(24,8,24,NULL),(25,8,24,NULL),(26,8,24,NULL),(27,8,24,NULL),(28,8,24,NULL),(29,8,24,NULL),(30,8,24,NULL),(31,26,7,NULL),(32,8,24,NULL),(33,8,24,NULL),(34,8,24,NULL),(35,8,24,NULL),(36,15,25,NULL),(37,7,25,NULL),(38,4,27,NULL),(39,11,27,NULL),(40,4,27,NULL),(41,10,28,NULL),(42,19,28,NULL),(43,3,75,NULL),(44,53,75,NULL),(45,1,74,NULL),(46,4,74,NULL),(47,7,76,NULL),(48,6,76,NULL),(49,8,24,NULL),(50,56,77,NULL),(51,58,77,NULL),(52,51,69,NULL),(53,38,71,NULL),(54,45,72,NULL),(55,42,72,NULL),(56,52,70,NULL),(57,48,70,NULL),(58,25,9,NULL),(59,22,9,NULL),(60,8,24,NULL),(61,4,27,NULL),(62,8,24,NULL),(63,8,24,NULL),(64,8,24,NULL),(65,8,24,NULL),(66,9,79,NULL),(67,10,79,NULL),(68,4,80,NULL),(69,6,80,NULL),(70,55,77,NULL),(71,57,77,NULL),(72,22,13,NULL),(73,23,13,NULL),(74,8,24,NULL),(75,8,24,NULL),(76,8,24,NULL),(77,8,24,NULL),(78,8,24,NULL),(79,8,24,NULL);
/*!40000 ALTER TABLE `convocatoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:38:22
