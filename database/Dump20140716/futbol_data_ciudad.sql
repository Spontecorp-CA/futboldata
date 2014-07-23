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
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(45) NOT NULL,
  `pais_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ciudad_pais1_idx` (`pais_id`),
  CONSTRAINT `fk_ciudad_pais1` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Barquisimeto (Estado Lara)',1),(2,'Valencia (Estado Carabobo)',1),(3,'Caracas (Distrito Capital)',1),(8,'Cabudare (Estado Lara)',1),(9,'Bogota (Estado Asociado)',113),(10,'Santiago',247),(11,'Mexico D.F',36),(12,'San Cristóbal (Estado Táchira)',1),(13,'Buenos Aires ',94),(14,'Maracaibo (Estado Zulia)',1),(15,'Puerto Ordaz (Estado Bolívar)',1),(16,'Maracay (Estado Aragua)',1),(17,'Puerto Ayacucho (Estado Amazonas)',1),(18,'San Felipe (Estado Yaracuy)',1),(19,'Barinas (Estado Barinas)',1),(20,'Valera (Estado Trujillo)',1),(21,'Puerto La Cruz (Estado Anzoátegui)',1),(22,'Guanare (Estado Portuguesa)',1),(23,'Mérida (Estado Mérida)',1),(24,'El Vigía (Estado Mérida)',1),(25,'Araure (Estado Portuguesa)',1),(26,'Asunción',186),(27,'Coro (Estado Falcón)',1),(28,'La Guaira (Estado Vargas)',1),(29,'Zurich',24),(30,'Río de Janeiro',47),(31,'Medellín (Antioquía)',113),(32,'Cumaná (Estado Sucre)',1),(33,'El Callao (Estado Bolívar)',1),(34,'Maturín (Estado Monagas)',1),(35,'Panamá',80),(36,'Montevideo',202),(37,'Ciudad Bolívar (Estado Bolívar)',1),(38,'Caracas (Estado Miranda)',1),(39,'Guadalajara (Jalisco)',36);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:27
