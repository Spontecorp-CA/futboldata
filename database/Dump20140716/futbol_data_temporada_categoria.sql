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
-- Table structure for table `temporada_categoria`
--

DROP TABLE IF EXISTS `temporada_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporada_categoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `temporada_id` int(10) unsigned NOT NULL,
  `categoria_id` int(10) unsigned NOT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temporada_has_categoria_categoria1_idx` (`categoria_id`),
  KEY `fk_temporada_has_categoria_temporada1_idx` (`temporada_id`),
  CONSTRAINT `fk_temporada_has_categoria_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_temporada_has_categoria_temporada1` FOREIGN KEY (`temporada_id`) REFERENCES `temporada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporada_categoria`
--

LOCK TABLES `temporada_categoria` WRITE;
/*!40000 ALTER TABLE `temporada_categoria` DISABLE KEYS */;
INSERT INTO `temporada_categoria` VALUES (36,9,4,NULL,NULL),(37,9,2,NULL,NULL),(40,1,8,NULL,NULL),(41,3,9,NULL,NULL),(45,2,8,NULL,NULL),(46,8,8,NULL,NULL),(47,11,8,NULL,NULL),(48,12,8,NULL,NULL),(49,15,8,NULL,NULL),(50,16,4,NULL,NULL),(51,16,2,NULL,NULL),(52,13,3,NULL,NULL),(55,13,4,NULL,NULL),(56,13,2,NULL,NULL),(57,4,2,NULL,NULL),(58,4,9,NULL,NULL),(59,4,8,NULL,NULL),(60,14,4,NULL,NULL),(61,14,2,NULL,NULL),(64,4,1,NULL,NULL),(69,17,9,NULL,NULL),(70,17,8,NULL,NULL),(74,10,7,NULL,NULL),(75,10,13,NULL,NULL),(76,10,12,NULL,NULL),(77,10,11,NULL,NULL),(78,10,14,NULL,NULL);
/*!40000 ALTER TABLE `temporada_categoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:38:27
