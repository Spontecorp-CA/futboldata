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
-- Table structure for table `partido_evento`
--

DROP TABLE IF EXISTS `partido_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_evento` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `partido_id` int(10) unsigned NOT NULL,
  `evento_id` int(10) unsigned NOT NULL,
  `minuto` int(11) DEFAULT NULL,
  `jugador1_id` int(10) unsigned DEFAULT NULL,
  `jugador2_id` int(10) unsigned DEFAULT NULL,
  `staff_id` int(10) unsigned DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`),
  KEY `fk_partido_has_evento_evento1_idx` (`evento_id`),
  KEY `fk_partido_has_evento_partido1_idx` (`partido_id`),
  KEY `fk_partido_evento_jugador1_idx` (`jugador1_id`),
  KEY `fk_partido_evento_jugador2_idx` (`jugador2_id`),
  KEY `fk_partido_evento_staff1_idx` (`staff_id`),
  CONSTRAINT `fk_partido_evento_jugador1` FOREIGN KEY (`jugador1_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_jugador2` FOREIGN KEY (`jugador2_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_evento_evento1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_evento_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_evento`
--

LOCK TABLES `partido_evento` WRITE;
/*!40000 ALTER TABLE `partido_evento` DISABLE KEYS */;
INSERT INTO `partido_evento` VALUES (6,32,1,95,26,NULL,NULL,NULL),(7,32,1,15,NULL,NULL,32,NULL),(12,1,6,62,26,NULL,NULL,NULL),(13,1,7,62,38,NULL,NULL,NULL),(14,11,4,9,22,NULL,NULL,NULL),(15,11,6,72,26,NULL,NULL,NULL),(16,11,7,72,38,NULL,NULL,NULL),(17,24,4,41,13,NULL,NULL,NULL),(21,4,1,12,40,NULL,NULL,NULL),(22,4,1,12,40,NULL,NULL,NULL),(23,4,1,40,43,NULL,NULL,NULL),(24,4,1,60,46,NULL,NULL,NULL),(25,4,5,66,47,NULL,NULL,NULL),(26,24,2,43,24,NULL,NULL,NULL),(27,24,2,80,24,NULL,NULL,NULL),(28,24,1,80,24,NULL,NULL,NULL),(31,1,6,62,49,NULL,NULL,NULL),(32,1,7,62,54,NULL,NULL,NULL),(33,1,5,57,45,NULL,NULL,NULL),(34,32,2,44,NULL,NULL,25,NULL),(35,32,1,44,26,NULL,25,'Le dio una patada A Juan'),(36,32,5,40,22,NULL,NULL,'Error que le costo el partido'),(38,1,2,43,33,NULL,NULL,''),(39,1,2,80,33,NULL,NULL,''),(40,1,1,80,33,NULL,NULL,''),(41,11,6,0,22,NULL,NULL,'Se partio la pierna'),(42,24,6,61,4,NULL,NULL,''),(43,1,4,57,30,NULL,NULL,'Penalti'),(46,1,4,64,35,NULL,NULL,'Jugada Colectiva'),(47,1,4,76,21,NULL,NULL,'Jugada Colectiva'),(48,1,9,64,35,NULL,NULL,'Jugada por banda izquierda'),(49,1,4,29,57,NULL,NULL,'Fallo Defensivo'),(50,1,4,63,55,NULL,NULL,'Jugada por banda derecha'),(51,1,3,1,22,NULL,NULL,'Por golpear un contrario'),(52,1,9,57,22,NULL,NULL,'A pelota parada'),(54,1,11,79,48,NULL,NULL,'Habilita y Gol de Rivas'),(55,1,10,18,35,NULL,NULL,'A pase filtrado de Rómulo Otero'),(56,1,6,86,58,NULL,NULL,'Tenía una amarilla'),(57,1,7,86,59,NULL,NULL,'Por protección al once en cancha'),(58,1,3,58,40,NULL,NULL,'Por Insultar al Árbitro'),(59,1,3,64,NULL,NULL,23,'Por insultar al 4to árbitro'),(60,1,2,90,36,NULL,NULL,'Reclamar al Árbitro');
/*!40000 ALTER TABLE `partido_evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:23
