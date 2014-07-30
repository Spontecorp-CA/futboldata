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
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `direccion_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_email_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_email_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=591 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'f.v.f@gobierno.ve.gob',1),(2,'felix@futbol.com',3),(4,'hola@gmgmm.com',4),(6,'sadd@hola.mundo',3),(9,'paz@gmailcc.com',5),(10,'amor@gmailcc.com',5),(11,'ndand@gmail.com',13),(12,'32112@gmail.com',13),(13,'eaidfad@gmail.com',48),(14,'eaidfad@gmail.com',49),(15,'eaidfad@gmail.com',50),(16,'rwerwe@sdfdsf.com',50),(18,'32112@gmail.com',52),(19,'ndand@gmail.com',52),(20,'32112@gmail.com',53),(21,'ndand@gmail.com',53),(25,'eaidfad@gmail.com',58),(26,'rwerwe@sdfdsf.com',58),(27,'eaidfad@gmail.com',59),(28,'eaidfad@gmail.com',59),(29,'rwerwe@sdfdsf.com',59),(30,'delcampo@delcampofc.com',60),(31,'eaidfad@gmail.com',61),(32,'eaidfad@gmail.com',61),(33,'rwerwe@sdfdsf.com',61),(34,'rwerwe@sdfdsf.com',62),(35,'eaidfad@gmail.com',62),(36,'eaidfad@gmail.com',62),(37,'eaidfad@gmail.com',62),(38,'eaidfad@gmail.com',63),(39,'eaidfad@gmail.com',63),(40,'eaidfad@gmail.com',63),(41,'rwerwe@sdfdsf.com',63),(46,'kjghsdfkjh@kjhsdfjh.com',68),(47,'lasegunda@loquesea.com',73),(49,'elmail@loquesea.com',91),(50,'dgdfgfd@gsaidjk.com',97),(51,'effdsfds@hanmil.com',101),(52,'asocdcapital@gmail.com',107),(95,'asd@asd.com',51),(140,'rwerwe@sdfdsf.com',64),(143,'eaidfad@gmail.com',64),(590,'asdasd@gmail.com',1);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-29 11:20:46
