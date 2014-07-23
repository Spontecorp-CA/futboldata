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
-- Table structure for table `llave`
--

DROP TABLE IF EXISTS `llave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `llave` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `fase_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_llave_fase1_idx` (`fase_id`),
  CONSTRAINT `fk_llave_fase1` FOREIGN KEY (`fase_id`) REFERENCES `fase` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llave`
--

LOCK TABLES `llave` WRITE;
/*!40000 ALTER TABLE `llave` DISABLE KEYS */;
INSERT INTO `llave` VALUES (1,'Final',1,7),(2,'Octavos',1,4),(3,'Llave Ida',1,9),(4,'Llave Vuelta',1,9),(5,'Zona Sur-Llave G1',1,14),(6,'Zona Sur-Llave G2',1,14),(7,'Zona Sur-Llave G3',1,14),(8,'Zona Sur-Llave G4',1,14),(9,'Zona Sur-Llave G5',1,14),(10,'Zona Sur-Llave G6',1,14),(11,'Zona Sur-Llave G7',1,14),(12,'Zona Sur-Llave G8',1,14),(13,'Zona Norte-Llave G9',1,14),(14,'Zona Norte-Llave G10',1,14),(15,'Zona Norte-Llave G11',1,14),(16,'Zona Norte-Llave G12',1,14),(17,'Zona Norte-Llave G13',1,14),(18,'Zona Norte-Llave G14',1,14),(19,'Zona Norte-Llave G15',1,14),(20,'Zona Norte-Llave G16',1,14),(21,'Llave O1',1,15),(22,'Llave O2',1,15),(23,'Llave O3',1,15),(24,'Llave O4',1,15),(25,'Llave O5',1,15),(26,'Llave O6',1,15),(27,'Llave O7',1,15),(28,'Llave O8',1,15),(29,'Llave O9',1,15),(30,'Llave O10',1,15),(31,'Llave O11',1,15),(32,'Llave O12',1,15),(33,'Llave O13',1,15),(34,'Llave O14',1,15),(35,'Llave O15',1,15),(36,'Llave A',1,16),(37,'Llave B',1,16),(38,'Llave C',1,16),(39,'Llave D',1,16),(40,'Llave E',1,16),(41,'Llave F',1,16),(42,'Llave G',1,16),(43,'Llave H',1,16),(44,'Llave S1',1,17),(45,'Llave S2',1,17),(46,'Llave S3',1,17),(47,'Llave S4',1,17),(48,'Semifinal F1',1,18),(49,'Semifinal F2',1,18),(50,'Llave G1',1,19),(51,'Llave G2',1,19),(52,'Llave G3',1,19),(53,'Llave G4',1,19),(54,'Llave G5',1,19),(55,'Llave G6',1,19),(56,'A1',1,27),(57,'A3',1,27),(59,'A1',1,28),(60,'A3',1,28),(61,'Final',1,33),(62,'Llave 1',1,35),(63,'Llave 2',1,35),(64,'Llave 3',1,35),(65,'Llave 4',1,35),(66,'Llave 5',1,35),(67,'Llave 6',1,35),(68,'Llave 7',1,35),(69,'Llave 8',1,35);
/*!40000 ALTER TABLE `llave` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:38:07
