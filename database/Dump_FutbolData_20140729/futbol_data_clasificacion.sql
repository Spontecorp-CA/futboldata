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
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jornada_id` int(10) unsigned NOT NULL,
  `equipo_id` int(10) unsigned NOT NULL,
  `partido_id` int(10) unsigned NOT NULL,
  `clasificacion_grupo_id` int(10) unsigned NOT NULL,
  `j_jugados` int(11) DEFAULT NULL,
  `j_ganados` int(11) DEFAULT NULL,
  `j_empatados` int(11) DEFAULT NULL,
  `j_perdidos` int(11) DEFAULT NULL,
  `goles_favor` int(11) DEFAULT NULL,
  `goles_contra` int(11) DEFAULT NULL,
  `diferencia` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  `is_local` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clasificacion_jornada1_idx` (`jornada_id`),
  KEY `fk_clasificacion_equipo1_idx` (`equipo_id`),
  KEY `fk_clasificacion_partido1_idx` (`partido_id`),
  KEY `fk_clasificacion_clasificacion_grupo1_idx` (`clasificacion_grupo_id`),
  CONSTRAINT `fk_clasificacion_clasificacion_grupo1` FOREIGN KEY (`clasificacion_grupo_id`) REFERENCES `clasificacion_grupo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_jornada1` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
INSERT INTO `clasificacion` VALUES (5,1,1,1,9,1,1,0,0,3,2,1,3,1,NULL),(6,1,8,1,10,1,0,0,1,2,3,-1,0,0,NULL),(7,1,7,4,11,1,1,0,0,4,3,1,3,1,NULL),(8,1,8,4,10,1,0,0,1,3,4,-1,0,0,NULL),(9,4,9,79,12,1,1,0,0,3,0,3,3,1,NULL),(10,4,10,79,13,1,0,0,1,0,3,-3,0,0,NULL),(11,4,4,80,14,1,0,1,0,1,1,0,1,1,NULL),(12,4,6,80,15,1,0,1,0,1,1,0,1,0,NULL),(13,74,42,65,16,1,0,0,1,0,1,-1,0,1,NULL),(14,74,52,65,17,1,1,0,0,1,0,1,3,0,NULL),(15,74,44,66,18,1,1,0,0,2,1,1,3,1,NULL),(16,74,43,66,19,1,0,0,1,1,2,-1,0,0,NULL),(17,56,23,53,20,1,0,0,1,1,4,-3,0,1,NULL),(18,56,21,53,21,1,1,0,0,4,1,3,3,0,NULL),(19,56,24,54,22,1,0,0,1,1,3,-2,0,1,NULL),(20,56,22,54,23,1,1,0,0,3,1,2,3,0,NULL),(21,54,22,13,23,1,1,0,0,1,0,1,3,1,NULL),(22,54,23,13,20,1,0,0,1,0,1,-1,0,0,NULL),(23,54,21,12,21,1,1,0,0,3,1,2,3,1,NULL),(24,54,24,12,22,1,0,0,1,1,3,-2,0,0,NULL),(25,55,23,38,20,1,0,0,1,0,4,-4,0,1,NULL),(26,55,24,38,22,1,1,0,0,4,0,4,3,0,NULL),(27,55,21,37,21,1,0,1,0,0,0,0,1,1,NULL),(28,55,22,37,23,1,0,1,0,0,0,0,1,0,NULL),(29,57,28,14,24,1,0,0,1,1,5,-4,0,1,NULL),(30,57,25,14,25,1,1,0,0,5,1,4,3,0,NULL),(31,57,26,15,26,1,1,0,0,3,1,2,3,1,NULL),(32,57,27,15,27,1,0,0,1,1,3,-2,0,0,NULL),(33,58,27,39,27,1,0,0,1,2,3,-1,0,1,NULL),(34,58,25,39,25,1,1,0,0,3,2,1,3,0,NULL),(35,58,28,40,24,1,0,0,1,0,2,-2,0,1,NULL),(36,58,26,40,26,1,1,0,0,2,0,2,3,0,NULL),(37,59,27,55,27,1,0,0,1,0,3,-3,0,1,NULL),(38,59,28,55,24,1,1,0,0,3,0,3,3,0,NULL),(39,59,25,56,25,1,1,0,0,2,0,2,3,1,NULL),(40,59,26,56,26,1,0,0,1,0,2,-2,0,0,NULL),(41,60,29,16,28,1,1,0,0,3,0,3,3,1,NULL),(42,60,32,16,29,1,0,0,1,0,3,-3,0,0,NULL),(43,60,30,17,30,1,1,0,0,2,1,1,3,1,NULL),(44,60,31,17,31,1,0,0,1,1,2,-1,0,0,NULL),(45,61,29,41,28,1,1,0,0,2,1,1,3,1,NULL),(46,61,30,41,30,1,0,0,1,1,2,-1,0,0,NULL),(47,61,31,42,31,1,0,1,0,0,0,0,1,1,NULL),(48,61,32,42,29,1,0,1,0,0,0,0,1,0,NULL),(49,62,31,57,31,1,0,0,1,1,4,-3,0,1,NULL),(50,62,29,57,28,1,1,0,0,4,1,3,3,0,NULL),(51,62,32,58,29,1,1,0,0,2,1,1,3,1,NULL),(52,62,30,58,30,1,0,0,1,1,2,-1,0,0,NULL),(53,63,36,18,32,1,0,0,1,1,3,-2,0,1,NULL),(54,63,33,18,33,1,1,0,0,3,1,2,3,0,NULL),(55,63,35,19,34,1,0,0,1,1,2,-1,0,1,NULL),(56,63,34,19,35,1,1,0,0,2,1,1,3,0,NULL),(57,64,34,44,35,1,0,0,1,0,1,-1,0,1,NULL),(58,64,33,44,33,1,1,0,0,1,0,1,3,0,NULL),(59,64,36,43,32,1,1,0,0,2,1,1,3,1,NULL),(60,64,35,43,34,1,0,0,1,1,2,-1,0,0,NULL),(61,65,34,59,35,1,0,0,1,0,1,-1,0,1,NULL),(62,65,36,59,32,1,1,0,0,1,0,1,3,0,NULL),(63,65,33,60,33,1,0,1,0,0,0,0,1,1,NULL),(64,65,35,60,34,1,0,1,0,0,0,0,1,0,NULL),(65,66,38,20,36,1,1,0,0,2,1,1,3,1,NULL),(66,66,39,20,37,1,0,0,1,1,2,-1,0,0,NULL),(67,66,37,21,38,1,1,0,0,3,0,3,3,1,NULL),(68,66,40,21,39,1,0,0,1,0,3,-3,0,0,NULL),(69,67,38,45,36,1,0,0,1,2,5,-3,0,1,NULL),(70,67,37,45,38,1,1,0,0,5,2,3,3,0,NULL),(71,67,40,46,39,1,0,0,1,1,2,-1,0,1,NULL),(72,67,39,46,37,1,1,0,0,2,1,1,3,0,NULL),(73,68,40,61,39,1,0,0,1,0,3,-3,0,1,NULL),(74,68,38,61,36,1,1,0,0,3,0,3,3,0,NULL),(75,68,39,62,37,1,0,1,0,0,0,0,1,1,NULL),(76,68,37,62,38,1,0,1,0,0,0,0,1,0,NULL),(77,69,50,23,40,1,0,1,0,0,0,0,1,1,NULL),(78,69,51,23,41,1,0,1,0,0,0,0,1,0,NULL),(79,69,41,22,42,1,1,0,0,2,1,1,3,1,NULL),(80,69,49,22,43,1,0,0,1,1,2,-1,0,0,NULL),(81,70,41,47,42,1,1,0,0,1,0,1,3,1,NULL),(82,70,50,47,40,1,0,0,1,0,1,-1,0,0,NULL),(83,70,51,48,41,1,1,0,0,1,0,1,3,1,NULL),(84,70,49,48,43,1,0,0,1,0,1,-1,0,0,NULL),(85,71,51,63,41,1,0,0,1,2,3,-1,0,1,NULL),(86,71,41,63,42,1,1,0,0,3,2,1,3,0,NULL),(87,71,49,64,43,1,1,0,0,3,1,2,3,1,NULL),(88,71,50,64,40,1,0,0,1,1,3,-2,0,0,NULL),(89,72,52,33,17,1,1,0,0,4,0,4,3,1,NULL),(90,72,44,33,18,1,0,0,1,0,4,-4,0,0,NULL),(91,72,43,34,19,1,0,0,1,1,2,-1,0,1,NULL),(92,72,42,34,16,1,1,0,0,2,1,1,3,0,NULL),(93,73,42,50,16,1,0,1,0,2,2,0,1,1,NULL),(94,73,44,50,18,1,0,1,0,2,2,0,1,0,NULL),(95,73,52,49,17,1,0,1,0,2,2,0,1,1,NULL),(96,73,43,49,19,1,0,1,0,2,2,0,1,0,NULL),(97,75,45,35,44,1,1,0,0,2,1,1,3,1,NULL),(98,75,48,35,45,1,0,0,1,1,2,-1,0,0,NULL),(99,75,47,36,46,1,0,1,0,1,1,0,1,1,NULL),(100,75,46,36,47,1,0,1,0,1,1,0,1,0,NULL),(101,76,45,51,44,1,1,0,0,1,0,1,3,1,NULL),(102,76,47,51,46,1,0,0,1,0,1,-1,0,0,NULL),(103,76,46,52,47,1,0,0,1,2,4,-2,0,1,NULL),(104,76,48,52,45,1,1,0,0,4,2,2,3,0,NULL),(105,77,46,67,47,1,0,0,1,0,1,-1,0,1,NULL),(106,77,45,67,44,1,1,0,0,1,0,1,3,0,NULL),(107,77,48,68,45,1,0,1,0,1,1,0,1,1,NULL),(108,77,47,68,46,1,0,1,0,1,1,0,1,0,NULL),(109,32,73,81,48,1,0,0,1,0,5,-5,0,1,NULL),(110,32,67,81,49,1,1,0,0,5,0,5,3,0,NULL),(111,32,70,82,50,1,0,0,1,1,2,-1,0,1,NULL),(112,32,68,82,51,1,1,0,0,2,1,1,3,0,NULL),(113,32,69,83,52,1,0,1,0,1,1,0,1,1,NULL),(114,32,71,83,53,1,0,1,0,1,1,0,1,0,NULL),(115,33,79,84,54,1,0,0,1,0,1,-1,0,1,NULL),(116,33,78,84,55,1,1,0,0,1,0,1,3,0,NULL),(117,5,1,11,56,1,1,0,0,3,0,3,3,1,NULL),(118,5,14,11,57,1,0,0,1,0,3,-3,0,0,NULL),(119,5,8,24,60,1,1,0,0,2,1,1,3,1,NULL),(120,5,5,24,61,1,0,0,1,1,2,-1,0,0,NULL),(121,5,15,25,64,1,1,0,0,2,1,1,3,1,NULL),(122,5,7,25,65,1,0,0,1,1,2,-1,0,0,NULL),(123,5,12,26,68,1,0,1,0,2,2,0,1,1,NULL),(124,5,13,26,69,1,0,1,0,2,2,0,1,0,NULL),(125,5,4,27,72,1,1,0,0,3,2,1,3,1,NULL),(126,5,11,27,73,1,0,0,1,2,3,-1,0,0,NULL),(127,5,10,28,76,1,1,0,0,2,1,1,3,1,NULL),(128,5,19,28,77,1,0,0,1,1,2,-1,0,0,NULL),(129,5,18,29,80,1,0,0,1,1,2,-1,0,1,NULL),(130,5,6,29,81,1,1,0,0,2,1,1,3,0,NULL),(131,5,17,30,84,1,1,0,0,2,0,2,3,1,NULL),(132,5,9,30,85,1,0,0,1,0,2,-2,0,0,NULL),(133,5,20,31,88,1,0,1,0,1,1,0,1,1,NULL),(134,5,16,31,89,1,0,1,0,1,1,0,1,0,NULL),(135,6,13,173,92,1,0,0,1,0,3,-3,0,1,NULL),(136,6,1,173,93,1,1,0,0,3,0,3,3,0,NULL),(137,6,11,174,96,1,0,0,1,1,2,-1,0,1,NULL),(138,6,12,174,97,1,1,0,0,2,1,1,3,0,NULL),(139,6,14,175,100,1,1,0,0,2,1,1,3,1,NULL),(140,6,15,175,101,1,0,0,1,1,2,-1,0,0,NULL),(141,6,4,176,104,1,1,0,0,2,1,1,3,1,NULL),(142,6,20,176,105,1,0,0,1,1,2,-1,0,0,NULL),(143,6,16,177,108,1,0,1,0,2,2,0,1,1,NULL),(144,6,8,177,109,1,0,1,0,2,2,0,1,0,NULL),(145,6,5,178,112,1,1,0,0,1,0,1,3,1,NULL),(146,6,17,178,113,1,0,0,1,0,1,-1,0,0,NULL),(147,6,9,179,116,1,0,0,1,0,1,-1,0,1,NULL),(148,6,18,179,117,1,1,0,0,1,0,1,3,0,NULL),(149,6,6,180,120,1,1,0,0,1,0,1,3,1,NULL),(150,6,19,180,121,1,0,0,1,0,1,-1,0,0,NULL),(151,6,7,74,124,1,0,1,0,3,3,0,1,1,NULL),(152,6,10,74,125,1,0,1,0,3,3,0,1,0,NULL),(153,7,15,182,128,1,1,0,0,2,1,1,3,1,NULL),(154,7,13,182,129,1,0,0,1,1,2,-1,0,0,NULL),(155,7,12,183,134,1,0,1,0,2,2,0,1,1,NULL),(156,7,4,183,135,1,0,1,0,2,2,0,1,0,NULL),(157,7,7,184,138,1,1,0,0,3,2,1,3,1,NULL),(158,7,6,184,139,1,0,0,1,2,3,-1,0,0,NULL),(159,7,19,185,142,1,1,0,0,3,2,1,3,1,NULL),(160,7,9,185,143,1,0,0,1,2,3,-1,0,0,NULL),(161,7,18,186,146,1,0,0,1,1,3,-2,0,1,NULL),(162,7,5,186,147,1,1,0,0,3,1,2,3,0,NULL),(163,7,17,187,150,1,0,0,1,1,2,-1,0,1,NULL),(164,7,16,187,151,1,1,0,0,2,1,1,3,0,NULL),(165,7,8,188,154,1,0,1,0,1,1,0,1,1,NULL),(166,7,20,188,155,1,0,1,0,1,1,0,1,0,NULL),(167,7,10,181,158,1,0,1,0,0,0,0,1,1,NULL),(168,7,14,181,159,1,0,1,0,0,0,0,1,0,NULL),(169,7,1,76,162,1,1,0,0,3,0,3,3,1,NULL),(170,7,11,76,163,1,0,0,1,0,3,-3,0,0,NULL),(171,8,13,197,166,1,1,0,0,1,0,1,3,1,NULL),(172,8,10,197,167,1,0,0,1,0,1,-1,0,0,NULL),(173,8,4,189,170,1,0,0,1,0,2,-2,0,1,NULL),(174,8,1,189,171,1,1,0,0,2,0,2,3,0,NULL),(175,8,11,190,174,1,0,0,1,1,3,-2,0,1,NULL),(176,8,15,190,175,1,1,0,0,3,1,2,3,0,NULL),(177,8,14,191,178,1,0,0,1,0,2,-2,0,1,NULL),(178,8,7,191,179,1,1,0,0,2,0,2,3,0,NULL),(179,8,12,192,182,1,1,0,0,1,0,1,3,1,NULL),(180,8,8,192,183,1,0,0,1,0,1,-1,0,0,NULL),(181,8,20,193,186,1,0,1,0,1,1,0,1,1,NULL),(182,8,17,193,187,1,0,1,0,1,1,0,1,0,NULL),(183,8,16,194,190,1,0,1,0,0,0,0,1,1,NULL),(184,8,18,194,191,1,0,1,0,0,0,0,1,0,NULL),(185,8,5,195,194,1,1,0,0,2,1,1,3,1,NULL),(186,8,19,195,195,1,0,0,1,1,2,-1,0,0,NULL),(187,8,9,196,198,1,0,1,0,1,1,0,1,1,NULL),(188,8,6,196,199,1,0,1,0,1,1,0,1,0,NULL),(189,32,112,82,202,1,0,0,1,0,4,-4,0,1,NULL),(190,32,106,82,203,1,1,0,0,4,0,4,3,0,NULL),(191,32,125,83,206,1,0,0,1,0,2,-2,0,1,NULL),(192,32,119,83,207,1,1,0,0,2,0,2,3,0,NULL),(193,32,86,87,212,1,0,1,0,1,1,0,1,1,NULL),(194,32,80,87,213,1,0,1,0,1,1,0,1,0,NULL),(195,32,70,88,216,1,0,0,1,1,2,-1,0,1,NULL),(196,32,68,88,217,1,1,0,0,2,1,1,3,0,NULL),(197,32,83,89,220,1,0,0,1,0,1,-1,0,1,NULL),(198,32,81,89,221,1,1,0,0,1,0,1,3,0,NULL),(199,32,99,93,224,1,0,1,0,1,1,0,1,1,NULL),(200,32,93,93,225,1,0,1,0,1,1,0,1,0,NULL),(201,32,96,94,228,1,0,0,1,0,1,-1,0,1,NULL),(202,32,94,94,229,1,1,0,0,1,0,1,3,0,NULL),(203,32,109,95,232,1,0,0,1,0,3,-3,0,1,NULL),(204,32,107,95,233,1,1,0,0,3,0,3,3,0,NULL),(205,32,122,96,236,1,1,0,0,3,1,2,3,1,NULL),(206,32,120,96,237,1,0,0,1,1,3,-2,0,0,NULL),(207,32,69,97,240,1,0,1,0,1,1,0,1,1,NULL),(208,32,71,97,241,1,0,1,0,1,1,0,1,0,NULL),(209,32,82,98,244,1,0,0,1,0,1,-1,0,1,NULL),(210,32,84,98,245,1,1,0,0,1,0,1,3,0,NULL),(211,32,95,99,248,1,1,0,0,1,0,1,3,1,NULL),(212,32,97,99,249,1,0,0,1,0,1,-1,0,0,NULL),(213,32,108,100,252,1,1,0,0,2,1,1,3,1,NULL),(214,32,110,100,253,1,0,0,1,1,2,-1,0,0,NULL),(215,32,121,101,256,1,1,0,0,3,0,3,3,1,NULL),(216,32,123,101,257,1,0,0,1,0,3,-3,0,0,NULL),(217,34,72,111,260,1,0,0,1,1,5,-4,0,1,NULL),(218,34,68,111,261,1,1,0,0,5,1,4,3,0,NULL),(219,34,85,112,264,1,1,0,0,2,0,2,3,1,NULL),(220,34,81,112,265,1,0,0,1,0,2,-2,0,0,NULL),(221,34,98,113,268,1,0,0,1,0,2,-2,0,1,NULL),(222,34,94,113,269,1,1,0,0,2,0,2,3,0,NULL),(223,34,111,114,272,1,0,1,0,2,2,0,1,1,NULL),(224,34,107,114,273,1,0,1,0,2,2,0,1,0,NULL),(225,34,124,115,276,1,0,0,1,0,3,-3,0,1,NULL),(226,34,120,115,277,1,1,0,0,3,0,3,3,0,NULL),(227,34,69,116,280,1,1,0,0,1,0,1,3,1,NULL),(228,34,70,116,281,1,0,0,1,0,1,-1,0,0,NULL),(229,34,82,117,284,1,0,1,0,0,0,0,1,1,NULL),(230,34,83,117,285,1,0,1,0,0,0,0,1,0,NULL),(231,34,95,118,288,1,1,0,0,2,0,2,3,1,NULL),(232,34,96,118,289,1,0,0,1,0,2,-2,0,0,NULL),(233,34,108,119,292,1,1,0,0,3,2,1,3,1,NULL),(234,34,109,119,293,1,0,0,1,2,3,-1,0,0,NULL),(235,34,121,120,296,1,0,1,0,0,0,0,1,1,NULL),(236,34,122,120,297,1,0,1,0,0,0,0,1,0,NULL),(237,34,71,121,300,1,1,0,0,10,2,8,3,1,NULL),(238,34,73,121,301,1,0,0,1,2,10,-8,0,0,NULL),(239,34,84,122,304,1,1,0,0,2,0,2,3,1,NULL),(240,34,86,122,305,1,0,0,1,0,2,-2,0,0,NULL),(241,34,97,123,308,1,0,1,0,1,1,0,1,1,NULL),(242,34,99,123,309,1,0,1,0,1,1,0,1,0,NULL),(243,83,67,142,312,1,1,0,0,5,2,3,3,1,NULL),(244,83,68,142,313,1,0,0,1,2,5,-3,0,0,NULL),(245,83,80,143,316,1,1,0,0,2,0,2,3,1,NULL),(246,83,81,143,317,1,0,0,1,0,2,-2,0,0,NULL),(247,83,93,144,320,1,1,0,0,3,2,1,3,1,NULL),(248,83,94,144,321,1,0,0,1,2,3,-1,0,0,NULL),(249,83,106,145,324,1,1,0,0,3,2,1,3,1,NULL),(250,83,107,145,325,1,0,0,1,2,3,-1,0,0,NULL),(251,83,119,146,328,1,1,0,0,2,0,2,3,1,NULL),(252,83,120,146,329,1,0,0,1,0,2,-2,0,0,NULL),(253,83,72,147,332,1,0,1,0,0,0,0,1,1,NULL),(254,83,69,147,333,1,0,1,0,0,0,0,1,0,NULL),(255,83,85,148,336,1,1,0,0,1,0,1,3,1,NULL),(256,83,82,148,337,1,0,0,1,0,1,-1,0,0,NULL),(257,83,98,149,340,1,0,0,1,2,3,-1,0,1,NULL),(258,83,95,149,341,1,1,0,0,3,2,1,3,0,NULL),(259,83,111,150,344,1,0,0,1,0,4,-4,0,1,NULL),(260,83,108,150,345,1,1,0,0,4,0,4,3,0,NULL),(261,83,124,151,348,1,0,0,1,0,3,-3,0,1,NULL),(262,83,121,151,349,1,1,0,0,3,0,3,3,0,NULL),(263,83,70,152,352,1,1,0,0,6,0,6,3,1,NULL),(264,83,73,152,353,1,0,0,1,0,6,-6,0,0,NULL),(265,83,83,153,356,1,0,0,1,1,2,-1,0,1,NULL),(266,83,86,153,357,1,1,0,0,2,1,1,3,0,NULL),(267,83,96,154,360,1,1,0,0,2,1,1,3,1,NULL),(268,83,99,154,361,1,0,0,1,1,2,-1,0,0,NULL),(269,83,109,155,364,1,0,1,0,2,2,0,1,1,NULL),(270,83,112,155,365,1,0,1,0,2,2,0,1,0,NULL),(271,83,122,156,368,1,1,0,0,1,0,1,3,1,NULL),(272,83,125,156,369,1,0,0,1,0,1,-1,0,0,NULL),(273,33,76,85,374,1,1,0,0,2,0,2,3,1,NULL),(274,33,77,85,375,1,0,0,1,0,2,-2,0,0,NULL),(275,33,74,86,378,1,0,0,1,5,6,-1,0,1,NULL),(276,33,75,86,379,1,1,0,0,6,5,1,3,0,NULL),(277,33,92,90,382,1,1,0,0,5,0,5,3,1,NULL),(278,33,91,90,383,1,0,0,1,0,5,-5,0,0,NULL),(279,33,89,91,386,1,1,0,0,2,1,1,3,1,NULL),(280,33,90,91,387,1,0,0,1,1,2,-1,0,0,NULL),(281,33,87,92,390,1,1,0,0,9,0,9,3,1,NULL),(282,33,88,92,391,1,0,0,1,0,9,-9,0,0,NULL),(283,33,102,102,394,1,1,0,0,3,2,1,3,1,NULL),(284,33,103,102,395,1,0,0,1,2,3,-1,0,0,NULL),(285,33,100,103,398,1,0,1,0,1,1,0,1,1,NULL),(286,33,101,103,399,1,0,1,0,1,1,0,1,0,NULL),(287,33,105,104,402,1,1,0,0,4,2,2,3,1,NULL),(288,33,104,104,403,1,0,0,1,2,4,-2,0,0,NULL),(289,33,115,105,406,1,1,0,0,1,0,1,3,1,NULL),(290,33,116,105,407,1,0,0,1,0,1,-1,0,0,NULL),(291,33,113,106,410,1,0,0,1,0,2,-2,0,1,NULL),(292,33,114,106,411,1,1,0,0,2,0,2,3,0,NULL),(293,33,118,107,414,1,0,0,1,1,3,-2,0,1,NULL),(294,33,117,107,415,1,1,0,0,3,1,2,3,0,NULL),(295,33,128,108,418,1,0,1,0,1,1,0,1,1,NULL),(296,33,129,108,419,1,0,1,0,1,1,0,1,0,NULL),(297,33,126,109,422,1,1,0,0,2,0,2,3,1,NULL),(298,33,127,109,423,1,0,0,1,0,2,-2,0,0,NULL),(299,33,131,110,426,1,0,1,0,2,2,0,1,1,NULL),(300,33,130,110,427,1,0,1,0,2,2,0,1,0,NULL),(301,35,78,126,430,1,0,0,1,2,7,-5,0,1,NULL),(302,35,74,126,431,1,1,0,0,7,2,5,3,0,NULL),(303,35,91,127,434,1,0,0,1,2,3,-1,0,1,NULL),(304,35,87,127,435,1,1,0,0,3,2,1,3,0,NULL),(305,35,104,128,438,1,1,0,0,2,1,1,3,1,NULL),(306,35,100,128,439,1,0,0,1,1,2,-1,0,0,NULL),(307,35,117,129,442,1,0,1,0,1,1,0,1,1,NULL),(308,35,113,129,443,1,0,1,0,1,1,0,1,0,NULL),(309,35,130,130,446,1,1,0,0,3,2,1,3,1,NULL),(310,35,126,130,447,1,0,0,1,2,3,-1,0,0,NULL),(311,35,75,131,450,1,1,0,0,4,3,1,3,1,NULL),(312,35,76,131,451,1,0,0,1,3,4,-1,0,0,NULL),(313,35,88,132,454,1,0,0,1,2,5,-3,0,1,NULL),(314,35,89,132,455,1,1,0,0,5,2,3,3,0,NULL),(315,35,101,133,458,1,1,0,0,4,1,3,3,1,NULL),(316,35,102,133,459,1,0,0,1,1,4,-3,0,0,NULL),(317,35,114,134,462,1,0,0,1,1,2,-1,0,1,NULL),(318,35,115,134,463,1,1,0,0,2,1,1,3,0,NULL),(319,35,127,135,466,1,1,0,0,1,0,1,3,1,NULL),(320,35,128,135,467,1,0,0,1,0,1,-1,0,0,NULL),(321,35,77,136,470,1,1,0,0,1,0,1,3,1,NULL),(322,35,79,136,471,1,0,0,1,0,1,-1,0,0,NULL),(323,35,90,137,474,1,0,1,0,3,3,0,1,1,NULL),(324,35,92,137,475,1,0,1,0,3,3,0,1,0,NULL),(325,35,103,138,478,1,1,0,0,5,3,2,3,1,NULL),(326,35,105,138,479,1,0,0,1,3,5,-2,0,0,NULL),(327,35,116,139,482,1,0,0,1,0,1,-1,0,1,NULL),(328,35,118,139,483,1,1,0,0,1,0,1,3,0,NULL),(329,35,129,140,486,1,0,1,0,1,1,0,1,1,NULL),(330,35,131,140,487,1,0,1,0,1,1,0,1,0,NULL);
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:21:11