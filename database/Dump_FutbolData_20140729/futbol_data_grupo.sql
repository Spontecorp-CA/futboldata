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
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `fase_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_fase1_idx` (`fase_id`),
  CONSTRAINT `fk_grupo_fase1` FOREIGN KEY (`fase_id`) REFERENCES `fase` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Grupo 1',1,3),(2,'Apertura 2014',1,1),(3,'Grupo 2',1,3),(4,'Grupo B',0,1),(5,'Clausura 2015',1,6),(6,'Grupo A',1,9),(7,'Grupo B',1,9),(8,'Grupo Centro Oriente',1,10),(9,'Grupo Centro Occidente',1,10),(10,'Ascenso 2015',1,11),(11,'Grupo Oriental',1,12),(12,'Grupo Central',1,12),(13,'Grupo Occidental',1,12),(14,'Grupo A',1,13),(15,'Grupo B',1,13),(16,'Grupo 1',1,20),(17,'Grupo 2',1,20),(18,'Grupo 3',1,20),(19,'Grupo 4',1,20),(20,'Grupo 5',1,20),(21,'Grupo 6',1,20),(22,'Grupo 7',1,20),(23,'Grupo 8',1,20),(24,'Grupo A',1,24),(25,'Grupo B',1,24),(26,'Grupo A',1,26),(27,'Grupo B',1,26),(29,'Grupo de Prueba',1,3),(30,'Grupo A',1,34),(31,'Grupo B',1,34),(32,'Grupo C',1,34),(33,'Grupo D',1,34),(34,'Grupo E',1,34),(35,'Grupo F',1,34),(36,'Grupo G',1,34),(37,'Grupo H',1,34),(38,'Grupo 1',1,40),(39,'Grupo 2',1,40),(40,'Grupo 3',1,40),(41,'Grupo 4',1,40);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:29
