CREATE DATABASE  IF NOT EXISTS `futbol_data_balonazos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data_balonazos`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: spontedev32    Database: futbol_data_balonazos
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
-- Table structure for table `equipo_en_grupo`
--

DROP TABLE IF EXISTS `equipo_en_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_en_grupo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equipo_id` int(10) unsigned NOT NULL,
  `grupo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo_has_grupo_grupo1_idx` (`grupo_id`),
  KEY `fk_equipo_has_grupo_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_has_grupo_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_has_grupo_grupo1` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_en_grupo`
--

LOCK TABLES `equipo_en_grupo` WRITE;
/*!40000 ALTER TABLE `equipo_en_grupo` DISABLE KEYS */;
INSERT INTO `equipo_en_grupo` VALUES (1,1,1),(2,7,1),(3,6,1),(4,4,1),(5,9,1),(6,13,1),(7,16,1),(8,18,1),(9,5,1),(10,2,1),(11,3,1),(12,14,1),(13,11,1),(14,17,1),(15,12,1),(16,10,1),(17,8,1),(18,15,1),(19,1,2),(20,4,2),(21,9,2),(22,5,2),(23,15,2),(24,16,2),(25,19,2),(26,2,2),(27,11,2),(28,10,2),(29,18,2),(30,6,2),(31,3,2),(32,17,2),(33,20,2),(34,7,2),(35,13,2),(36,8,2),(37,27,3),(38,30,3),(39,24,3),(40,23,3),(41,22,3),(42,25,3),(43,26,3),(44,21,3),(45,28,3),(46,29,3),(47,31,4),(48,34,4),(49,39,4),(50,36,4),(51,38,4),(52,35,4),(53,33,4),(54,32,4),(55,40,4),(56,37,4);
/*!40000 ALTER TABLE `equipo_en_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-14  9:23:09
