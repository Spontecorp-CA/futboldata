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
-- Table structure for table `competicion`
--

DROP TABLE IF EXISTS `competicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competicion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `descripcion` text,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  `logo` text,
  PRIMARY KEY (`id`),
  KEY `fk_competicion_asociacion1_idx` (`asociacion_id`),
  KEY `fk_competicion_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_competicion_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_competicion_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competicion`
--

LOCK TABLES `competicion` WRITE;
/*!40000 ALTER TABLE `competicion` DISABLE KEYS */;
INSERT INTO `competicion` VALUES (1,'Liga Movistar 1ra División',1,'Liga de Fútbol de la Primera División del Fútbol Profesional Venezolano.',9,69,NULL),(2,'Liga Movistar 2da División',1,'Liga de Fútbol de la Segunda División del Fútbol Profesional Venezolano.',9,74,NULL),(3,'3ra División',1,'Liga de Fútbol de la Tercera División del Fútbol Profesional Venezolano.',9,75,NULL),(5,'La cuarta',1,'Liga de Prueba Spontecorp.',14,101,'competicion1405450051285'),(6,'Liga César del Vecchio Serie \"A\"',1,'Liga dedicada al fútbol menor de la gran Caracas.',NULL,83,NULL),(7,'Serie Nacional Sub 18 y Sub 20',1,'Torneo que realiza FVF para los equipos filiales que pertenecen a la 1ra División del Fúven.',9,84,NULL),(8,'Copa Sudamericana',1,'La Copa Sudamericana es el segundo torneo en importancia a nivel de clubes organizado por la Confederación Sudamericana de Fútbol.',10,85,NULL),(9,'Copa Libertadores',1,'En América del Sur los partidos oficiales entre clubes de distintos países nacieron en 1900, cuando Francis Chevallier Boutell asumió la presidencia de la Asociación del  Fútbol Argentino (AFA). Creó la Copa Competencia y donó el trofeo para ser disputado entre los clubes de Buenos Aires, Rosario y Montevideo que se inscribieran para participar.',10,86,NULL),(10,'Copa Mundial FIFA Brasil 2014',1,'Copa organizada por FIFA, es la competición de Fútbol más llamativa a nivel mundial.',14,87,NULL),(11,'Liga César del Vecchio Serie \"B\"',1,'Liga dedicada al fútbol menor de la gran Caracas.',NULL,88,NULL),(12,'Colegial División \"A\"',1,'Liga dedicada al fútbol menor de la Gran Caracas.',NULL,102,NULL),(13,'Colegial División \"B\"',1,'Liga dedicada al fútbol menor de la Gran Caracas.',NULL,113,NULL);
/*!40000 ALTER TABLE `competicion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:24
