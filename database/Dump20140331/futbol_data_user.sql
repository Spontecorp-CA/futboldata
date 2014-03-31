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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(80) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `perfil_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_perfil1_idx` (`perfil_id`),
  CONSTRAINT `fk_user_perfil1` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Juan','Admin','jadmin','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','jadmin@loquesea.com','1234567',1,1),(4,'Joseba','Baroja','jbaroja','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','skjfhas','21343214',1,1),(9,'Ruben','Sarabia','rsarabia','525ba009c0050360237e8952a3ba6b475617c97a1fa6629bee0ea43b36dd4b63','rsarabia@spontecorp.com','8558166693',1,1),(10,'Gustavo','Vera','vera','525ba009c0050360237e8952a3ba6b475617c97a1fa6629bee0ea43b36dd4b63','ouetywroyt','976523465',1,1),(11,'Alejandro','González','alejandro','1732d2323c8408337e2d77c192b6160980e6a68cfd4422712c3b19c4290819cb','agonzales@loquesea.com','(0212) 456.7891',0,1),(12,'Luis','Barrera','lbarrera','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','luis.barrera@lkhjdsf.com','(0212) 555.5555',1,1),(13,'Pedro','Perez','pperez','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','pperez@lhgdsf.com','(0212) 789.4561',1,1),(14,'David','Reyes','dreyes','c3380624269d6e6024318bc1c86f3a338a2ed8af9eef8ac64a4e9170e0806a4a','dreyes@uywter.com','(0212) 555.7894',1,1),(15,'Felix','Angulo','feliz','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','felix@fads.com','(0254) 255.1246',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-31 10:49:51
