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
-- Table structure for table `equipo_staff_titulo`
--

DROP TABLE IF EXISTS `equipo_staff_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_staff_titulo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equipo_staff_id` int(10) unsigned NOT NULL,
  `equipo_staff_equipo_id` int(10) unsigned NOT NULL,
  `equipo_staff_persona_id` int(10) unsigned NOT NULL,
  `titulo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`equipo_staff_id`,`equipo_staff_equipo_id`,`equipo_staff_persona_id`,`titulo_id`),
  KEY `fk_equipo_staff_has_titulo_titulo1_idx` (`titulo_id`),
  KEY `fk_equipo_staff_has_titulo_equipo_staff1_idx` (`equipo_staff_id`,`equipo_staff_equipo_id`,`equipo_staff_persona_id`),
  CONSTRAINT `fk_equipo_staff_has_titulo_equipo_staff1` FOREIGN KEY (`equipo_staff_id`, `equipo_staff_equipo_id`, `equipo_staff_persona_id`) REFERENCES `equipo_staff` (`id`, `equipo_id`, `persona_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_staff_has_titulo_titulo1` FOREIGN KEY (`titulo_id`) REFERENCES `titulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_staff_titulo`
--

LOCK TABLES `equipo_staff_titulo` WRITE;
/*!40000 ALTER TABLE `equipo_staff_titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_staff_titulo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-31 10:49:41
