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
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text,
  `status` int(11) DEFAULT NULL,
  `tipo_evento_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_evento_tipo_evento1_idx` (`tipo_evento_id`),
  CONSTRAINT `fk_evento_tipo_evento1` FOREIGN KEY (`tipo_evento_id`) REFERENCES `tipo_evento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'Roja','Consecuencia de la doble amarilla',1,1),(2,'Amarilla','Es para indicar que un jugador ha sido oficialmente amonestado.',1,1),(3,'Roja Directa','Una tarjeta roja mostrada por el árbitro indica que un jugador ha sido expulsado de forma directa.',1,1),(4,'Gol','Un gol o tanto se marca después de que la pelota cruce completamente la línea de meta o línea de gol de una portería.',1,1),(5,'Autogol','Es cuando un jugador introduce la pelota en la portería de su propio equipo.',1,1),(6,'Sale','Es cuando un jugador es sustituido por otro jugador en el terreno de juego.',1,1),(7,'Entra','Es cuando un jugador es sustituido por otro jugador en el terreno de juego.',1,1),(8,'Gol De Tiro Libre','Pelota quieta',1,2),(9,'Gol de cabeza','',1,2),(10,'Gol con el pie','',1,2),(11,'Pase de Gol','',1,2),(12,'Pase Filtrado','',1,2),(13,'Tiros de Esquina','',1,3),(14,'Faltas','',1,3);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:59
