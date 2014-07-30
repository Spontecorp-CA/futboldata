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
-- Table structure for table `fase`
--

DROP TABLE IF EXISTS `fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `tipo_fase` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `temporada_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fase_temporada1_idx` (`temporada_id`),
  CONSTRAINT `fk_fase_temporada1` FOREIGN KEY (`temporada_id`) REFERENCES `temporada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'Torneo Apertura 2014',0,1,1),(2,'Fase de Grupos',NULL,1,2),(3,'Clasificatoria',0,1,4),(4,'Semifinal',1,1,4),(5,'Final',1,1,4),(6,'Clausura 2015',0,1,1),(7,'Serie Final',1,1,1),(8,'Liguilla Pre-Sudamericana',1,1,1),(9,'Fase de Grupos',0,1,10),(10,'Apertura 2014',0,1,3),(11,'Ascenso 2015',0,1,3),(12,'Promoción y Permanencia 2015',0,1,3),(13,'Cuartos de Final',1,1,10),(14,'Primera Fase',1,1,11),(15,'Segunda Fase',1,1,11),(16,'Octavos de Final',1,1,11),(17,'Cuartos de Final',1,1,11),(18,'Semifinal',1,1,11),(19,'Primera Fase',1,1,12),(20,'Segunda Fase',0,1,12),(21,'Octavos de Final',1,1,12),(22,'Cuartos de Final',1,1,12),(23,'Semifinal',1,1,12),(24,'Fase Apertura',0,1,13),(26,'Fase Clausura ',0,1,13),(27,'Final',1,1,13),(28,'Semi Final',1,1,13),(29,'Fase 1',0,1,13),(30,'Fase  007',1,1,13),(31,'Grupo de Clasificatoria ',0,1,13),(32,'Final',1,1,12),(33,'Final',1,1,11),(34,'Fase de Grupos',0,1,15),(35,'Octavos de Final',1,1,15),(36,'Cuartos de Final',1,1,15),(37,'Semifinal',1,1,15),(38,'Tercer Puesto',1,1,15),(39,'Final',1,1,15),(40,'Fase de Grupos',0,1,16);
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:47
