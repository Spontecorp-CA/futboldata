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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_in_liga`
--

LOCK TABLES `equipo_in_liga` WRITE;
/*!40000 ALTER TABLE `equipo_in_liga` DISABLE KEYS */;
INSERT INTO `equipo_in_liga` VALUES (1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,6,1),(7,1,8,1),(8,1,7,1),(9,1,9,1),(10,1,10,1),(11,1,11,1),(12,1,12,1),(13,1,13,1),(14,1,14,1),(15,1,15,1),(16,1,16,1),(17,1,17,1),(18,1,18,1),(19,1,19,1),(20,1,20,1),(21,2,21,1),(22,2,22,1),(23,2,23,1),(24,2,24,1),(25,2,25,1),(26,2,26,1),(27,2,27,1),(28,2,28,1),(29,2,29,1),(30,2,30,1),(31,2,31,1),(32,2,32,1),(33,2,33,1),(34,2,34,1),(35,2,35,1),(36,2,36,1),(37,2,37,1),(38,2,38,1),(39,2,39,1),(40,2,40,1);
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

-- Dump completed on 2014-08-14  9:23:13
