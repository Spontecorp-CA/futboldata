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
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `persona_id` int(10) unsigned NOT NULL,
  `posicion_id` int(10) unsigned DEFAULT NULL,
  `camiseta` int(11) DEFAULT NULL,
  `zurdo` int(11) DEFAULT NULL COMMENT 'zurdo: 0, para derecho; 1; para zurdo; 2 ambidiestro',
  `altura` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `agente_id` int(10) unsigned DEFAULT NULL,
  `categoria_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_persona1_idx` (`persona_id`),
  KEY `fk_jugador_posicion1_idx` (`posicion_id`),
  KEY `fk_jugador_agente1_idx` (`agente_id`),
  KEY `fk_jugador_categoria1_idx` (`categoria_id`),
  CONSTRAINT `fk_jugador_agente1` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_posicion1` FOREIGN KEY (`posicion_id`) REFERENCES `posicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,11,8,18,1,1.72,62,1,NULL,8),(2,12,7,8,1,1.78,87,1,NULL,8),(3,13,6,4,1,1.86,84,1,NULL,8),(4,14,2,0,1,1.79,79,1,NULL,4),(13,29,8,10,0,1.78,70,1,NULL,8),(18,37,1,14,1,0,0,0,NULL,8),(19,38,11,5,1,1.79,84,1,NULL,8),(20,39,7,879,1,1.79,74,1,NULL,8),(21,40,8,18,0,1.75,70,1,NULL,8),(22,47,2,1,1,1.86,72,1,NULL,8),(23,48,9,24,1,1.86,72,1,NULL,8),(24,49,8,17,1,1.86,72,1,NULL,8),(26,51,3,6,0,1.86,78,1,NULL,8),(27,52,4,2,1,1.78,70,1,NULL,8),(28,53,6,4,1,1.82,72,1,NULL,8),(29,54,6,24,1,1.8,80,1,NULL,8),(30,55,8,13,1,1.69,59,1,NULL,8),(31,56,7,15,1,1.83,75,1,NULL,8),(32,57,8,18,2,1.69,60,1,NULL,8),(33,58,8,17,0,1.79,70,1,NULL,8),(34,59,1,9,1,1.81,75,1,NULL,8),(35,60,1,27,1,1.8,71,1,NULL,8),(36,73,2,1,1,1.86,86,1,NULL,4),(37,75,2,1,1,1.86,80,1,NULL,NULL),(38,76,3,19,0,1.78,68,1,NULL,8),(39,77,1,11,1,1.73,70,1,NULL,4),(40,88,2,22,1,1.89,86,1,NULL,NULL),(41,89,10,2,1,1.7,73,1,NULL,NULL),(42,90,10,4,1,1.88,84,1,NULL,NULL),(43,91,10,21,0,1.85,74,1,NULL,NULL),(44,92,10,28,1,1.79,80,1,NULL,NULL),(45,93,10,29,1,1.87,86,1,NULL,NULL),(46,94,11,5,1,1.79,82,1,NULL,NULL),(47,95,11,6,1,1.84,84,1,NULL,NULL),(48,96,11,10,0,1.64,63,1,NULL,NULL),(49,97,11,11,1,1.78,77,1,NULL,NULL),(50,98,11,14,0,1.75,67,1,NULL,NULL),(51,99,11,20,0,1.63,68,1,NULL,NULL),(52,100,11,23,1,1.75,70,1,NULL,NULL),(53,101,11,24,0,1.72,68,1,NULL,NULL),(54,102,11,32,1,1.72,67,1,NULL,NULL),(55,103,1,7,1,1.65,72,1,NULL,NULL),(56,104,1,8,1,1.74,79,1,NULL,NULL),(57,105,1,9,1,1.82,84,1,NULL,NULL),(58,106,1,18,1,1.71,69,1,NULL,8),(59,107,1,18,1,1.76,73,1,NULL,4),(60,108,2,1,1,181,87,1,NULL,8),(61,109,1,12,1,182,82,1,NULL,8),(64,112,6,4,1,1.84,80,1,NULL,8),(65,113,6,5,1,1.87,79,1,NULL,8),(66,114,3,14,1,1.91,81,1,NULL,8);
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:21:19