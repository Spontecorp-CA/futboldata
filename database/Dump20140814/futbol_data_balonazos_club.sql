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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `year_fundacion` varchar(4) DEFAULT NULL,
  `historia` longtext,
  `status` int(11) DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  `logo` text,
  PRIMARY KEY (`id`),
  KEY `fk_club_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_club_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Aragua Fútbol Club','2002','',1,20,NULL),(2,'Atlético Venezuela Club de Fútbol','2009','',1,22,NULL),(3,'Carabobo Fútbol Club','','',1,23,NULL),(4,'Caracas Fútbol Club','','',1,24,NULL),(5,'Club Deportivo Lara','','',1,25,NULL),(6,'Deportivo Anzoátegui Sport Club','','',1,26,NULL),(7,'Deportivo La Guaira','','',1,27,NULL),(8,'Deportivo Petare Fútbol Club','','',1,28,NULL),(9,'Deportivo Táchira Fútbol Club','','',1,29,NULL),(10,'Estudiantes de Mérida Fútbol Club','','',1,30,NULL),(11,'Llaneros de Guanare Fútbol Club','','',1,31,NULL),(12,'Metropolitanos Fútbol Club','','',1,32,NULL),(13,'ACCD Mineros de Guayana','','',1,33,NULL),(14,'Portuguesa Fútbol Club','','',1,34,NULL),(15,'Trujillanos Fútbol Club','','',1,35,NULL),(16,'Tucanes de Amazonas Fútbol Club','','',1,36,NULL),(17,'Zamora Fútbol Club','','',1,37,NULL),(18,'Zulia Fútbol Club','','',1,38,NULL),(19,'Fundación Atlético El Vigía Fútbol Club','','',1,43,NULL),(20,'Yaracuyanos Fútbol Club','','',1,44,NULL),(21,'Arroceros de Calabozo Fútbol Club','','',1,55,NULL),(22,'Angostura Fútbol Club','','',1,56,NULL),(23,'Atlético Socopó Fútbol Club','','',1,57,NULL),(24,'Diamantes de Guayana Fútbol Club','','',1,58,NULL),(25,'Estudiantes de Caracas Fútbol Club','','',1,59,NULL),(26,'Lotería del Táchira Fútbol Club','','',1,60,NULL),(27,'Gran Valencia Fútbol Club','','',1,61,NULL),(28,'Margarita Fútbol Club','','',1,62,NULL),(29,'Monagas Sport Club','','',1,63,NULL),(30,'Policía de Lara Fútbol Club','','',1,64,NULL),(31,'Unión Atlético Falcón','','',1,65,NULL),(32,'Unión Lara Sport Club','','',1,66,NULL),(33,'Universidad Central de Venezuela Fútbol Club','','',1,67,NULL),(34,'Ureña Sport Club','','',1,68,NULL);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-14  9:23:00
