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
-- Table structure for table `red_social`
--

DROP TABLE IF EXISTS `red_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `red_social` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario` varchar(80) DEFAULT NULL,
  `persona_id` int(10) unsigned DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `tipo_red_social_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_red_social_persona1_idx` (`persona_id`),
  KEY `fk_red_social_asociacion1_idx` (`asociacion_id`),
  KEY `fk_red_social_club1_idx` (`club_id`),
  KEY `fk_red_social_competicion1_idx` (`competicion_id`),
  KEY `fk_red_social_equipo1_idx` (`equipo_id`),
  KEY `fk_red_social_tipo_red_social1_idx` (`tipo_red_social_id`),
  CONSTRAINT `fk_red_social_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_tipo_red_social1` FOREIGN KEY (`tipo_red_social_id`) REFERENCES `tipo_red_social` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `red_social`
--

LOCK TABLES `red_social` WRITE;
/*!40000 ALTER TABLE `red_social` DISABLE KEYS */;
INSERT INTO `red_social` VALUES (6,'ddssdd',NULL,NULL,NULL,NULL,NULL,2),(7,'ddssdd',NULL,NULL,NULL,NULL,NULL,1),(12,'felix180',11,NULL,NULL,NULL,NULL,2),(15,'felix180',13,NULL,NULL,NULL,NULL,1),(18,'joseamromero',17,NULL,NULL,NULL,NULL,2),(19,'jose.a.m.romero',17,NULL,NULL,NULL,NULL,1),(20,'joseamromero',17,NULL,NULL,NULL,NULL,3),(22,'sasd',19,NULL,NULL,NULL,NULL,2),(23,'hhhhhh',20,NULL,NULL,NULL,NULL,1),(24,'ddddasdasd',21,NULL,NULL,NULL,NULL,1),(25,'felix189',12,NULL,NULL,NULL,NULL,2),(30,'TUKA180',12,NULL,NULL,NULL,NULL,3),(35,'elhey',11,NULL,NULL,NULL,NULL,1),(44,'jaja',36,NULL,NULL,NULL,NULL,1),(45,'@alainbaroja',47,NULL,NULL,NULL,NULL,2),(46,'@alainbaroja',47,NULL,NULL,NULL,NULL,2),(47,'prueba1',43,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `red_social` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:22
