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
  CONSTRAINT `fk_equipo_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Caracas FC',NULL,1,'CFC','equipo1403443102407',1,8),(2,'Caracas FC',NULL,1,'CFC','equipo1403443137289',1,4),(3,'Caracas FC',NULL,1,'CFC','equipo1403443181179',1,2),(4,'Mineros de Guayana',NULL,1,'MG','equipo1403456795840',5,8),(5,'Atlético Venezuela',NULL,1,'AV','equipo1402591927696',3,8),(6,'Carabobo FC',NULL,1,'CFC','equipo1403364849904',4,8),(7,'Deportivo Lara',NULL,1,'DL','equipo1403492833533',6,8),(8,'Deportivo Táchira',NULL,1,'DT','equipo1403611092410',7,8),(9,'Deportivo Petare',NULL,1,'DP','equipo1402627801355',8,8),(10,'Deportivo La Guaira',NULL,1,'DLG','equipo1403532691865',9,8),(11,'Metropolitanos FC',NULL,1,'MFC','equipo1402673999368',10,8),(12,'Aragua FC',NULL,1,'AFC','equipo1402682551125',11,8),(13,'Trujillanos FC',NULL,1,'TFC','equipo1402685157612',12,8),(14,'Estudiantes de Mérida',NULL,1,'EDM','equipo1402685615575',13,8),(15,'Zamora FC',NULL,1,'ZFC','equipo1402686196261',14,8),(16,'Llaneros de Guanare ',NULL,1,'LLDG','equipo1402690090358',15,8),(17,'Zulia FC',NULL,1,'ZFC','equipo1402690661403',16,8),(18,'Tucanes de Amazonas',NULL,1,'TDA','equipo1402693973249',17,8),(19,'Deportivo Anzoátegui',NULL,1,'DANZ','equipo1402694611357',18,8),(20,'Portuguesa FC',NULL,1,'PFC','equipo1402694967738',19,8),(21,'Brasil',NULL,1,'BRA','equipo1403093126045',20,8),(22,'México',NULL,1,'MEX','equipo1403094476303',21,8),(23,'Camerún',NULL,1,'CAM','equipo1403094545068',22,8),(24,'Croacia',NULL,1,'CRO','equipo1403094615420',23,8),(25,'Holanda',NULL,1,'HOL','equipo1403094665020',24,8),(26,'Chile',NULL,1,'CHI','equipo1403094705799',25,8),(27,'Australia',NULL,1,'AUS','equipo1403094747148',26,8),(28,'España',NULL,1,'ESP','equipo1403094787854',27,8),(29,'Colombia',NULL,1,'COL','equipo1403094830743',28,8),(30,'Costa de Marfil',NULL,1,'CDM','equipo1403094870627',29,8),(31,'Japón',NULL,1,'JAP','equipo1403094943161',30,8),(32,'Grecia',NULL,1,'GRE','equipo1403094984141',31,8),(33,'Costa Rica',NULL,1,'CR','equipo1403095031797',32,8),(34,'Italia',NULL,1,'ITA','equipo1403095074748',33,8),(35,'Inglaterra',NULL,1,'ING','equipo1403095120328',34,8),(36,'Uruguay',NULL,1,'URU','equipo1403095161769',35,8),(37,'Francia',NULL,1,'FRA','equipo1403095228579',36,8),(38,'Suiza',NULL,1,'SUI','equipo1403095280482',37,8),(39,'Ecuador',NULL,1,'ECU','equipo1403095320048',38,8),(40,'Honduras',NULL,1,'HON','equipo1403095379604',39,8),(41,'Argentina',NULL,1,'ARG','vacio',40,8),(42,'EE UU',NULL,1,'USA','equipo1403095499761',45,8),(43,'Ghana',NULL,1,'GHA','equipo1403095553038',46,8),(44,'Portugal',NULL,1,'POR','equipo1403095607504',47,8),(45,'Bélgica',NULL,1,'BEL','equipo1403095672528',48,8),(46,'Corea del Sur',NULL,1,'CDS','equipo1403095806769',49,8),(47,'Rusia',NULL,1,'RUS','equipo1403095864548',50,8),(48,'Argelia',NULL,1,'ARG','equipo1403095910309',51,8),(49,'Bosnia',NULL,1,'BOS','equipo1403736121099',43,8),(50,'Irán',NULL,1,'IRA','equipo1403736781801',41,8),(51,'Nigeria',NULL,1,'NIG','equipo1403736846949',42,8),(52,'Alemania',NULL,1,'ALE','equipo1403797222382',44,8),(53,'Mineros de Guayana',NULL,1,'MG','equipo1404829268135',5,2),(54,'Mineros de Guayana',NULL,1,'MG','equipo1404829325137',5,4),(55,'Atlético Venezuela',NULL,1,'AV','equipo1404829372875',3,2),(56,'Atlético Venezuela',NULL,1,'AV','equipo1404829411284',3,4),(57,'Aragua FC',NULL,1,'AFC','equipo1404829477382',11,2),(58,'Aragua FC',NULL,1,'AFC','equipo1404829512368',11,4),(59,'Carabobo FC',NULL,1,'CFC','vacio',4,2),(60,'Carabobo FC',NULL,1,'CFC','vacio',4,4),(61,'Deportivo Lara',NULL,1,'CDL','vacio',6,2),(62,'Deportivo Lara',NULL,1,'CDL','vacio',6,4),(63,'Deportivo Petare',NULL,1,'CDP','vacio',8,2),(64,'Deportivo Petare',NULL,1,'CDP','vacio',8,4),(65,'Deportivo La Guaira',NULL,1,'DLG','vacio',9,2),(66,'Deportivo La Guaira',NULL,1,'DLG','vacio',9,4);
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

-- Dump completed on 2014-07-16 14:37:31
