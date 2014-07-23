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
-- Table structure for table `equipo_in_liga`
--

DROP TABLE IF EXISTS `equipo_in_liga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_in_liga` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `competicion_id` int(10) unsigned NOT NULL,
  `equipo_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_equipo_in_liga_competicion1_idx` (`competicion_id`),
  KEY `fk_equipo_in_liga_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_in_liga_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_in_liga_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_in_liga`
--

LOCK TABLES `equipo_in_liga` WRITE;
/*!40000 ALTER TABLE `equipo_in_liga` DISABLE KEYS */;
INSERT INTO `equipo_in_liga` VALUES (1,1,1,1),(2,1,4,1),(3,2,2,0),(4,2,2,0),(5,5,1,1),(6,5,4,0),(7,1,5,1),(8,1,6,1),(9,1,7,1),(10,1,8,1),(11,5,4,1),(12,5,5,1),(13,5,6,1),(14,5,7,1),(15,5,8,1),(16,5,9,1),(17,1,10,1),(18,1,11,1),(19,1,9,1),(20,1,12,1),(21,1,13,1),(22,1,14,1),(23,1,15,1),(24,1,16,1),(25,1,17,1),(26,1,18,1),(27,1,19,1),(28,1,20,1),(29,5,10,1),(30,10,21,1),(31,10,22,1),(32,10,23,1),(33,10,24,1),(34,10,25,1),(35,10,26,1),(36,10,27,1),(37,10,28,1),(38,10,29,1),(39,10,30,1),(40,10,31,1),(41,10,32,1),(42,10,33,1),(43,10,46,1),(44,10,34,1),(45,10,35,1),(46,10,36,1),(47,10,37,1),(48,10,38,1),(49,10,39,1),(50,10,40,1),(51,10,41,1),(52,10,42,1),(53,10,43,1),(54,10,44,1),(55,10,45,1),(56,10,47,1),(57,10,48,1),(58,7,2,1),(59,7,3,1),(60,10,49,1),(61,10,50,1),(62,10,51,1),(63,10,52,1),(64,5,2,1),(65,5,3,1),(66,7,53,1),(67,7,54,1),(68,7,55,1),(69,7,56,1),(70,7,57,1),(71,7,58,1),(72,7,59,1),(73,7,60,1),(74,7,61,1),(75,7,62,1),(76,7,63,1),(77,7,64,1),(78,7,65,1),(79,7,66,1);
/*!40000 ALTER TABLE `equipo_in_liga` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:58
