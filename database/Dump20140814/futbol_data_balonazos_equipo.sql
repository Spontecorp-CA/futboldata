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
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `nombre_alterno` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `abreviacion` varchar(10) DEFAULT NULL,
  `logo` text,
  `club_id` int(10) unsigned NOT NULL,
  `categoria_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo_club1_idx` (`club_id`),
  KEY `fk_equipo_categoria1_idx` (`categoria_id`),
  CONSTRAINT `fk_equipo_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Aragua FC',NULL,1,'AFC','vacio',1,1),(2,'Atlético Venezuela',NULL,1,'AV','vacio',2,1),(3,'Carabobo FC',NULL,1,'CFC','vacio',3,1),(4,'Caracas FC',NULL,1,'CFC','vacio',4,1),(5,'Deportivo Lara',NULL,1,'CDL','vacio',5,1),(6,'Deportivo Anzoátegui',NULL,1,'DANZ','vacio',6,1),(7,'Deportivo La Guaira',NULL,1,'DLG','vacio',7,1),(8,'Deportivo Petare',NULL,1,'DP','vacio',8,1),(9,'Deportivo Táchira',NULL,1,'DT','vacio',9,1),(10,'Estudiantes de Mérida',NULL,1,'EDM','vacio',10,1),(11,'Llaneros de Guanare',NULL,1,'LLDG','vacio',11,1),(12,'Metropolitanos FC',NULL,1,'MFC','vacio',12,1),(13,'Mineros de Guayana',NULL,1,'MDG','vacio',13,1),(14,'Portuguesa FC',NULL,1,'PFC','vacio',14,1),(15,'Trujillanos FC',NULL,1,'TFC','vacio',15,1),(16,'Tucanes de Amazonas',NULL,1,'TDA','vacio',16,1),(17,'Zamora FC',NULL,1,'ZFC','vacio',17,1),(18,'Zulia FC',NULL,1,'ZFC','vacio',18,1),(19,'Yaracuyanos FC',NULL,1,'YFC','vacio',20,1),(20,'Atlético El Vigía',NULL,1,'AEV','vacio',19,1),(21,'Arroceros de Calabozo',NULL,1,'ADC','vacio',21,2),(22,'Margarita FC',NULL,1,'MFC','vacio',28,2),(23,'Monagas SC',NULL,1,'MSC','vacio',29,2),(24,'Estudiantes de Caracas',NULL,1,'EDC','vacio',25,2),(25,'Diamantes de Guayana',NULL,1,'DDG','vacio',24,2),(26,'Mineros de Guayana',NULL,1,'MDG','vacio',13,2),(27,'UCV FC',NULL,1,'UCV','vacio',33,2),(28,'Angostura FC',NULL,1,'AFC','vacio',22,2),(29,'Deportivo Anzoátegui',NULL,1,'DANZ','vacio',6,2),(30,'Gran Valencia',NULL,1,'GVFC','vacio',27,2),(31,'Carabobo FC',NULL,1,'CFC','vacio',3,2),(32,'Atlético Socopó',NULL,1,'AS','vacio',23,2),(33,'Policía de Lara',NULL,1,'PDL','vacio',30,2),(34,'Atlético El Vigía',NULL,1,'AEV','vacio',19,2),(35,'Ureña SC',NULL,1,'USC','vacio',34,2),(36,'Lotería del Táchira',NULL,1,'LDT','vacio',26,2),(37,'UA Falcón',NULL,1,'UAF','vacio',31,2),(38,'Unión Lara',NULL,1,'UL','vacio',32,2),(39,'Zamora FC',NULL,1,'ZFC','vacio',17,2),(40,'Yaracuyanos FC',NULL,1,'YFC','vacio',20,2);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-14  9:22:46
