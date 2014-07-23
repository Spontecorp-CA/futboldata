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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Caracas Fútbol Club','1967','Fue Fundado el 12 de diciembre de 1967 por iniciativa de un grupo de amigos. En 1984 se une al Caracas Yamaha y debuta en 1ra División. Durante 2 años fue administrado por el canal de televisión RCTV. En 1989 paso a manos de la Organización Cocodrilos y Laboratorios Vargas. Es el club con más títulos con 11 campeonatos y 5 Copas Venezuela.',1,54,'club1405439856845'),(3,'Atlético Venezuela Club de Fútbol','2008','Nace de la fusión de los clubes..........',1,64,NULL),(4,'Carabobo Fútbol Club','1990','Nace en el año 1990 de la mano de Juan Arango.',1,51,NULL),(5,'ACCD Mineros de Guayana','1980','Poca historia tiene este club.......',1,60,NULL),(6,'Club Deportivo Lara','1989','nbkjabIÑUVDKÑJWQVD',1,133,NULL),(7,'Deportivo Táchira Fútbol Club','1970','El “Carrusel Aurinegro”, como es conocido popularmente el Deportivo Táchira F.C., es una institución con 40 años de historia, engalanada por los siete títulos de Campeón obtenidos en el fútbol profesional venezolano, las diecisiete participaciones en Copa Libertadores de América, tres en Copa Conmebol, dos incursiones en Copa Sudamericana y otras presentaciones destacadas en competiciones nacionales e internacionales.',1,146,NULL),(8,'Deportivo Petare Fútbol Club','2010','Con el apoyo estratégico de la Alcaldía de Sucre, nació el Deportivo Petare, registrando su primer partido en el fútbol profesional venezolano el 8 de agosto de 2010 ante Deportivo Anzoátegui en el Estadio José Antonio Anzoátegui de Puerto La Cruz e incursionando en el ámbito internacional al enfrentar a Cerro Porteño de Paraguay en la fase previa de Copa Libertadores 2011, un cupo que había adquirido la franquicia anterior gracias al tercer lugar en la tabla acumulada durante la temporada 2009/2010.',1,149,NULL),(9,'Deportivo La Guaira','2013','Deportivo La Guaira nace con el objetivo de tener un Estado, un estadio y una afición.',1,150,NULL),(10,'Metropolitanos Fútbol Club','2012','Metropolitanos de Caracas FC es un equipo de fútbol profesional venezolano que tiene como proyecto consolidarse en el balompié rentado de nuestro país. En senda ascendente, el equipo poco a poco va camino a convertirse en una institución sólida en la estructura deportiva que hace vida en Venezuela.',1,151,NULL),(11,'Aragua Fútbol Club','1987','Más que un equipo de fútbol profesional, Aragua Fútbol Club es una institución que busca enaltecer los valores deportivos en la entidad aragüeña. Desde grandes profesionales que han marcado diferencia en el balompié profesional, hasta jóvenes que comienzan a dar sus primeros pasos pensando en convertirse en futuros jugadores de la Vinotinto, el elenco con sede en la “Ciudad Jardín” de Venezuela quiere escribir una gran historia en el país, trabajando desde las bases para formar a los próximos protagonistas del futuro.',1,152,NULL),(12,'Trujillanos Fútbol Club','1981','Trujillanos Fútbol Club fue fundado el 25 de agosto de 1981 y en su octava temporada de segunda división conquista el título y asciende a la máxima categoría del balompié profesional venezolano, para estrenarse en la 1989/1990.',1,153,NULL),(13,'Estudiantes de Mérida Fútbol Club','1971','Todo comienza cuando Mérida conquista los dos Campeonatos Juveniles efectuados en esta ciudad durante los años de 1969 y 1970. Esto prendió la fiebre para aspirar contar con un equipo profesional de fútbol en la ciudad turística y estudiantil por excelencia de Venezuela. Los difuntos Don José Arano (vasco de nacimiento) y Luis Ghersi Govea, realizan los primeros contactos legales ante la Liga Mayor de Fútbol y la Federación Venezolana de Fútbol (FVF). ',1,154,NULL),(14,'Zamora Fútbol Club','1977','El 2 de febrero de 1977 nace oficialmente el Zamora. Su primera Junta Directiva estuvo integrada por Rafael Rosales Peña como Presidente, Diputado al Congreso para ese entonces, Giovanni Rizzeto, Humberto Mazzei, Antonio Marinelli, Carlos Ferro, Julio Figueroa y Lindolfo Villafañe.',1,155,NULL),(15,'Llaneros de Guanare Fútbol Club','1984','Fundado el 26 de agosto de 1984 (aunque Llaneros de Guanare como tal existe desde el año 1953, ganando en aquel entonces la Primera Copa Flor Chalbaud de Pérez Jiménez, torneo disputado como amateur).Tiene como su sede el estadio Rafael Calles Pinto, un campo que ha sido complicado para muchos equipos y que cuenta con una capacidad aproximada para 13 mil espectadores. Actualmente es el equipo más viejo del fútbol venezolano sin cambiar de nombre',1,156,NULL),(16,'Zulia Fútbol Club','2005','Nació en el 30 de junio de 2005 con el firme propósito de trascender y lograr grandes cosas dentro del fútbol profesional venezolano. Tras varios años de lucha y arduo trabajo, el equipo petrolero ascendió a la máxima categoría del balompié nacional. ',1,157,NULL),(17,'Tucanes de Amazonas Fútbol Club','2008','2008-2009 fue el año debut del equipo amazónico, participando en la Segunda División B, en la región Centro-Oriental. debutó en partido oficial contra el UCV Aragua, con resultado favorable de Los Plumíferos por 2-1 a casa llena (5000 espectadores), en la segunda jornada venció como visitante al Carabobo Fútbol Club B con goleada de 0-3, perdió el invicto en la jornada 6, después de 4 victorias y un empate, ante Fundación Cesarger en Cumaná. Al finalizar el apertura quedó en la cuarta posición con 20 puntos, al igual que el Atlético Orinoco, que se ganó la tercera posición por más goles a favor.',1,158,NULL),(18,'Deportivo Anzoátegui Sport Club','2002','El club fue fundado en el año 2002 por el Lic. Juan Pereira. Debutó oficialmente en la Torneo Aspirantes contra Paratocos Fútbol Club el 9 de noviembre del 2002 en el Estadio Olímpico Hermanos Ghersi Páez en condición de visitante con resultado a favor de 3 a 0, el primer gol en la historia del club lo realizó Erbin Díaz. En esa misma temporada (2002-2003) el equipo logro situarse en el primer lugar de forma invicta del campeonato de tercera división y logra el ascenso.',1,159,NULL),(19,'Portuguesa Fútbol Club','1972','El 12 de febrero de 1972, reunidos en el Club Ítalo Venezolano de Araure, un grupo de empresarios se dio a la tarea de formar un equipo de fútbol profesional que representara a Portuguesa, aunque para ese entonces jamás se imaginaron los logros a corto plazo que tendría su idea.',1,160,NULL),(20,'Selección Brasil','1910','',1,163,NULL),(21,'Selección México','1912','',1,164,NULL),(22,'Selección Camerún','1940','',1,165,NULL),(23,'Selección Croacia','1939','',1,166,NULL),(24,'Selección Holanda','1922','',1,167,NULL),(25,'Selección Chile','1922','',1,168,NULL),(26,'Selección Australia','1940','',1,169,NULL),(27,'Selección España','1900','',1,170,NULL),(28,'Selección Colombia','1945','',1,171,NULL),(29,'Selección Costa de Marfil','1934','',1,172,NULL),(30,'Selección Japón','1946','',1,173,NULL),(31,'Selección Grecia','1937','',1,174,NULL),(32,'Selección Costa Rica','1948','',1,175,NULL),(33,'Selección Italia','','',1,176,NULL),(34,'Selección Inglaterra','1890','',1,177,NULL),(35,'Selección Uruguay','1910','',1,178,NULL),(36,'Selección Francia','1900','',1,179,NULL),(37,'Selección Suiza','1910','',1,180,NULL),(38,'Selección Ecuador','1920','',1,181,NULL),(39,'Selección Honduras','','',1,182,NULL),(40,'Selección Argentina','1900','',1,183,NULL),(41,'Selección Irán','1930','',1,184,NULL),(42,'Selección Nigeria','1930','',1,185,NULL),(43,'Selección Bosnia','1920','',1,186,NULL),(44,'Selección Alemania','1910','',1,187,NULL),(45,'Selección EEUU','1979','',1,188,NULL),(46,'Selección Ghana','1923','',1,189,NULL),(47,'Selección Portugal','1924','',1,190,NULL),(48,'Selección Bélgica','1923','',1,191,NULL),(49,'Selección Corea del Sur','','',1,192,NULL),(50,'Selección Rusia','','',1,193,NULL),(51,'Selección Argelia','','',1,194,NULL);
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

-- Dump completed on 2014-07-16 14:37:44
