CREATE DATABASE  IF NOT EXISTS `futbol_data_balonazos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data_balonazos`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: spontedev32    Database: futbol_data_balonazos
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
-- Table structure for table `cancha`
--

DROP TABLE IF EXISTS `cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancha` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `superficie` varchar(45) DEFAULT NULL,
  `medidas` varchar(45) DEFAULT NULL,
  `foto` text,
  `status` int(11) DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  `coordenada_long` varchar(12) DEFAULT NULL,
  `coordenada_lat` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancha_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_cancha_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancha`
--

LOCK TABLES `cancha` WRITE;
/*!40000 ALTER TABLE `cancha` DISABLE KEYS */;
INSERT INTO `cancha` VALUES (1,'Hermanos Ghersi Páez',16000,'Grama Natural','',NULL,1,5,'-67.612204','10.258729'),(2,'Olímpico de La UCV',0,'Grama Natural','','cancha1406127159314',1,6,'-66.885811','10.491344'),(3,'Misael Delgado',10000,'Grama Artificial','','cancha1406128189894',1,7,'-68.010862','10.223347'),(4,'Metropolitano de Cabudare',45312,'Grama Natural','110 x 70','cancha1406129478856',1,8,'-69.2196816','9.9936043'),(5,'José Antonio Anzoátegui',40000,'Grama Natural','','cancha1406130395967',1,9,'-64.656239','10.179736'),(6,'Pueblo Nuevo',38755,'Grama Natural','','cancha1406132738210',1,10,'-72.204337','7.7818573'),(7,'Metropolitano de Mérida',42200,'Grama Natural','','cancha1406133431601',1,11,'-71.217999','8.556295'),(8,'Rafael Calles Pinto',13000,'Grama Natural','','cancha1406134381421',1,12,'-69.765968','9.035276'),(9,'CTE Cachamay',41600,'Grama Natural','105 x 70','cancha1406135046931',1,13,'-62.696256','8.312642'),(10,'José Antonio Páez',14000,'Grama Natural','105,4 x 67,5','cancha1406135816942',1,14,'-69.210376','9.571097'),(11,'José Alberto Pérez',25000,'Grama Natural','','cancha1406136452788',1,15,'-70.602307','9.33341'),(12,'Antonio José de Sucre',13000,'Grama Natural','','cancha1406137713381',1,16,'-67.6182063','5.6654696'),(13,'Agustín Tovar La Carolina',24396,'Grama Natural','','cancha1406138990641',1,17,'-70.207486','8.624387'),(14,'\"Pachencho\" Romero',46000,'Grama Natural','','cancha1406139831197',1,18,'-71.644989','10.673966'),(15,'Brígido Iriarte',8000,'Grama Natural','','cancha1406141046348',1,19,'-66.941839','10.482981'),(16,'Ramón \"Gato\" Hernández',0,'','',NULL,1,45,'',''),(17,'Florentino Oropeza',0,'','',NULL,1,46,'-68.756485','10.332349'),(18,'Alfredo Simonpietri',0,'','',NULL,1,48,'-67.3752019','8.9369069'),(19,'Monumental de Maturín',0,'','',NULL,1,49,'-63.265036','9.708024'),(20,'Polideportivo El Gallo',0,'','',NULL,1,50,'-62.649737','8.362136'),(21,'Cocodrilos Sport Park',0,'','',NULL,1,51,'-66.941307','10.475316'),(22,'Farid Richá',0,'','',NULL,1,52,'-69.328913','10.080649'),(23,'Jesús Uribe',0,'','',NULL,1,53,'-68.20183','10.486252'),(24,'Reinaldo Melo',0,'','',NULL,1,54,'',''),(25,'Bicentario',0,'','',NULL,1,69,'','');
/*!40000 ALTER TABLE `cancha` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-14  9:22:53
