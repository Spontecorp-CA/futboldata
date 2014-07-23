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
-- Table structure for table `temporada`
--

DROP TABLE IF EXISTS `temporada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporada` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `competicion_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temporada_competicion1_idx` (`competicion_id`),
  CONSTRAINT `fk_temporada_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporada`
--

LOCK TABLES `temporada` WRITE;
/*!40000 ALTER TABLE `temporada` DISABLE KEYS */;
INSERT INTO `temporada` VALUES (1,'Temporada 2014-2015','2014-01-10','2014-01-14',1,1),(2,'Temporada 2013-2014','2013-01-18','2014-01-25',1,1),(3,'Temporada 2014-2015','2014-01-09','2015-01-02',1,2),(4,'Temporada 2014-2015','2014-01-05','2014-01-15',1,5),(5,'Temporada 13','2014-01-14','2014-01-16',1,2),(6,'hhffg','2014-01-13','2014-01-15',1,2),(7,'hola 2','2014-01-05','2014-01-06',1,2),(8,'Tempordad 2012-2013','2014-01-01','2014-01-20',1,1),(9,'temporada 2014 -2015','2014-01-01','2015-01-01',1,3),(10,'Temporada 2015','2015-01-03','2015-01-11',1,6),(11,'Copa Sudamericana 2014','2014-01-19','2014-01-16',1,8),(12,'Copa Libertadores 2015','2015-01-29','2015-01-06',1,9),(13,'Temporada 2013-2014',NULL,NULL,1,5),(14,'Temporada 2012-2013',NULL,NULL,1,5),(15,'Brasil 2014','2014-01-12','2014-01-13',1,10),(16,'Temporada 2014-2015','2014-01-10','2015-01-31',1,7),(17,'Temporada 2011-2012',NULL,NULL,1,5),(18,'Temporada 2015','2015-01-10','2015-07-11',1,12);
/*!40000 ALTER TABLE `temporada` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:32
