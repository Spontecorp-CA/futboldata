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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
INSERT INTO `clasificacion` VALUES (5,1,1,1,9,1,1,0,0,3,2,1,3,1,NULL),(6,1,8,1,10,1,0,0,1,2,3,-1,0,0,NULL),(7,1,7,4,11,1,1,0,0,4,3,1,3,1,NULL),(8,1,8,4,10,1,0,0,1,3,4,-1,0,0,NULL),(9,4,9,79,12,1,1,0,0,3,0,3,3,1,NULL),(10,4,10,79,13,1,0,0,1,0,3,-3,0,0,NULL),(11,4,4,80,14,1,0,1,0,1,1,0,1,1,NULL),(12,4,6,80,15,1,0,1,0,1,1,0,1,0,NULL);
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

-- Dump completed on 2014-07-16 14:38:14
