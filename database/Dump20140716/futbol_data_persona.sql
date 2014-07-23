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
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `apodo` varchar(45) DEFAULT NULL,
  `documento_identidad` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nacionalidad` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `foto` text,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_persona_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_persona_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (11,'Alejandro','Guerra','M','','11954911','2014-01-03','Venezuela','(4352) 425.4345','(1232) 412.2212','felixe@gasd.com',NULL,'jugador1401896753856',24),(12,'Edgar','Jiménez','M','','4.615.101','2014-01-03','Venezuela','(1111) 111.1111','(2222) 222.2222','fggaffa@gmail.com',NULL,'jugador1401897475576',25),(13,'Julio','Machado','M','','54615101','2014-01-03','Venezuela','(3333) 333.3333','(9897) 879.7944','fggaffa@gmail.com',NULL,'jugador1401902052007',26),(14,'Bryan José','Martin Gammiero','M','','00003','1993-01-02','Venezuela','','','',NULL,'jugador1404137331936',27),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'José','Ángel','M',NULL,'18111459','1987-01-30','Venezuela','(0212) 442.6577','04143517291','',NULL,NULL,29),(18,'Hola ','Cha','',NULL,'123456','2014-01-01','Algeria','(1231) 313.1313','32131313','jose@asd.com',NULL,NULL,30),(19,'asdasda','dadada','M',NULL,'18123123','2014-01-15','Algeria','(1231) 123.1321','123123123',NULL,NULL,NULL,31),(20,'ere','asdasdasd','M',NULL,'12312313',NULL,'American Samoa','(1333) 111.3333','12312313','',NULL,NULL,32),(21,'asdasda','asdasdasd','F',NULL,'1231231','2014-01-01','American Samoa','(1232) 123.1321','123123123',NULL,NULL,NULL,33),(29,'Daniel','Febles','M','','7.987.098','2014-01-14','Venezuela','(1231) 221.2112','(1231) 212.1221','FAHOLA@GMAIL.COM',NULL,'jugador1401903078988',41),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,42),(31,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,43),(32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,44),(33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,45),(36,'Hola','Chao','F',NULL,'1234','2014-01-01','','(1231) 231.2312','(1231) 231.2312','asd@asd.com',NULL,NULL,57),(37,'Héctor','Pérez','M','\"Tico\"','1997114','2014-01-15','Venezuela','','','jua.qdm@cc.com',NULL,'jugador1400599266685',65),(38,'Miguel','Mea Vitali','M','\"Miky\"','13.984.489','1981-01-19','Venezuela','','','aismd@giasi.com',NULL,'jugador1400599621479',66),(39,'Giácomo','Di Giorgi','M','\"El perfecto\"','4847989','1981-01-24','Venezuela','','','juan.dongua@gotmail.com',NULL,'jugador1400600677688',70),(40,'Cristian','Flores','M','','8778989','1988-01-03','Venezuela','','','oso.peligroso@gmail.com',NULL,'jugador1400601111982',71),(41,'María ','Marchena','M',NULL,'45544','2014-01-20','Cambodia','(1231) 231.3131','(1231) 231.2312','asd@asd.com',NULL,NULL,105),(42,'María ','Marchena','M',NULL,'45544','2014-01-20','Cambodia','(1231) 231.3131','(1231) 231.2312','asd@asd.com',NULL,NULL,106),(43,'Miguel','Cordero','M',NULL,'12345','2014-01-01','Venezuela','(1212) 455.1521','(1231) 123.1231','asd@asd.com',1,'staff1402516375312',108),(44,'Pedro','Romero','M',NULL,'12345','2014-01-01','Saint Helena','(1212) 455.1521','(1231) 123.1231','asd@asd.com',NULL,NULL,109),(45,'Rafael','Esquivel','M',NULL,'54321','2014-01-08','Venezuela','(1231) 231.2312','(1231) 231.2312','ja@asd.com',1,'staff1402517804445',110),(46,'Elio','Quintal','M',NULL,'3.948.489','2014-01-06','Venezuela','(0212) 234.5676','(0414) 345.5678','quintal@gmail.com',NULL,NULL,111),(47,'Alain','Baroja','M','','17.368.678','1989-01-23','Venezuela','(0212) 635.8281','(0414) 479.9203','alainb@gmail.com',NULL,'jugador1401832693296',114),(48,'Anthony','Uribe','M','','15.987.309','2004-01-09','Venezuela','','','wjime@gmail.com',NULL,'jugador1402592242123',115),(49,'Héctor','Pérez','M','\"Tico\"','23.983.098','2004-01-02','Venezuela','','','wjime@gmail.com',NULL,'jugador1402592763057',116),(51,'Rubert ','Quijada','M','','18.987.287','2004-01-03','Venezuela','','','',NULL,'jugador1402015952252',119),(52,'Francisco','Carabalí','M','','17.298.298','2004-01-03','Venezuela','','','',NULL,'jugador1402017510558',120),(53,'Roberto ','Tucker','M','','14.876.234','2004-01-02','Argentina','','','',NULL,'jugador1402017709663',121),(54,'Andrés','Sánchez','M','','14.762.367','2004-01-14','Venezuela','','','',NULL,'jugador1402017862356',122),(55,'Rómulo','Otero','M','','19.789.098','2004-01-25','Venezuela','','','',NULL,'jugador1402017984309',123),(56,'Ricardo','Andreutti','M','','17.276.298','2004-01-30','Venezuela','','','',NULL,'jugador1402018117174',124),(57,'Luis','González','M','','22.098.987','2004-01-29','Venezuela','','','',NULL,'jugador1402018421581',125),(58,'Félix ','Cásseres','M','','19.873.398','2004-01-27','Venezuela','','','',NULL,'jugador1402018608049',126),(59,'Dany','Cure','M','','21.897.267','2004-01-14','Venezuela','','','',NULL,'jugador1402018734123',127),(60,'Edder ','Farías','M','','15.874.398','2004-01-17','Venezuela','','','',NULL,'jugador1402018893912',128),(61,'Eduardo ','Saragó','M',NULL,'12.987.365','1982-01-11','Venezuela','','','',1,'staff1404916821007',129),(63,'Rubén','Villavicencio','M',NULL,'23456789','1975-01-11','Venezuela','','','',1,'staff1402435154453',135),(66,'Juanita','Alimaña','F',NULL,'3216547','1974-01-03','Venezuela','','','',NULL,'arbitro1402515131700',138),(67,'Otrova','Gomaz','M',NULL,'3215487','1981-01-24','Venezuela','','','',NULL,'vacio',139),(68,'Miguel Ángel','Romero','M',NULL,'16.338.764','2004-01-01','Venezuela','','','',NULL,'staff1402517116764',141),(69,'Roberto','Cara e Piedra','M',NULL,'','1969-01-02','Venezuela','','','',NULL,'arbitro1402521028284',142),(71,'Felix','Angulo','M',NULL,'20.233.321',NULL,'','','','',NULL,'vacio',144),(72,'Felix','Angulo','M',NULL,'',NULL,'Venezuela','','','',NULL,'vacio',145),(73,'José David','Contreras','M','','22.765.909','1994-01-20','Venezuela','','','',NULL,'jugador1402622024156',147),(74,'José','Argote','M',NULL,'14.876.287','1980-01-22','Venezuela','','','',NULL,'arbitro1402924051864',148),(75,'Júlio César','Soares De Espíndola','M','','29.098.367','1979-01-03','Brazil','','','',NULL,'jugador1403286166491',195),(76,'Víctor','Sifontes ','M','','22.097.234','1993-01-21','Venezuela','','','',NULL,'vacio',197),(77,'Nicolás','Marquez','M','','24.876.256','1996-01-21','Venezuela','','','',NULL,'vacio',198),(78,'Manuel','Plasencia','M',NULL,'4.387.938',NULL,'Venezuela','','','',NULL,NULL,199),(79,'Jhon','Giraldo','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,200),(80,'Jhonny','Ferreira','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,201),(81,'José','Hernández','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,202),(82,'Ruberth ','Morán','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,203),(83,'Rafael','Dudamel','M',NULL,'',NULL,'Venezuela','','','',1,NULL,204),(84,'Francisco','Moreno','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,205),(85,'Hugo','Savarese','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,206),(86,'Laureano','González','M',NULL,'',NULL,'Venezuela','','','',1,NULL,207),(87,'Gerardo','Rivero','M',NULL,'',NULL,'Venezuela','','','',NULL,NULL,208),(88,'Yerikson','Murillo','M','','','1994-01-25','Venezuela','','','',NULL,'jugador1403780921429',209),(89,'Diego','Ostos','M','','','1993-01-21','Venezuela','','','',NULL,'jugador1403781120780',210),(90,'Wilker','Ángel','M','','','1993-01-18','Venezuela','','','',NULL,'jugador1403781285962',211),(91,'Yuber','Mosquera','M','','23.565.565','1984-01-31','Colombia','','','',NULL,'jugador1403781467616',212),(92,'Carlos','Rivero','M','','','1992-01-27','Venezuela','','','',NULL,'jugador1403782534883',213),(93,'Carlos','Salazar','M','','21.767.878','1989-01-15','Venezuela','','','',NULL,'jugador1403782701633',214),(94,'Francisco','Flores','M','','','1990-01-30','Venezuela','','','',NULL,'jugador1403783021054',215),(95,'Agnel','Flores','M','','','1989-01-29','Venezuela','','','',NULL,'jugador1403783247601',216),(96,'Yohandry','Orozco','M','','','1991-01-19','Venezuela','','','',NULL,'jugador1403783389306',217),(97,'César','González','M','\"Maestrico\"','18.278.254','1982-01-01','Venezuela','','','',NULL,'jugador1403783558096',218),(98,'Ebby','Pérez','M','','','1991-01-01','Venezuela','','','',NULL,'jugador1403783958027',219),(99,'Romeri','Villamizar','M','','','1995-01-06','Venezuela','','','',NULL,'jugador1403784108648',220),(100,'Marcos','Sánchez','M','','','1989-01-23','Panama','','','',NULL,'jugador1403784438252',221),(101,'Carlos','Cermeño','M','','','1995-01-09','Venezuela','','','',NULL,'jugador1403784579681',222),(102,'José Luis','Tancredi','M','','','1983-01-14','Uruguay','','','',NULL,'jugador1403784850887',223),(103,'José Miguel','Reyes','M','','','1992-01-19','Venezuela','','','',NULL,'jugador1403784981926',224),(104,'Ángel','Osorio','M','','','1990-01-02','Venezuela','','','',NULL,'jugador1403785114057',225),(105,'Gelmín','Rivas','M','','','1989-01-23','Venezuela','','','',NULL,'jugador1403785242965',226),(106,'José Alí','Meza','M','','19.095.001','1995-01-01','Venezuela','','','',NULL,'jugador1403785467116',227),(107,'Juan Carlos','Azócar','M','','5678987','1995-01-01','Venezuela','','','',NULL,'jugador1403785600488',228),(108,'Javier Eduardo','Toyo Bárcenas','M','','000000001','1977-01-12','Venezuela','','','',NULL,'jugador1404135190439',229),(109,'Wilber Eduardo','Jiménez ','M','Monstruo','000002','1990-01-16','Venezuela','','','',NULL,'jugador1404137068027',230),(112,'Javier Alejandro','González Tupper','M','','00076','1988-01-26','Venezuela','','','',NULL,'jugador1404137951207',233),(113,'Diego Sebástian','Menghi','M','Mariscal','0201201','1985-01-17','Argentina','','','',NULL,'jugador1404138127940',234),(114,'Henry Leonardo ','Bautista Blanco','M','Leo','02304','1983-01-29','Venezuela','','','',NULL,'jugador1404138696603',235);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-16 14:37:54
