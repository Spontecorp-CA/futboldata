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
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_en_grupo`
--

LOCK TABLES `equipo_en_grupo` WRITE;
/*!40000 ALTER TABLE `equipo_en_grupo` DISABLE KEYS */;
INSERT INTO `equipo_en_grupo` VALUES (20,9,29),(23,24,30),(24,22,30),(25,21,30),(26,23,30),(27,27,31),(28,26,31),(29,25,31),(30,28,31),(31,29,32),(32,30,32),(33,32,32),(34,31,32),(35,35,33),(36,33,33),(37,36,33),(38,34,33),(39,37,34),(40,40,34),(41,38,34),(42,39,34),(43,41,35),(44,51,35),(45,50,35),(46,49,35),(47,46,37),(48,48,37),(49,45,37),(50,47,37),(51,44,36),(52,42,36),(53,52,36),(54,43,36),(101,3,38),(102,55,38),(103,53,38),(104,57,38),(109,60,39),(110,64,39),(111,66,39),(112,62,39),(166,6,2),(167,14,2),(168,20,2),(169,18,2),(170,15,2),(171,9,2),(172,17,2),(173,5,2),(174,8,2),(175,12,2),(176,7,2),(177,11,2),(178,19,2),(179,16,2),(180,4,2),(181,13,2),(182,10,2),(183,1,2),(184,1,3),(185,4,3),(294,130,7),(295,128,7),(296,127,7),(297,131,7),(298,126,7),(299,129,7),(300,73,6),(301,72,6),(302,70,6),(303,67,6),(304,71,6),(305,68,6),(306,69,6),(317,7,1),(318,5,1),(319,6,1),(321,4,1),(323,3,1),(326,9,3),(329,10,3),(330,3,3),(333,7,3),(336,1,1),(337,8,3);
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

-- Dump completed on 2014-07-29 11:20:49
