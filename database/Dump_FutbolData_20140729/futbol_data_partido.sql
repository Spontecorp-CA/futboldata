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
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `observaciones` text,
  `equipo_local_id` int(10) unsigned DEFAULT NULL,
  `equipo_visitante_id` int(10) unsigned DEFAULT NULL,
  `equipo_local_text` varchar(45) DEFAULT NULL,
  `equipo_visitante_text` varchar(45) DEFAULT NULL,
  `cancha_id` int(10) unsigned DEFAULT NULL,
  `tx_tv` int(11) DEFAULT NULL,
  `tx_radio` int(11) DEFAULT NULL,
  `asistencia` int(11) DEFAULT NULL,
  `status_partido_id` int(10) unsigned NOT NULL,
  `llave_id` int(10) unsigned DEFAULT NULL,
  `jornada_id` int(10) unsigned DEFAULT NULL,
  `goles_equipo_local` int(11) DEFAULT NULL,
  `goles_equipo_visitante` int(11) DEFAULT NULL,
  `goles_local_prorroga` int(11) DEFAULT NULL,
  `goles_visitante_prorroga` int(11) DEFAULT NULL,
  `goles_local_penalties` int(11) DEFAULT NULL,
  `goles_visitante_penalties` int(11) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `categoria_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_equipo1_idx` (`equipo_local_id`),
  KEY `fk_partido_equipo2_idx` (`equipo_visitante_id`),
  KEY `fk_partido_cancha1_idx` (`cancha_id`),
  KEY `fk_partido_status_partido1_idx` (`status_partido_id`),
  KEY `fk_partido_llave1_idx` (`llave_id`),
  KEY `fk_partido_jornada1_idx` (`jornada_id`),
  KEY `fk_partido_categoria1_idx` (`categoria_id`),
  CONSTRAINT `fk_partido_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_equipo1` FOREIGN KEY (`equipo_local_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_equipo2` FOREIGN KEY (`equipo_visitante_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_jornada1` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_llave1` FOREIGN KEY (`llave_id`) REFERENCES `llave` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_status_partido1` FOREIGN KEY (`status_partido_id`) REFERENCES `status_partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,'2014-06-26','16:00:00',NULL,NULL,1,8,NULL,NULL,1,NULL,NULL,NULL,9,NULL,1,3,2,NULL,NULL,0,0,1,8),(4,'2014-06-17','11:59:00',NULL,NULL,7,8,NULL,NULL,6,NULL,NULL,NULL,9,NULL,1,4,3,NULL,NULL,0,0,2,8),(5,'2015-06-21','17:00:00',NULL,NULL,NULL,NULL,'Campeón Apertura','Campeón Clausura',NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'2015-06-28','17:00:00',NULL,NULL,NULL,NULL,'Campeón Clausura','Campeón Apertura',NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'2014-06-28','11:30:00',NULL,NULL,21,26,'','',5,NULL,NULL,NULL,8,62,NULL,4,3,NULL,NULL,NULL,NULL,49,8),(8,'2014-06-28','15:30:00',NULL,NULL,29,36,'','',1,NULL,NULL,NULL,1,63,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,8),(9,'2014-06-29','11:30:00',NULL,NULL,25,22,'','',3,NULL,NULL,NULL,1,64,NULL,NULL,NULL,NULL,NULL,NULL,NULL,51,8),(10,'2014-06-29','15:30:00',NULL,NULL,33,32,'','',2,NULL,NULL,NULL,1,65,NULL,NULL,NULL,NULL,NULL,NULL,NULL,52,8),(11,'2014-08-10','16:00:00',NULL,NULL,1,14,NULL,NULL,1,NULL,NULL,NULL,9,NULL,5,3,0,NULL,NULL,0,0,1,8),(12,'2014-06-12','15:00:00',NULL,NULL,21,24,NULL,NULL,1,NULL,NULL,NULL,9,NULL,54,3,1,NULL,NULL,0,0,1,8),(13,'2014-06-13','11:30:00',NULL,NULL,22,23,NULL,NULL,1,NULL,NULL,NULL,9,NULL,54,1,0,NULL,NULL,0,0,2,8),(14,'2014-06-13','14:30:00',NULL,NULL,28,25,NULL,NULL,1,NULL,NULL,NULL,9,NULL,57,1,5,NULL,NULL,0,0,3,8),(15,'2014-06-13','17:30:00',NULL,NULL,26,27,NULL,NULL,1,NULL,NULL,NULL,9,NULL,57,3,1,NULL,NULL,0,0,4,8),(16,'2014-06-14','11:30:00',NULL,NULL,29,32,NULL,NULL,1,NULL,NULL,NULL,9,NULL,60,3,0,NULL,NULL,0,0,5,8),(17,'2014-06-14','20:30:00',NULL,NULL,30,31,NULL,NULL,1,NULL,NULL,NULL,9,NULL,60,2,1,NULL,NULL,0,0,6,8),(18,'2014-06-14','14:30:00',NULL,NULL,36,33,NULL,NULL,1,NULL,NULL,NULL,9,NULL,63,1,3,NULL,NULL,0,0,7,8),(19,'2014-06-14','17:30:00',NULL,NULL,35,34,NULL,NULL,1,NULL,NULL,NULL,9,NULL,63,1,2,NULL,NULL,0,0,8,8),(20,'2014-06-15','11:30:00',NULL,NULL,38,39,NULL,NULL,1,NULL,NULL,NULL,9,NULL,66,2,1,NULL,NULL,0,0,9,8),(21,'2014-06-15','14:30:00',NULL,NULL,37,40,NULL,NULL,1,NULL,NULL,NULL,9,NULL,66,3,0,NULL,NULL,0,0,10,8),(22,'2014-06-15','17:30:00',NULL,NULL,41,49,NULL,NULL,1,NULL,NULL,NULL,9,NULL,69,2,1,NULL,NULL,0,0,11,8),(23,'2014-06-16','14:30:00',NULL,NULL,50,51,NULL,NULL,1,NULL,NULL,NULL,9,NULL,69,0,0,NULL,NULL,0,0,12,8),(24,'2014-08-10','17:00:00',NULL,NULL,8,5,NULL,NULL,3,NULL,NULL,NULL,9,NULL,5,2,1,NULL,NULL,0,0,2,8),(25,'2014-08-10','17:00:00',NULL,NULL,15,7,NULL,NULL,2,NULL,NULL,NULL,9,NULL,5,2,1,NULL,NULL,0,0,3,8),(26,'2014-08-10','15:00:00',NULL,NULL,12,13,NULL,NULL,2,NULL,NULL,NULL,9,NULL,5,2,2,NULL,NULL,0,0,4,8),(27,'2014-08-10','17:00:00',NULL,NULL,4,11,NULL,NULL,4,NULL,NULL,NULL,9,NULL,5,3,2,NULL,NULL,0,0,5,8),(28,'2014-08-10','15:00:00',NULL,NULL,10,19,NULL,NULL,5,NULL,NULL,NULL,9,NULL,5,2,1,NULL,NULL,0,0,6,8),(29,'2014-08-10','15:00:00',NULL,NULL,18,6,NULL,NULL,2,NULL,NULL,NULL,9,NULL,5,1,2,NULL,NULL,0,0,7,8),(30,'2014-08-10','15:00:00',NULL,NULL,17,9,NULL,NULL,2,NULL,NULL,NULL,9,NULL,5,2,0,NULL,NULL,0,0,8,8),(31,'2014-08-10','15:00:00',NULL,NULL,20,16,NULL,NULL,2,NULL,NULL,NULL,9,NULL,5,1,1,NULL,NULL,0,0,9,8),(32,'2014-06-26','15:00:00',NULL,NULL,1,5,'','',1,NULL,NULL,NULL,9,2,NULL,3,1,NULL,NULL,0,0,0,8),(33,'2014-06-16','11:30:00',NULL,NULL,52,44,NULL,NULL,3,NULL,NULL,NULL,9,NULL,72,4,0,NULL,NULL,0,0,13,8),(34,'2014-06-16','17:30:00',NULL,NULL,43,42,NULL,NULL,1,NULL,NULL,NULL,9,NULL,72,1,2,NULL,NULL,0,0,14,8),(35,'2014-06-17','11:30:00',NULL,NULL,45,48,NULL,NULL,4,NULL,NULL,NULL,9,NULL,75,2,1,NULL,NULL,0,0,15,8),(36,'2014-06-17','17:30:00',NULL,NULL,47,46,NULL,NULL,2,NULL,NULL,NULL,9,NULL,75,1,1,NULL,NULL,0,0,16,8),(37,'2014-06-17','14:30:00',NULL,NULL,21,22,NULL,NULL,4,NULL,NULL,NULL,9,NULL,55,0,0,NULL,NULL,0,0,17,8),(38,'2014-06-18','17:30:00',NULL,NULL,23,24,NULL,NULL,1,NULL,NULL,NULL,9,NULL,55,0,4,NULL,NULL,0,0,18,8),(39,'2014-06-18','11:30:00',NULL,NULL,27,25,NULL,NULL,3,NULL,NULL,NULL,9,NULL,58,2,3,NULL,NULL,0,0,19,8),(40,'2014-06-18','14:30:00',NULL,NULL,28,26,NULL,NULL,5,NULL,NULL,NULL,9,NULL,58,0,2,NULL,NULL,0,0,20,8),(41,'2014-06-19','11:30:00',NULL,NULL,29,30,NULL,NULL,2,NULL,NULL,NULL,9,NULL,61,2,1,NULL,NULL,0,0,21,8),(42,'2014-06-19','17:30:00',NULL,NULL,31,32,NULL,NULL,4,NULL,NULL,NULL,9,NULL,61,0,0,NULL,NULL,0,0,22,8),(43,'2014-06-19','14:30:00',NULL,NULL,36,35,NULL,NULL,1,NULL,NULL,NULL,9,NULL,64,2,1,NULL,NULL,0,0,23,8),(44,'2014-06-20','11:30:00',NULL,NULL,34,33,NULL,NULL,4,NULL,NULL,NULL,9,NULL,64,0,1,NULL,NULL,0,0,24,8),(45,'2014-06-20','14:30:00',NULL,NULL,38,37,NULL,NULL,6,NULL,NULL,NULL,9,NULL,67,2,5,NULL,NULL,0,0,25,8),(46,'2014-06-20','17:30:00',NULL,NULL,40,39,NULL,NULL,3,NULL,NULL,NULL,9,NULL,67,1,2,NULL,NULL,0,0,26,8),(47,'2014-06-21','11:30:00',NULL,NULL,41,50,NULL,NULL,1,NULL,NULL,NULL,9,NULL,70,1,0,NULL,NULL,0,0,27,8),(48,'2014-06-21','17:30:00',NULL,NULL,51,49,NULL,NULL,3,NULL,NULL,NULL,9,NULL,70,1,0,NULL,NULL,0,0,28,8),(49,'2014-06-21','14:30:00',NULL,NULL,52,43,NULL,NULL,5,NULL,NULL,NULL,9,NULL,73,2,2,NULL,NULL,0,0,29,8),(50,'2014-06-22','17:30:00',NULL,NULL,42,44,NULL,NULL,6,NULL,NULL,NULL,9,NULL,73,2,2,NULL,NULL,0,0,30,8),(51,'2014-06-22','11:30:00',NULL,NULL,45,47,NULL,NULL,1,NULL,NULL,NULL,9,NULL,76,1,0,NULL,NULL,0,0,31,8),(52,'2014-06-22','14:30:00',NULL,NULL,46,48,NULL,NULL,3,NULL,NULL,NULL,9,NULL,76,2,4,NULL,NULL,0,0,32,8),(53,'2014-06-23','15:30:00',NULL,NULL,23,21,NULL,NULL,2,NULL,NULL,NULL,9,NULL,56,1,4,NULL,NULL,0,0,33,8),(54,'2014-06-23','15:30:00',NULL,NULL,24,22,NULL,NULL,4,NULL,NULL,NULL,9,NULL,56,1,3,NULL,NULL,0,0,34,8),(55,'2014-06-23','11:30:00',NULL,NULL,27,28,NULL,NULL,5,NULL,NULL,NULL,9,NULL,59,0,3,NULL,NULL,0,0,35,8),(56,'2014-06-23','11:30:00',NULL,NULL,25,26,NULL,NULL,6,NULL,NULL,NULL,9,NULL,59,2,0,NULL,NULL,0,0,36,8),(57,'2014-06-24','15:30:00',NULL,NULL,31,29,NULL,NULL,1,NULL,NULL,NULL,9,NULL,62,1,4,NULL,NULL,0,0,37,8),(58,'2014-06-24','15:30:00',NULL,NULL,32,30,NULL,NULL,2,NULL,NULL,NULL,9,NULL,62,2,1,NULL,NULL,0,0,38,8),(59,'2014-06-24','11:30:00',NULL,NULL,34,36,NULL,NULL,1,NULL,NULL,NULL,9,NULL,65,0,1,NULL,NULL,0,0,39,8),(60,'2014-06-24','10:30:00',NULL,NULL,33,35,NULL,NULL,5,NULL,NULL,NULL,9,NULL,65,0,0,NULL,NULL,0,0,40,8),(61,'2014-06-25','15:30:00',NULL,NULL,40,38,NULL,NULL,1,NULL,NULL,NULL,9,NULL,68,0,3,NULL,NULL,0,0,41,8),(62,'2014-06-25','15:30:00',NULL,NULL,39,37,NULL,NULL,6,NULL,NULL,NULL,9,NULL,68,0,0,NULL,NULL,0,0,42,8),(63,'2014-06-25','11:30:00',NULL,NULL,51,41,NULL,NULL,4,NULL,NULL,NULL,9,NULL,71,2,3,NULL,NULL,0,0,43,8),(64,'2014-06-25','11:30:00',NULL,NULL,49,50,NULL,NULL,2,NULL,NULL,NULL,9,NULL,71,3,1,NULL,NULL,0,0,44,8),(65,'2014-06-26','11:30:00',NULL,NULL,42,52,NULL,NULL,1,NULL,NULL,NULL,9,NULL,74,0,1,NULL,NULL,0,0,45,8),(66,'2014-06-26','11:30:00',NULL,NULL,44,43,NULL,NULL,1,NULL,NULL,NULL,9,NULL,74,2,1,NULL,NULL,0,0,46,8),(67,'2014-06-26','15:30:00',NULL,NULL,46,45,NULL,NULL,3,NULL,NULL,NULL,9,NULL,77,0,1,NULL,NULL,0,0,47,8),(68,'2014-06-26','15:30:00',NULL,NULL,48,47,NULL,NULL,3,NULL,NULL,NULL,9,NULL,77,1,1,NULL,NULL,0,0,48,8),(69,'2014-06-30','11:30:00',NULL,NULL,37,51,'','',5,NULL,NULL,NULL,1,66,NULL,NULL,NULL,NULL,NULL,NULL,NULL,53,8),(70,'2014-06-30','15:30:00',NULL,NULL,52,48,'','',6,NULL,NULL,NULL,1,67,NULL,NULL,NULL,NULL,NULL,NULL,NULL,54,8),(71,'2014-07-01','11:30:00',NULL,NULL,41,38,'','',1,NULL,NULL,NULL,9,68,NULL,1,0,NULL,NULL,NULL,NULL,55,8),(72,'2014-07-01','15:30:00',NULL,NULL,45,42,'','',3,NULL,NULL,NULL,9,69,NULL,2,1,NULL,NULL,NULL,NULL,56,8),(73,'2014-06-03','15:00:00',NULL,NULL,4,6,'Final','',3,NULL,NULL,NULL,1,56,NULL,0,0,NULL,NULL,NULL,NULL,0,8),(74,'2014-08-16','18:00:00',NULL,NULL,7,10,NULL,NULL,6,NULL,NULL,NULL,9,NULL,6,3,3,NULL,NULL,0,0,13,8),(75,'2014-07-08','15:00:00',NULL,NULL,3,53,NULL,NULL,2,NULL,NULL,NULL,1,NULL,78,NULL,NULL,NULL,NULL,NULL,NULL,1,2),(76,'2014-08-13','19:30:00',NULL,NULL,1,11,NULL,NULL,1,NULL,NULL,NULL,9,NULL,7,3,0,NULL,NULL,0,0,21,8),(77,'2014-07-09','15:00:00',NULL,NULL,55,57,NULL,NULL,5,NULL,NULL,NULL,1,NULL,78,NULL,NULL,NULL,NULL,NULL,NULL,3,2),(78,'2014-07-09','17:00:00',NULL,NULL,53,55,NULL,NULL,4,NULL,NULL,NULL,1,NULL,78,NULL,NULL,NULL,NULL,NULL,NULL,2,2),(79,'2014-08-08','14:00:00',NULL,NULL,9,10,NULL,NULL,1,NULL,NULL,NULL,9,NULL,4,3,0,NULL,NULL,0,0,5,8),(80,'2014-07-12','15:00:00',NULL,NULL,4,6,NULL,NULL,5,NULL,NULL,NULL,9,NULL,4,1,1,NULL,NULL,0,0,0,8),(81,'2014-01-11','09:00:00',NULL,NULL,73,67,NULL,NULL,1,NULL,NULL,NULL,9,NULL,32,0,5,NULL,NULL,0,0,1,7),(82,'2014-01-11','09:00:00',NULL,NULL,112,106,NULL,NULL,1,NULL,NULL,NULL,9,NULL,32,0,4,NULL,NULL,0,0,4,13),(83,'2014-01-11','11:00:00',NULL,NULL,125,119,NULL,NULL,1,NULL,NULL,NULL,9,NULL,32,0,2,NULL,NULL,0,0,5,14),(84,'2014-01-11','09:30:00',NULL,NULL,79,78,NULL,NULL,4,NULL,NULL,NULL,9,NULL,33,0,1,NULL,NULL,0,0,22,7),(85,'2014-01-11','09:00:00',NULL,NULL,76,77,NULL,NULL,5,NULL,NULL,NULL,9,NULL,33,2,0,NULL,NULL,0,0,16,7),(86,'2014-01-11','09:30:00',NULL,NULL,74,75,NULL,NULL,6,NULL,NULL,NULL,9,NULL,33,5,6,NULL,NULL,0,0,19,7),(87,'2014-01-11','10:00:00',NULL,NULL,86,80,NULL,NULL,1,NULL,NULL,NULL,9,NULL,32,1,1,NULL,NULL,0,0,2,11),(88,'2014-01-11','09:00:00',NULL,NULL,70,68,NULL,NULL,2,NULL,NULL,NULL,9,NULL,32,1,2,NULL,NULL,0,0,6,7),(89,'2014-01-11','10:00:00',NULL,NULL,83,81,NULL,NULL,2,NULL,NULL,NULL,9,NULL,32,0,1,NULL,NULL,0,0,7,11),(90,'2014-01-11','10:30:00',NULL,NULL,92,91,NULL,NULL,4,NULL,NULL,NULL,9,NULL,33,5,0,NULL,NULL,0,0,23,11),(91,'2014-01-11','10:00:00',NULL,NULL,89,90,NULL,NULL,5,NULL,NULL,NULL,9,NULL,33,2,1,NULL,NULL,0,0,17,11),(92,'2014-01-11','10:30:00',NULL,NULL,87,88,NULL,NULL,6,NULL,NULL,NULL,9,NULL,33,9,0,NULL,NULL,0,0,20,11),(93,'2014-01-11','11:00:00',NULL,NULL,99,93,NULL,NULL,1,NULL,NULL,NULL,9,NULL,32,1,1,NULL,NULL,0,0,3,12),(94,'2014-01-11','11:00:00',NULL,NULL,96,94,NULL,NULL,2,NULL,NULL,NULL,9,NULL,32,0,1,NULL,NULL,0,0,8,12),(95,'2014-01-11','09:00:00',NULL,NULL,109,107,NULL,NULL,2,NULL,NULL,NULL,9,NULL,32,0,3,NULL,NULL,0,0,9,13),(96,'2014-01-11','09:00:00',NULL,NULL,122,120,NULL,NULL,2,NULL,NULL,NULL,9,NULL,32,3,1,NULL,NULL,0,0,10,14),(97,'2014-01-11','09:00:00',NULL,NULL,69,71,NULL,NULL,6,NULL,NULL,NULL,9,NULL,32,1,1,NULL,NULL,0,0,11,7),(98,'2014-01-11','10:00:00',NULL,NULL,82,84,NULL,NULL,6,NULL,NULL,NULL,9,NULL,32,0,1,NULL,NULL,0,0,12,11),(99,'2014-01-11','11:00:00',NULL,NULL,95,97,NULL,NULL,6,NULL,NULL,NULL,9,NULL,32,1,0,NULL,NULL,0,0,13,12),(100,'2014-01-11','13:00:00',NULL,NULL,108,110,NULL,NULL,6,NULL,NULL,NULL,9,NULL,32,2,1,NULL,NULL,0,0,14,13),(101,'2014-01-11','15:00:00',NULL,NULL,121,123,NULL,NULL,6,NULL,NULL,NULL,9,NULL,32,3,0,NULL,NULL,0,0,15,14),(102,'2014-01-11','11:00:00',NULL,NULL,102,103,NULL,NULL,5,NULL,NULL,NULL,9,NULL,33,3,2,NULL,NULL,0,0,18,12),(103,'2014-01-11','11:30:00',NULL,NULL,100,101,NULL,NULL,6,NULL,NULL,NULL,9,NULL,33,1,1,NULL,NULL,0,0,21,12),(104,'2014-01-11','11:30:00',NULL,NULL,105,104,NULL,NULL,4,NULL,NULL,NULL,9,NULL,33,4,2,NULL,NULL,0,0,24,12),(105,'2014-01-11','13:00:00',NULL,NULL,115,116,NULL,NULL,5,NULL,NULL,NULL,9,NULL,33,1,0,NULL,NULL,0,0,25,13),(106,'2014-01-11','13:30:00',NULL,NULL,113,114,NULL,NULL,6,NULL,NULL,NULL,9,NULL,33,0,2,NULL,NULL,0,0,26,13),(107,'2014-01-11','13:30:00',NULL,NULL,118,117,NULL,NULL,4,NULL,NULL,NULL,9,NULL,33,1,3,NULL,NULL,0,0,27,13),(108,'2014-01-11','15:00:00',NULL,NULL,128,129,NULL,NULL,5,NULL,NULL,NULL,9,NULL,33,1,1,NULL,NULL,0,0,28,14),(109,'2014-01-11','15:30:00',NULL,NULL,126,127,NULL,NULL,6,NULL,NULL,NULL,9,NULL,33,2,0,NULL,NULL,0,0,29,14),(110,'2014-01-11','15:30:00',NULL,NULL,131,130,NULL,NULL,4,NULL,NULL,NULL,9,NULL,33,2,2,NULL,NULL,0,0,30,14),(111,'2014-01-18','09:00:00',NULL,NULL,72,68,NULL,NULL,1,NULL,NULL,NULL,9,NULL,34,1,5,NULL,NULL,0,0,31,7),(112,'2014-01-18','10:00:00',NULL,NULL,85,81,NULL,NULL,1,NULL,NULL,NULL,9,NULL,34,2,0,NULL,NULL,0,0,32,11),(113,'2014-01-18','11:00:00',NULL,NULL,98,94,NULL,NULL,1,NULL,NULL,NULL,9,NULL,34,0,2,NULL,NULL,0,0,33,12),(114,'2014-01-18','09:00:00',NULL,NULL,111,107,NULL,NULL,1,NULL,NULL,NULL,9,NULL,34,2,2,NULL,NULL,0,0,34,13),(115,'2014-01-18','11:00:00',NULL,NULL,124,120,NULL,NULL,1,NULL,NULL,NULL,9,NULL,34,0,3,NULL,NULL,0,0,35,14),(116,'2014-01-18','09:30:00',NULL,NULL,69,70,NULL,NULL,2,NULL,NULL,NULL,9,NULL,34,1,0,NULL,NULL,0,0,36,7),(117,'2014-01-18','10:30:00',NULL,NULL,82,83,NULL,NULL,2,NULL,NULL,NULL,9,NULL,34,0,0,NULL,NULL,0,0,37,11),(118,'2014-01-18','11:30:00',NULL,NULL,95,96,NULL,NULL,2,NULL,NULL,NULL,9,NULL,34,2,0,NULL,NULL,0,0,38,12),(119,'2014-01-18','13:30:00',NULL,NULL,108,109,NULL,NULL,2,NULL,NULL,NULL,9,NULL,34,3,2,NULL,NULL,0,0,39,13),(120,'2014-01-18','15:30:00',NULL,NULL,121,122,NULL,NULL,2,NULL,NULL,NULL,9,NULL,34,0,0,NULL,NULL,0,0,40,14),(121,'2014-01-18','09:30:00',NULL,NULL,71,73,NULL,NULL,3,NULL,NULL,NULL,9,NULL,34,10,2,NULL,NULL,0,0,41,7),(122,'2014-01-18','10:30:00',NULL,NULL,84,86,NULL,NULL,3,NULL,NULL,NULL,9,NULL,34,2,0,NULL,NULL,0,0,42,11),(123,'2014-01-18','11:30:00',NULL,NULL,97,99,NULL,NULL,3,NULL,NULL,NULL,9,NULL,34,1,1,NULL,NULL,0,0,43,12),(124,'2014-01-18','09:30:00',NULL,NULL,110,112,NULL,NULL,3,NULL,NULL,NULL,1,NULL,34,NULL,NULL,NULL,NULL,NULL,NULL,44,13),(125,'2014-01-18','11:30:00',NULL,NULL,123,125,NULL,NULL,3,NULL,NULL,NULL,1,NULL,34,NULL,NULL,NULL,NULL,NULL,NULL,45,14),(126,'2014-01-18','09:00:00',NULL,NULL,78,74,NULL,NULL,4,NULL,NULL,NULL,9,NULL,35,2,7,NULL,NULL,0,0,46,7),(127,'2014-01-18','10:00:00',NULL,NULL,91,87,NULL,NULL,4,NULL,NULL,NULL,9,NULL,35,2,3,NULL,NULL,0,0,47,11),(128,'2014-01-18','11:00:00',NULL,NULL,104,100,NULL,NULL,4,NULL,NULL,NULL,9,NULL,35,2,1,NULL,NULL,0,0,48,12),(129,'2014-01-18','13:00:00',NULL,NULL,117,113,NULL,NULL,4,NULL,NULL,NULL,9,NULL,35,1,1,NULL,NULL,0,0,49,13),(130,'2014-01-18','15:00:00',NULL,NULL,130,126,NULL,NULL,4,NULL,NULL,NULL,9,NULL,35,3,2,NULL,NULL,0,0,50,14),(131,'2014-01-18','09:00:00',NULL,NULL,75,76,NULL,NULL,5,NULL,NULL,NULL,9,NULL,35,4,3,NULL,NULL,0,0,51,7),(132,'2014-01-18','10:00:00',NULL,NULL,88,89,NULL,NULL,5,NULL,NULL,NULL,9,NULL,35,2,5,NULL,NULL,0,0,52,11),(133,'2014-01-18','11:00:00',NULL,NULL,101,102,NULL,NULL,5,NULL,NULL,NULL,9,NULL,35,4,1,NULL,NULL,0,0,53,12),(134,'2014-01-18','13:00:00',NULL,NULL,114,115,NULL,NULL,5,NULL,NULL,NULL,9,NULL,35,1,2,NULL,NULL,0,0,54,13),(135,'2014-01-18','15:00:00',NULL,NULL,127,128,NULL,NULL,5,NULL,NULL,NULL,9,NULL,35,1,0,NULL,NULL,0,0,55,14),(136,'2014-01-18','09:00:00',NULL,NULL,77,79,NULL,NULL,6,NULL,NULL,NULL,9,NULL,35,1,0,NULL,NULL,0,0,56,7),(137,'2014-01-18','10:00:00',NULL,NULL,90,92,NULL,NULL,6,NULL,NULL,NULL,9,NULL,35,3,3,NULL,NULL,0,0,57,11),(138,'2014-01-18','11:00:00',NULL,NULL,103,105,NULL,NULL,6,NULL,NULL,NULL,9,NULL,35,5,3,NULL,NULL,0,0,58,12),(139,'2014-01-18','09:00:00',NULL,NULL,116,118,NULL,NULL,6,NULL,NULL,NULL,9,NULL,35,0,1,NULL,NULL,0,0,59,13),(140,'2014-01-18','11:00:00',NULL,NULL,129,131,NULL,NULL,6,NULL,NULL,NULL,9,NULL,35,1,1,NULL,NULL,0,0,60,14),(141,NULL,NULL,NULL,NULL,NULL,NULL,'1° Grupo A','4° Grupo B',NULL,NULL,NULL,NULL,12,70,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,7),(142,'2014-01-25','09:00:00',NULL,NULL,67,68,NULL,NULL,1,NULL,NULL,NULL,9,NULL,83,5,2,NULL,NULL,0,0,61,7),(143,'2014-01-25','10:00:00',NULL,NULL,80,81,NULL,NULL,1,NULL,NULL,NULL,9,NULL,83,2,0,NULL,NULL,0,0,62,11),(144,'2014-01-25','11:00:00',NULL,NULL,93,94,NULL,NULL,1,NULL,NULL,NULL,9,NULL,83,3,2,NULL,NULL,0,0,63,12),(145,'2014-01-25','09:00:00',NULL,NULL,106,107,NULL,NULL,1,NULL,NULL,NULL,9,NULL,83,3,2,NULL,NULL,0,0,64,13),(146,'2014-01-25','11:00:00',NULL,NULL,119,120,NULL,NULL,1,NULL,NULL,NULL,9,NULL,83,2,0,NULL,NULL,0,0,65,14),(147,'2014-01-25','09:00:00',NULL,NULL,72,69,NULL,NULL,2,NULL,NULL,NULL,9,NULL,83,0,0,NULL,NULL,0,0,66,7),(148,'2014-01-25','10:00:00',NULL,NULL,85,82,NULL,NULL,2,NULL,NULL,NULL,9,NULL,83,1,0,NULL,NULL,0,0,67,11),(149,'2014-01-25','11:00:00',NULL,NULL,98,95,NULL,NULL,2,NULL,NULL,NULL,9,NULL,83,2,3,NULL,NULL,0,0,68,12),(150,'2014-01-25','09:00:00',NULL,NULL,111,108,NULL,NULL,2,NULL,NULL,NULL,9,NULL,83,0,4,NULL,NULL,0,0,69,13),(151,'2014-01-25','11:00:00',NULL,NULL,124,121,NULL,NULL,2,NULL,NULL,NULL,9,NULL,83,0,3,NULL,NULL,0,0,70,14),(152,'2014-01-25','09:30:00',NULL,NULL,70,73,NULL,NULL,3,NULL,NULL,NULL,9,NULL,83,6,0,NULL,NULL,0,0,71,7),(153,'2014-01-25','10:30:00',NULL,NULL,83,86,NULL,NULL,3,NULL,NULL,NULL,9,NULL,83,1,2,NULL,NULL,0,0,72,11),(154,'2014-01-25','11:30:00',NULL,NULL,96,99,NULL,NULL,3,NULL,NULL,NULL,9,NULL,83,2,1,NULL,NULL,0,0,73,12),(155,'2014-01-25','10:00:00',NULL,NULL,109,112,NULL,NULL,3,NULL,NULL,NULL,9,NULL,83,2,2,NULL,NULL,0,0,74,13),(156,'2014-01-25','12:00:00',NULL,NULL,122,125,NULL,NULL,3,NULL,NULL,NULL,9,NULL,83,1,0,NULL,NULL,0,0,75,14),(157,'2014-01-25','09:30:00',NULL,NULL,75,78,NULL,NULL,4,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,76,7),(158,'2014-01-25','10:30:00',NULL,NULL,88,91,NULL,NULL,4,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,77,11),(159,'2014-01-25','11:30:00',NULL,NULL,101,104,NULL,NULL,4,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,78,12),(160,'2014-01-25','13:30:00',NULL,NULL,114,117,NULL,NULL,4,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,79,13),(161,'2014-01-25','15:30:00',NULL,NULL,127,130,NULL,NULL,4,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,80,14),(162,'2014-01-25','09:30:00',NULL,NULL,79,76,NULL,NULL,5,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,81,7),(163,'2014-01-25','10:30:00',NULL,NULL,92,89,NULL,NULL,5,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,82,11),(164,'2014-01-25','11:30:00',NULL,NULL,105,102,NULL,NULL,5,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,83,12),(165,'2014-01-25','14:00:00',NULL,NULL,118,115,NULL,NULL,5,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,84,13),(166,'2014-01-25','16:00:00',NULL,NULL,131,128,NULL,NULL,5,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,85,14),(167,'2014-01-25','09:00:00',NULL,NULL,74,77,NULL,NULL,6,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,86,7),(168,'2014-01-25','10:00:00',NULL,NULL,87,90,NULL,NULL,6,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,87,11),(169,'2014-01-25','11:00:00',NULL,NULL,100,103,NULL,NULL,6,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,88,12),(170,'2014-01-25','09:00:00',NULL,NULL,113,116,NULL,NULL,6,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,89,13),(171,'2014-01-25','11:00:00',NULL,NULL,126,129,NULL,NULL,6,NULL,NULL,NULL,1,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,90,14),(172,'2014-08-10','17:00:00',NULL,NULL,7,10,NULL,NULL,6,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,5,8),(173,'2014-11-12','18:00:00',NULL,NULL,13,1,NULL,NULL,5,NULL,NULL,NULL,9,NULL,6,0,3,NULL,NULL,0,0,11,8),(174,'2014-08-17','15:00:00',NULL,NULL,11,12,NULL,NULL,5,NULL,NULL,NULL,9,NULL,6,1,2,NULL,NULL,0,0,10,8),(175,'2014-08-17','16:00:00',NULL,NULL,14,15,NULL,NULL,2,NULL,NULL,NULL,9,NULL,6,2,1,NULL,NULL,0,0,12,8),(176,'2014-08-17','16:00:00',NULL,NULL,4,20,NULL,NULL,4,NULL,NULL,NULL,9,NULL,6,2,1,NULL,NULL,0,0,14,8),(177,'2014-08-17','16:00:00',NULL,NULL,16,8,NULL,NULL,2,NULL,NULL,NULL,9,NULL,6,2,2,NULL,NULL,0,0,15,8),(178,'2014-08-17','16:00:00',NULL,NULL,5,17,NULL,NULL,5,NULL,NULL,NULL,9,NULL,6,1,0,NULL,NULL,0,0,16,8),(179,'2014-08-17','16:00:00',NULL,NULL,9,18,NULL,NULL,1,NULL,NULL,NULL,9,NULL,6,0,1,NULL,NULL,0,0,17,8),(180,'2014-08-17','15:30:00',NULL,NULL,6,19,NULL,NULL,2,NULL,NULL,NULL,9,NULL,6,1,0,NULL,NULL,0,0,18,8),(181,'2014-08-23','18:00:00',NULL,NULL,10,14,NULL,NULL,5,NULL,NULL,NULL,9,NULL,7,0,0,NULL,NULL,0,0,19,8),(182,'2014-08-24','18:00:00',NULL,NULL,15,13,NULL,NULL,2,NULL,NULL,NULL,9,NULL,7,2,1,NULL,NULL,0,0,20,8),(183,'2014-08-24','15:30:00',NULL,NULL,12,4,NULL,NULL,2,NULL,NULL,NULL,9,NULL,7,2,2,NULL,NULL,0,0,22,8),(184,'2014-08-24','17:00:00',NULL,NULL,7,6,NULL,NULL,6,NULL,NULL,NULL,9,NULL,7,3,2,NULL,NULL,0,0,23,8),(185,'2014-08-24','17:00:00',NULL,NULL,19,9,NULL,NULL,2,NULL,NULL,NULL,9,NULL,7,3,2,NULL,NULL,0,0,24,8),(186,'2014-08-24','15:00:00',NULL,NULL,18,5,NULL,NULL,2,NULL,NULL,NULL,9,NULL,7,1,3,NULL,NULL,0,0,25,8),(187,'2014-08-24','15:30:00',NULL,NULL,17,16,NULL,NULL,2,NULL,NULL,NULL,9,NULL,7,1,2,NULL,NULL,0,0,26,8),(188,'2014-08-24','17:00:00',NULL,NULL,8,20,NULL,NULL,3,NULL,NULL,NULL,9,NULL,7,1,1,NULL,NULL,0,0,27,8),(189,'2014-08-31','16:00:00',NULL,NULL,4,1,NULL,NULL,4,NULL,NULL,NULL,9,NULL,8,0,2,NULL,NULL,0,0,28,8),(190,'2014-08-31','16:00:00',NULL,NULL,11,15,NULL,NULL,5,NULL,NULL,NULL,9,NULL,8,1,3,NULL,NULL,0,0,29,8),(191,'2014-08-31','16:00:00',NULL,NULL,14,7,NULL,NULL,2,NULL,NULL,NULL,9,NULL,8,0,2,NULL,NULL,0,0,31,8),(192,'2014-08-31','15:30:00',NULL,NULL,12,8,NULL,NULL,6,NULL,NULL,NULL,9,NULL,8,1,0,NULL,NULL,0,0,32,8),(193,'2014-08-31','17:00:00',NULL,NULL,20,17,NULL,NULL,3,NULL,NULL,NULL,9,NULL,8,1,1,NULL,NULL,0,0,33,8),(194,'2014-08-31','16:00:00',NULL,NULL,16,18,NULL,NULL,4,NULL,NULL,NULL,9,NULL,8,0,0,NULL,NULL,0,0,34,8),(195,'2014-08-31','16:00:00',NULL,NULL,5,19,NULL,NULL,3,NULL,NULL,NULL,9,NULL,8,2,1,NULL,NULL,0,0,35,8),(196,'2014-08-31','16:00:00',NULL,NULL,9,6,NULL,NULL,4,NULL,NULL,NULL,9,NULL,8,1,1,NULL,NULL,0,0,36,8),(197,'2014-09-10','18:00:00',NULL,NULL,13,10,NULL,NULL,2,NULL,NULL,NULL,9,NULL,8,1,0,NULL,NULL,0,0,30,8),(198,'2014-08-19','21:30:00',NULL,NULL,142,143,'','',2,NULL,NULL,NULL,1,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,8),(199,'2014-08-26','16:00:00',NULL,NULL,143,142,'','',3,NULL,NULL,NULL,1,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,8),(200,'2014-08-19','17:00:00',NULL,NULL,144,145,'','',1,NULL,NULL,NULL,1,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,8),(201,'2014-08-26','21:00:00',NULL,NULL,145,144,'','',4,NULL,NULL,NULL,1,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,8);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:55