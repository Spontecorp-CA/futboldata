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
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `direccion` text,
  `pagina_web` varchar(80) DEFAULT NULL,
  `latitud` int(11) DEFAULT NULL,
  `longitud` int(11) DEFAULT NULL,
  `ciudad_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_direccion_ciudad1_idx` (`ciudad_id`),
  CONSTRAINT `fk_direccion_ciudad1` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudad` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (5,'Avenida Campo Elías Norte',NULL,NULL,NULL,13),(6,'Ciudad Universitaria (UCV)',NULL,NULL,NULL,14),(7,'Av. Andrés Eloy Blanco',NULL,NULL,NULL,15),(8,'Autopista Barquisimeto-Acarigua',NULL,NULL,NULL,16),(9,'Urb. Las Garzas (Barcelona).',NULL,NULL,NULL,17),(10,'San Cristóbal -Pueblo Nuevo.',NULL,NULL,NULL,18),(11,'Urbanización La Mara-Mérida',NULL,NULL,NULL,19),(12,'Sector San José (Guanare)',NULL,NULL,NULL,20),(13,'Avenida Guayana de Puerto Ordaz',NULL,NULL,NULL,21),(14,'Araure - Portuguesa',NULL,NULL,NULL,22),(15,'Avenida Las Ferias',NULL,NULL,NULL,23),(16,'Urb. Marcelino Bueno',NULL,NULL,NULL,24),(17,'Avenida 15 Barinas',NULL,NULL,NULL,25),(18,'Avenida 22 (Maracaibo)',NULL,NULL,NULL,26),(19,'Avenida Estadio (Caracas)',NULL,NULL,NULL,14),(20,'Av. Sucre, Sector central del área comercial del Parque El Ejercito (Las Ballenas). Maracay, Estado Aragua.','http://araguafc.com',NULL,NULL,13),(21,'',NULL,NULL,NULL,NULL),(22,'Las Mercedes','',NULL,NULL,14),(23,'','',NULL,NULL,15),(24,'','',NULL,NULL,14),(25,'','',NULL,NULL,16),(26,'','',NULL,NULL,17),(27,'','',NULL,NULL,14),(28,'','',NULL,NULL,14),(29,'','',NULL,NULL,18),(30,'','',NULL,NULL,19),(31,'','',NULL,NULL,20),(32,'','',NULL,NULL,14),(33,'','',NULL,NULL,21),(34,'','',NULL,NULL,22),(35,'','',NULL,NULL,23),(36,'','',NULL,NULL,24),(37,'','',NULL,NULL,25),(38,'','',NULL,NULL,26),(39,'Av. Santos Erminy, 1ra Calle Las Delicias, Torre Mega II, PHB, Sabana Grande.','',NULL,NULL,14),(40,'Av. Santos Erminy Torre Mega II Sabana Grande.','',NULL,NULL,14),(41,'',NULL,NULL,NULL,NULL),(42,'',NULL,NULL,NULL,NULL),(43,'','',NULL,NULL,27),(44,'','',NULL,NULL,27),(45,'El Vigía (Estado Mérida)',NULL,NULL,NULL,28),(46,'Avenida Alberto Ravell',NULL,NULL,NULL,29),(47,'Av. Santos Erminy, 1ra Calle Las Delicias Torre Mega II, PHB, Sabana Grande','http://www.federacionvenezolanadefutbol.org/ve',NULL,NULL,14),(48,'Calabozo (Fte Cementerio Nuevo)',NULL,NULL,NULL,30),(49,'Av. Zona Industrial Maturín',NULL,NULL,NULL,31),(50,'Enlace Av. Libertador',NULL,NULL,NULL,32),(51,'Cota 905',NULL,NULL,NULL,14),(52,'Av. Libertador con calle 37',NULL,NULL,NULL,33),(53,'Calle Comercio con Callejón 2',NULL,NULL,NULL,34),(54,'Barinas',NULL,NULL,NULL,25),(55,'','',NULL,NULL,30),(56,'','',NULL,NULL,35),(57,'','',NULL,NULL,36),(58,'','',NULL,NULL,21),(59,'','',NULL,NULL,14),(60,'','',NULL,NULL,18),(61,'','',NULL,NULL,15),(62,'','',NULL,NULL,37),(63,'','',NULL,NULL,31),(64,'','',NULL,NULL,33),(65,'','',NULL,NULL,38),(66,'','',NULL,NULL,33),(67,'','',NULL,NULL,14),(68,'','',NULL,NULL,39),(69,'Ureña',NULL,NULL,NULL,39);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-14  9:22:58
