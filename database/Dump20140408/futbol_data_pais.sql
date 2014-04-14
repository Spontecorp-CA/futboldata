CREATE DATABASE  IF NOT EXISTS `futbol_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 128.32.16.3    Database: futbol_data
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
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Venezuela'),(2,'Australia'),(3,'China'),(4,'Japan'),(5,'Thailand'),(6,'India'),(7,'Malaysia'),(8,'Kore'),(9,'Hong Kong'),(10,'Taiwan'),(11,'Philippines'),(12,'Vietnam'),(13,'France'),(14,'Europe'),(15,'Germany'),(16,'Sweden'),(17,'Italy'),(18,'Greece'),(19,'Spain'),(20,'Austria'),(21,'United Kingdom'),(22,'Netherlands'),(23,'Belgium'),(24,'Switzerland'),(25,'United Arab Emirates'),(26,'Israel'),(27,'Ukraine'),(28,'Russian Federation'),(29,'Kazakhstan'),(30,'Portugal'),(31,'Saudi Arabia'),(32,'Denmark'),(33,'Ira'),(34,'Norway'),(35,'United States'),(36,'Mexico'),(37,'Canada'),(38,'Syrian Arab Republic'),(39,'Cyprus'),(40,'Czech Republic'),(41,'Iraq'),(42,'Turkey'),(43,'Romania'),(44,'Lebanon'),(45,'Hungary'),(46,'Georgia'),(47,'Brazil'),(48,'Azerbaijan'),(49,'Satellite Provider'),(50,'Palestinian Territory'),(51,'Lithuania'),(52,'Oman'),(53,'Slovakia'),(54,'Serbia'),(55,'Finland'),(56,'Iceland'),(57,'Bulgaria'),(58,'Slovenia'),(59,'Moldov'),(60,'Macedonia'),(61,'Liechtenstein'),(62,'Jersey'),(63,'Poland'),(64,'Croatia'),(65,'Bosnia and Herzegovina'),(66,'Estonia'),(67,'Latvia'),(68,'Jordan'),(69,'Kyrgyzstan'),(70,'Reunion'),(71,'Ireland'),(72,'Libya'),(73,'Luxembourg'),(74,'Armenia'),(75,'Virgin Island'),(76,'Yemen'),(77,'Belarus'),(78,'Gibraltar'),(79,'Martinique'),(80,'Panama'),(81,'Dominican Republic'),(82,'Guam'),(83,'Puerto Rico'),(84,'Virgin Island'),(85,'Mongolia'),(86,'New Zealand'),(87,'Singapore'),(88,'Indonesia'),(89,'Nepal'),(90,'Papua New Guinea'),(91,'Pakistan'),(92,'Bahamas'),(93,'Saint Lucia'),(94,'Argentina'),(95,'Bangladesh'),(96,'Tokelau'),(97,'Cambodia'),(98,'Macau'),(99,'Maldives'),(100,'Afghanistan'),(101,'New Caledonia'),(102,'Fiji'),(103,'Wallis and Futuna'),(104,'Qatar'),(105,'Albania'),(106,'Belize'),(107,'Uzbekistan'),(108,'Kuwait'),(109,'Montenegro'),(110,'Peru'),(111,'Bermuda'),(112,'Curacao'),(113,'Colombia'),(114,'Chile'),(115,'Ecuador'),(116,'South Africa'),(117,'Isle of Man'),(118,'Bolivia'),(119,'Guernsey'),(120,'Malta'),(121,'Tajikistan'),(122,'Seychelles'),(123,'Bahrain'),(124,'Egypt'),(125,'Zimbabwe'),(126,'Liberia'),(127,'Kenya'),(128,'Ghana'),(129,'Nigeria'),(130,'Tanzani'),(131,'Zambia'),(132,'Madagascar'),(133,'Angola'),(134,'Namibia'),(135,'Cote D\'Ivoire'),(136,'Sudan'),(137,'Cameroon'),(138,'Malawi'),(139,'Gabon'),(140,'Mali'),(141,'Benin'),(142,'Chad'),(143,'Botswana'),(144,'Cape Verde'),(145,'Rwanda'),(146,'Congo'),(147,'Uganda'),(148,'Mozambique'),(149,'Gambia'),(150,'Lesotho'),(151,'Mauritius'),(152,'Morocco'),(153,'Algeria'),(154,'Guinea'),(155,'Cong'),(156,'Swaziland'),(157,'Burkina Faso'),(158,'Sierra Leone'),(159,'Somalia'),(160,'Niger'),(161,'Central African Republic'),(162,'Togo'),(163,'Burundi'),(164,'Equatorial Guinea'),(165,'South Sudan'),(166,'Senegal'),(167,'Mauritania'),(168,'Djibouti'),(169,'Comoros'),(170,'British Indian Ocean Territory'),(171,'Tunisia'),(172,'Greenland'),(173,'Holy See (Vatican City State)'),(174,'Costa Rica'),(175,'Cayman Islands'),(176,'Jamaica'),(177,'Guatemala'),(178,'Marshall Islands'),(179,'Antarctica'),(180,'Barbados'),(181,'Aruba'),(182,'Monaco'),(183,'Anguilla'),(184,'Saint Kitts and Nevis'),(185,'Grenada'),(186,'Paraguay'),(187,'Montserrat'),(188,'Turks and Caicos Islands'),(189,'Antigua and Barbuda'),(190,'Tuvalu'),(191,'French Polynesia'),(192,'Solomon Islands'),(193,'Vanuatu'),(194,'Eritrea'),(195,'Trinidad and Tobago'),(196,'Andorra'),(197,'Haiti'),(198,'Saint Helena'),(199,'Micronesi'),(200,'El Salvador'),(201,'Honduras'),(202,'Uruguay'),(203,'Sri Lanka'),(204,'Western Sahara'),(205,'Christmas Island'),(206,'Samoa'),(207,'Suriname'),(208,'Cook Islands'),(209,'Kiribati'),(210,'Niue'),(211,'Tonga'),(212,'Mayotte'),(213,'Norfolk Island'),(214,'Brunei Darussalam'),(215,'Turkmenistan'),(216,'Pitcairn Islands'),(217,'San Marino'),(218,'Aland Islands'),(219,'Faroe Islands'),(220,'Svalbard and Jan Mayen'),(221,'Cocos (Keeling) Islands'),(222,'Nauru'),(223,'South Georgia and the South Sandwich Islands'),(224,'United States Minor Outlying Islands'),(225,'Guinea-Bissau'),(226,'Palau'),(227,'American Samoa'),(228,'Bhutan'),(229,'French Guiana'),(230,'Guadeloupe'),(231,'Saint Martin'),(232,'Saint Vincent and the Grenadines'),(233,'Saint Pierre and Miquelon'),(234,'Saint Barthelemy'),(235,'Dominica'),(236,'Sao Tome and Principe'),(237,'Kore'),(238,'Falkland Islands (Malvinas)'),(239,'Northern Mariana Islands'),(240,'Timor-Leste'),(241,'Bonair'),(242,'Myanmar'),(243,'Nicaragua'),(244,'Sint Maarten (Dutch part)'),(245,'Guyana'),(246,'Lao People\'s Democratic Republic'),(247,'Cuba'),(248,'Ethiopia');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-08 17:26:11
