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
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornada` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `grupo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jornada_grupo1_idx` (`grupo_id`),
  CONSTRAINT `fk_jornada_grupo1` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` VALUES (1,1,'',1,1),(4,2,'',1,1),(5,1,'Fecha 1',1,2),(6,2,'Fecha 2',1,2),(7,3,'Fecha 3',1,2),(8,4,'Fecha 4',1,2),(9,5,'Fecha 5',1,2),(10,6,'Fecha 6',1,2),(11,7,'Fecha 7',1,2),(12,8,'Fecha 8',1,2),(13,9,'Fecha 9',1,2),(14,10,'Fecha 10',1,2),(15,11,'Fecha 11',1,2),(16,12,'Fecha 12',1,2),(17,13,'Fecha 13',1,2),(18,14,'Fecha 14',1,2),(19,15,'Fecha 15',1,2),(20,16,'Fecha 16',1,2),(21,17,'Fecha 17',1,2),(22,NULL,NULL,1,5),(23,NULL,NULL,1,5),(24,NULL,NULL,1,5),(25,NULL,NULL,1,5),(26,NULL,NULL,1,5),(27,NULL,NULL,1,5),(28,NULL,NULL,1,5),(29,NULL,NULL,1,5),(30,NULL,NULL,1,5),(31,NULL,NULL,1,5),(32,1,'Fecha 1',1,6),(33,1,'Fecha 1',1,7),(34,2,'Fecha 2',1,6),(35,2,'Fecha 2',1,7),(36,1,'Fecha 1',1,8),(37,NULL,NULL,1,9),(38,NULL,NULL,1,11),(39,NULL,NULL,1,12),(40,NULL,NULL,1,13),(41,NULL,NULL,1,14),(42,NULL,NULL,1,15),(43,NULL,'Fecha 1',1,16),(44,NULL,NULL,1,26),(45,NULL,NULL,1,26),(46,NULL,NULL,1,26),(47,NULL,NULL,1,27),(48,NULL,NULL,1,27),(49,NULL,NULL,1,27),(50,3,'Fecha 3',1,1),(51,NULL,'Fecha 2',1,16),(52,NULL,'Fecha 3',1,16),(53,NULL,'Fecha 4',1,16),(54,1,'Fecha 1',1,30),(55,2,'Fecha 2',1,30),(56,3,'Fecha 3',1,30),(57,NULL,'Fecha 1',1,31),(58,NULL,'Fecha 2',1,31),(59,NULL,'Fecha 3',1,31),(60,NULL,'Fecha 1',1,32),(61,NULL,'Fecha 2',1,32),(62,NULL,'Fecha 3',1,32),(63,NULL,'Fecha 1',1,33),(64,NULL,'Fecha 2',1,33),(65,NULL,'Fecha 3',1,33),(66,NULL,'Fecha 1',1,34),(67,NULL,'Fecha 2',1,34),(68,NULL,'Fecha 3',1,34),(69,NULL,'Fecha 1',1,35),(70,NULL,'Fecha 2',1,35),(71,NULL,'Fecha 3',1,35),(72,NULL,'Fecha 1',1,36),(73,NULL,'Fecha 2',1,36),(74,NULL,'Fecha 3',1,36),(75,NULL,'Fecha 1',1,37),(76,NULL,'Fecha 2',1,37),(77,NULL,'Fecha 3',1,37),(78,1,'Fecha 1',1,38),(79,NULL,'',1,24),(80,1,'Fecha 1',1,39),(81,1,'Fecha 1',1,40),(82,1,'Fecha 1',1,41);
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:38:11
