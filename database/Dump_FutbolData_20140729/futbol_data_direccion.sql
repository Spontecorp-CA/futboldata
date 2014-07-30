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
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'Los Teques.','No tiene',NULL,NULL,3),(3,'La Redoma Palo Negro.','No tiene',NULL,NULL,16),(4,'Estadio \"Pachencho\" Romero.','No tiene',NULL,NULL,14),(5,'El Obelisco. Barquisimeto.','No tiene',NULL,NULL,1),(13,'Ujano','www.comc.vv',NULL,NULL,1),(14,'asdasdasdasd',NULL,NULL,NULL,1),(15,'Cruz Blanca',NULL,NULL,NULL,1),(16,NULL,NULL,NULL,NULL,NULL),(17,'asddassa',NULL,NULL,NULL,12),(18,'asddassa',NULL,NULL,NULL,12),(19,'asddassa',NULL,NULL,NULL,12),(20,'sdadsasdasdasd',NULL,NULL,NULL,2),(22,'ddssdsd',NULL,NULL,NULL,1),(23,'Aqui y alla',NULL,NULL,NULL,8),(24,'Catia.',NULL,NULL,NULL,3),(25,'Puerto Ordaz, Estado Bolívar.',NULL,NULL,NULL,15),(26,'Calle 245',NULL,NULL,NULL,12),(27,'Caracas',NULL,NULL,NULL,3),(28,'Av La Salle con calle Lima, Torre Phelps, Piso 19, Of 19-D, Los Caobos',NULL,NULL,NULL,3),(29,'Cruz Blanca',NULL,NULL,NULL,1),(30,'dfdfdfdfdf',NULL,NULL,NULL,2),(31,'dasdasdasd',NULL,NULL,NULL,2),(32,'ffffffffffffffff',NULL,NULL,NULL,2),(33,'Blabla',NULL,NULL,NULL,2),(34,'Valparaiso',NULL,NULL,NULL,9),(35,'Valparaiso',NULL,NULL,NULL,9),(36,'Holaaa',NULL,NULL,NULL,9),(37,'Valparaiso',NULL,NULL,NULL,9),(38,'Pueblo pequeño',NULL,NULL,NULL,2),(39,'Valparaiso',NULL,NULL,NULL,9),(40,'Pueblo pequeño',NULL,NULL,NULL,2),(41,'Cumbres de Curumo',NULL,NULL,NULL,3),(42,NULL,NULL,NULL,NULL,NULL),(43,NULL,NULL,NULL,NULL,NULL),(44,NULL,NULL,NULL,NULL,NULL),(45,NULL,NULL,NULL,NULL,NULL),(48,'Plaza venezu','asdasd',NULL,NULL,2),(49,'Plaza venezuela','asdasd',NULL,NULL,2),(50,'Plaza venezuela','asdasd',NULL,NULL,2),(51,'Estadio Misael Delgado.','No Tiene',NULL,NULL,2),(52,'Ujano, al lado de juxos','www.comc.vv',NULL,NULL,1),(53,'Ujano, al lado de juxos','www.comc.vv',NULL,NULL,1),(54,'Cota 905, Cocodrilos Sport Park','www.caracasfutbolclub.com',NULL,NULL,3),(57,'blabla',NULL,NULL,NULL,1),(58,'Plaza venezuela','asdasd',NULL,NULL,2),(59,'Plaza venezuela','asdasd',NULL,NULL,2),(60,'Polideportivo Cachamay.........','www.delcampofc.com',NULL,NULL,3),(61,'Plaza venezuela','asdasd',NULL,NULL,2),(62,'Plaza venezuela','asdasd',NULL,NULL,2),(63,'Plaza venezuela','asdasd',NULL,NULL,2),(64,'Las Mercedes.','asdasd',NULL,NULL,2),(65,'La Pastora.',NULL,NULL,NULL,3),(66,'Baruta.',NULL,NULL,NULL,3),(67,'Cualquier otra','www.laprimera.com',NULL,NULL,3),(68,'Cualquier otra','www.laprimera.com',NULL,NULL,3),(69,'Torre Mega II, PHB Sabana Grande.','www.laprimera.com',NULL,NULL,3),(70,'Calle 5 con 26',NULL,NULL,NULL,25),(71,'Cerca de la Playa colorada.',NULL,NULL,NULL,23),(72,'en algun lugar de la mancha','',NULL,NULL,2),(73,'en algun lugar de la mancha','',NULL,NULL,2),(74,'Torre Mega II, PHB Sabana Grande.','No Tiene',NULL,NULL,3),(75,'cerca de la estación del metro de bellas artes','wwww.la3ra.com',NULL,NULL,3),(77,'otra mas','fdsfsdf',NULL,NULL,3),(78,'otra mas larga','fdsfsdf',NULL,NULL,3),(83,'La Guacamaya, Las Mercedes.','No Tiene',NULL,NULL,3),(84,'Torre Mega II, PHB Sabana Grande.','No Tiene',NULL,NULL,3),(85,'Asunción, Paraguay','',NULL,NULL,26),(86,'Asunción, Paraguay','www.conmebol.com',NULL,NULL,26),(87,'P.O. Box 8044 Zurich, Switzerland.','',NULL,NULL,29),(88,'lkjhasdkjh','',NULL,NULL,3),(89,'otra mas larga','fdsfsdf',NULL,NULL,3),(90,'otra mas larga','fdsfsdf',NULL,NULL,3),(91,'otra mas larga','fdsfsdf',NULL,NULL,3),(92,'otra mas larga','fdsfsdf',NULL,NULL,3),(93,'otra mas larga','fdsfsdf',NULL,NULL,3),(94,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(95,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(96,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(97,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(98,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(99,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(100,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(101,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(102,'Colegio Jefferson.','No Tiene',NULL,NULL,38),(103,'Ciudad Universitaria, Los Chaguaramos',NULL,NULL,NULL,3),(104,'Las Mercedes, Calle La Cinta',NULL,NULL,NULL,3),(105,'Aqui ',NULL,NULL,NULL,8),(106,'Aqui ',NULL,NULL,NULL,8),(107,'Estadio Brigido Iriarte-El Paraíso','No Tiene',NULL,NULL,3),(108,'Altamira',NULL,NULL,NULL,3),(109,'rancho bajo',NULL,NULL,NULL,9),(110,'Santa Rosa',NULL,NULL,NULL,3),(111,'La Tahona.',NULL,NULL,NULL,3),(112,'Estadio Florentino Oropeza.','No Tiene',NULL,NULL,18),(113,'Colegio Jefferson.','',NULL,NULL,38),(114,'La Tahona.',NULL,NULL,NULL,3),(115,'La Pastora',NULL,NULL,NULL,3),(116,'Prados del Este',NULL,NULL,NULL,3),(117,'',NULL,NULL,NULL,14),(118,'Torre Mega, Sábana Grande, Caracas.','http://www.federacionvenezolanadefutbol.org',NULL,NULL,3),(119,'Calle Venezuela.',NULL,NULL,NULL,3),(120,'Trujillo.',NULL,NULL,NULL,14),(121,'Buenos Aires.',NULL,NULL,NULL,13),(122,'La Trinidad.',NULL,NULL,NULL,3),(123,'Cota 905.',NULL,NULL,NULL,3),(124,'Centro Italo.',NULL,NULL,NULL,3),(125,'Barrio Obrero.',NULL,NULL,NULL,3),(126,'Nuevo Horizonte.',NULL,NULL,NULL,3),(127,'Machiques.',NULL,NULL,NULL,14),(128,'Obelisco.',NULL,NULL,NULL,1),(129,'Terrazas del Avila.',NULL,NULL,NULL,3),(130,'Pueblo Nuevo, San Cristóbal',NULL,NULL,NULL,12),(131,'Av. Caracas con Av. Guayanas Ciudad Guayana',NULL,NULL,NULL,15),(132,'Asunción, Paraguay','www.conmebol.com',NULL,NULL,26),(133,'Metropolitano de Cabudare','No tiene',NULL,NULL,1),(134,'Una dirección loca',NULL,NULL,NULL,14),(135,'Manzanares',NULL,NULL,NULL,3),(138,'',NULL,NULL,NULL,NULL),(139,'',NULL,NULL,NULL,NULL),(140,'Av. Estadio, El Paraiso.',NULL,NULL,NULL,3),(141,'Parque del Este.',NULL,NULL,NULL,3),(142,'',NULL,NULL,NULL,3),(143,'',NULL,NULL,NULL,1),(144,'',NULL,NULL,NULL,1),(145,'',NULL,NULL,NULL,1),(146,'Pueblo Nuevo.','',NULL,NULL,12),(147,'Guasdualito, Estado Apure, Venezuela.',NULL,NULL,NULL,20),(148,'Zulia',NULL,NULL,NULL,14),(149,'Municipio Petare','http://deportivopetarefc.com',NULL,NULL,3),(150,'La Guacamaya, Caracas.','http://www.deportivolaguaira.com',NULL,NULL,28),(151,'Brígido Iriarte.','http://www.metropolitanosfc.com',NULL,NULL,3),(152,'Estadio Hermanos Ghersi','http://araguafc.com',NULL,NULL,16),(153,'Valera ','http://trujillanos-fc.com.ve',NULL,NULL,20),(154,'Metropolitano de Mérida','No Tiene',NULL,NULL,23),(155,'La Carolina','http://zamorafc.com',NULL,NULL,19),(156,'Rafael Calles Pinto','No Tiene',NULL,NULL,22),(157,'Pachencho Romero','http://fczulia.com',NULL,NULL,14),(158,'Antonio José de Sucre','No Tiene',NULL,NULL,17),(159,'José Antonio Anzoátegui','No Tiene',NULL,NULL,21),(160,'José Antonio Páez','No tiene',NULL,NULL,25),(161,'Pueblo Nuevo.','No Tiene',NULL,NULL,12),(162,'P.O. Box 8044 Zurich, Switzerland ','http://es.fifa.com',NULL,NULL,29),(163,'Copacabana','',NULL,NULL,NULL),(164,'México DF','No Tiene',NULL,NULL,11),(165,'','',NULL,NULL,NULL),(166,'','',NULL,NULL,NULL),(167,'','',NULL,NULL,NULL),(168,'','',NULL,NULL,NULL),(169,'','',NULL,NULL,NULL),(170,'','',NULL,NULL,NULL),(171,'','',NULL,NULL,NULL),(172,'','',NULL,NULL,NULL),(173,'','',NULL,NULL,NULL),(174,'','',NULL,NULL,NULL),(175,'','',NULL,NULL,NULL),(176,'','',NULL,NULL,NULL),(177,'','',NULL,NULL,NULL),(178,'','',NULL,NULL,NULL),(179,'','',NULL,NULL,NULL),(180,'','',NULL,NULL,NULL),(181,'','',NULL,NULL,NULL),(182,'','',NULL,NULL,NULL),(183,'','',NULL,NULL,NULL),(184,'','',NULL,NULL,NULL),(185,'','',NULL,NULL,NULL),(186,'','',NULL,NULL,NULL),(187,'','',NULL,NULL,NULL),(188,'','',NULL,NULL,NULL),(189,'','',NULL,NULL,NULL),(190,'','',NULL,NULL,NULL),(191,'','',NULL,NULL,NULL),(192,'','',NULL,NULL,NULL),(193,'','',NULL,NULL,NULL),(194,'','',NULL,NULL,NULL),(195,'Río de Janeiro, Brasil.',NULL,NULL,NULL,30),(196,'Autopista Barquisimeto - Acarigua',NULL,NULL,NULL,1),(197,'Valera.',NULL,NULL,NULL,20),(198,'Maracay',NULL,NULL,NULL,16),(199,'',NULL,NULL,NULL,3),(200,'',NULL,NULL,NULL,3),(201,'Carabobo, Valencia.',NULL,NULL,NULL,3),(202,'',NULL,NULL,NULL,3),(203,'',NULL,NULL,NULL,23),(204,'',NULL,NULL,NULL,18),(205,'',NULL,NULL,NULL,23),(206,'',NULL,NULL,NULL,3),(207,'',NULL,NULL,NULL,3),(208,'',NULL,NULL,NULL,3),(209,'',NULL,NULL,NULL,12),(210,'',NULL,NULL,NULL,12),(211,'',NULL,NULL,NULL,20),(212,'Medellín',NULL,NULL,NULL,31),(213,'',NULL,NULL,NULL,2),(214,'Cumaná.',NULL,NULL,NULL,32),(215,'',NULL,NULL,NULL,1),(216,'',NULL,NULL,NULL,33),(217,'',NULL,NULL,NULL,14),(218,'Maturín.',NULL,NULL,NULL,34),(219,'',NULL,NULL,NULL,15),(220,'',NULL,NULL,NULL,3),(221,'',NULL,NULL,NULL,35),(222,'',NULL,NULL,NULL,34),(223,'',NULL,NULL,NULL,36),(224,'',NULL,NULL,NULL,2),(225,'',NULL,NULL,NULL,18),(226,'',NULL,NULL,NULL,32),(227,'Ciudad Bolívar',NULL,NULL,NULL,37),(228,'',NULL,NULL,NULL,16),(229,'Caracas',NULL,NULL,NULL,3),(230,'Caracas',NULL,NULL,NULL,3),(233,'Caracas',NULL,NULL,NULL,3),(234,'Justino Posse',NULL,NULL,NULL,13),(235,'Caracas',NULL,NULL,NULL,3),(236,'El Paraíso.','No tiene',NULL,NULL,3),(237,'La Tahona','No tiene',NULL,NULL,38),(238,'Chuao','',NULL,NULL,38),(239,'Campo Alegre','',NULL,NULL,38),(240,'Maripérez ','No tiene',NULL,NULL,3),(241,'El Peñon','No tiene',NULL,NULL,38),(242,'Montalbán','No tiene',NULL,NULL,3),(243,'La Florida','No tiene',NULL,NULL,3),(244,'Cumbres de Curumo','No tiene',NULL,NULL,38),(245,'Universidad Santa María','No tiene',NULL,NULL,38),(246,'La Castellana-Chacao','No tiene',NULL,NULL,38),(247,'Las Mercedes','No tiene',NULL,NULL,38),(248,'Guarenas','No tiene',NULL,NULL,38),(249,'',NULL,NULL,NULL,NULL),(250,'',NULL,NULL,NULL,NULL),(251,'',NULL,NULL,NULL,NULL),(252,'',NULL,NULL,NULL,NULL),(253,'',NULL,NULL,NULL,NULL),(254,'',NULL,NULL,NULL,NULL),(255,'Charallave','',NULL,NULL,3),(256,'','',NULL,NULL,3),(257,'','',NULL,NULL,3),(258,'','',NULL,NULL,3),(259,'','',NULL,NULL,3),(260,'','',NULL,NULL,NULL),(261,'','',NULL,NULL,NULL),(262,'','',NULL,NULL,NULL),(263,'','',NULL,NULL,NULL),(264,'',NULL,NULL,NULL,NULL);
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

-- Dump completed on 2014-07-29 11:20:39
