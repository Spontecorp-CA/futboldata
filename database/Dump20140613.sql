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
-- Table structure for table `afiliacion`
--

DROP TABLE IF EXISTS `afiliacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `afiliacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `asociacion_super_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_asociacion_asociacion_super_idx` (`asociacion_super_id`),
  KEY `fk_asociacion_has_asociacion_1_idx` (`asociacion_id`),
  CONSTRAINT `fk_asociacion_has_asociacion_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_asociacion_has_asociacion_asociacion2` FOREIGN KEY (`asociacion_super_id`) REFERENCES `asociacion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afiliacion`
--

LOCK TABLES `afiliacion` WRITE;
/*!40000 ALTER TABLE `afiliacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `afiliacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agente` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `persona_id` int(10) unsigned NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_agente_persona1_idx` (`persona_id`),
  CONSTRAINT `fk_agente_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agente`
--

LOCK TABLES `agente` WRITE;
/*!40000 ALTER TABLE `agente` DISABLE KEYS */;
/*!40000 ALTER TABLE `agente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arbitro`
--

DROP TABLE IF EXISTS `arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arbitro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `persona_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_arbitro_persona1_idx` (`persona_id`),
  KEY `fk_arbitro_asociacion1_idx` (`asociacion_id`),
  CONSTRAINT `fk_arbitro_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_arbitro_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro`
--

LOCK TABLES `arbitro` WRITE;
/*!40000 ALTER TABLE `arbitro` DISABLE KEYS */;
INSERT INTO `arbitro` VALUES (12,17,1,1),(13,18,1,3),(14,19,1,2),(15,20,1,6),(19,66,1,1),(20,67,1,3),(21,69,1,6),(24,72,1,4),(25,74,1,NULL);
/*!40000 ALTER TABLE `arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arbitro_premio`
--

DROP TABLE IF EXISTS `arbitro_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arbitro_premio` (
  `arbitro_id` int(10) unsigned NOT NULL,
  `premio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`arbitro_id`,`premio_id`),
  KEY `fk_arbitro_has_premio_premio1_idx` (`premio_id`),
  KEY `fk_arbitro_has_premio_arbitro1_idx` (`arbitro_id`),
  CONSTRAINT `fk_arbitro_has_premio_arbitro1` FOREIGN KEY (`arbitro_id`) REFERENCES `arbitro` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_arbitro_has_premio_premio1` FOREIGN KEY (`premio_id`) REFERENCES `premio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro_premio`
--

LOCK TABLES `arbitro_premio` WRITE;
/*!40000 ALTER TABLE `arbitro_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `arbitro_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asociacion`
--

DROP TABLE IF EXISTS `asociacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asociacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  `logo` text,
  PRIMARY KEY (`id`),
  KEY `fk_asociacion_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_asociacion_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asociacion`
--

LOCK TABLES `asociacion` WRITE;
/*!40000 ALTER TABLE `asociacion` DISABLE KEYS */;
INSERT INTO `asociacion` VALUES (1,'Asociación Estado Miranda',1,1,NULL),(2,'Asociación Estado Aragua',1,3,NULL),(3,'Asociación Estado Carabobo',1,4,NULL),(4,'Asociación Estado Lara',1,5,NULL),(6,'Asociación Estado Zulia',1,4,NULL),(7,'Asociación Distrito Capital',1,107,NULL),(8,'Asociación Estado Yaracuy',1,112,NULL),(9,'Federación Venezolana de Fútbol',1,118,NULL),(10,'Conmebol',1,132,NULL);
/*!40000 ALTER TABLE `asociacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `is_himno` int(11) DEFAULT NULL,
  `club_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_audio_club1_idx` (`club_id`),
  CONSTRAINT `fk_audio_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES (1,'jkkjl',0,1),(2,'ewa',NULL,1);
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancha`
--

DROP TABLE IF EXISTS `cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancha` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `superficie` varchar(45) DEFAULT NULL,
  `medidas` varchar(45) DEFAULT NULL,
  `foto` text,
  `status` int(11) DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  `coordenada_long` varchar(12) DEFAULT NULL,
  `coordenada_lat` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancha_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_cancha_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancha`
--

LOCK TABLES `cancha` WRITE;
/*!40000 ALTER TABLE `cancha` DISABLE KEYS */;
INSERT INTO `cancha` VALUES (1,'Estadio Olímpico de la UCV',22000,'grama natural','105 x 67 Metros','cancha1401115919522',1,103,'-66.885811','10.491344'),(2,'La Guacamaya',10000,'Grama Artificial','100 x 60 Metros','cancha1401196150236',1,104,'-66.86757','10.478793'),(3,'Pueblo Nuevo',40000,'Grama Natural','','cancha1402069377785',1,130,'-72.204337','7.7818573'),(4,'Polideportivo Cachamay',45000,'Grama Natural','','cancha1402071599721',1,131,'-62.696256','8.312642'),(5,'Estadio Nacional Brígido Iriarte',12000,'Grama Natural','105 x 68','cancha1402515911538',1,140,'-66.941839','10.482981');
/*!40000 ALTER TABLE `cancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Gerente',1),(2,'Aguatero',1),(3,'Asistente Técnico',1),(4,'Presidente',1),(5,'Preparador Físico',1),(6,'Director Técnico',1),(7,'Fisioterapeuta',1),(8,'Gerente Deportivo',1);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` text,
  `tiempo_juego` int(11) DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Sub 17 ','Jugadores menores de 17 años',80,1),(2,'Sub 18','Jugadores menores de 18 años ',80,1),(3,'Sub 19','Jugadores menores de 19 años',0,1),(4,'Sub 20','Jugadores menores de 20 años',0,1),(5,'Sub 16','Jugadores menores de 16 años',0,1),(6,'Sub 15 ','Jugadores menores de 15 años',0,1),(7,'Pre-Infantil \"B\"','Jugadores de ocho años o menores de ocho años',50,1),(8,'1ra División','Torneo que realiza la FVF.',90,1),(9,'2da División','Categoría de plata del Futven.',90,1),(10,'Prueba Larga','Categoría de Prueba',45,1),(11,'Pre-Infantil \"A\"','Jugadores nacidos en los años 2004 y 2005',30,1);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(45) NOT NULL,
  `pais_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ciudad_pais1_idx` (`pais_id`),
  CONSTRAINT `fk_ciudad_pais1` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Barquisimeto',1),(2,'Valencia (Estado Carabobo)',1),(3,'Caracas (Distrito Capital)',1),(8,'Cabudare (Estado Lara)',1),(9,'Bogota (Estado Asociado)',113),(10,'Santiago',247),(11,'Mexico D.F',36),(12,'San Cristóbal (Estado Táchira)',1),(13,'Buenos Aires ',94),(14,'Maracaibo (Estado Zulia)',1),(15,'Puerto Ordaz (Estado Bolívar)',1),(16,'Maracay (Estado Aragua)',1),(17,'Puerto Ayacucho (Estado Amazonas)',1),(18,'San Felipe',1),(19,'Barinas (Estado Barinas)',1),(20,'Valera (Estado Trujillo)',1),(21,'Puerto La Cruz (Estado Anzoátegui)',1),(22,'Guanare (Estado Portuguesa)',1),(23,'Mérida (Estado Mérida)',1),(24,'El Vigía (Estado Mérida)',1),(25,'Araure (Estado Portuguesa)',1),(26,'Asunción',186),(27,'Coro (EStado Falcón)',1),(28,'La Guaira (Estado Vargas)',1);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jornada_id` int(10) unsigned NOT NULL,
  `equipo_id` int(10) unsigned NOT NULL,
  `j_jugados` int(11) DEFAULT NULL,
  `j_ganados` int(11) DEFAULT NULL,
  `j_empatados` int(11) DEFAULT NULL,
  `j_perdidos` int(11) DEFAULT NULL,
  `goles_favor` int(11) DEFAULT NULL,
  `goles_contra` int(11) DEFAULT NULL,
  `diferencia` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  `is_local` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clasificacion_jornada1_idx` (`jornada_id`),
  KEY `fk_clasificacion_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_clasificacion_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_clasificacion_jornada1` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Caracas Fútbol Club','1989','En el año 1982, el grupo Laboratorios Vargas le compra el equipo a.......',1,54,NULL),(3,'Atlético Venezuela Club de Fútbol','2008','Nace de la fusión de los clubes..........',1,64,NULL),(4,'Carabobo Fútbol Club','1990','Nace en el año 1990 de la mano de Juan Arango.',1,51,NULL),(5,'ACCD Mineros de Guayana','1980','Poca historia tiene este club.......',1,60,NULL),(6,'Club Deportivo Lara','1989','nbkjabIÑUVDKÑJWQVD',1,133,NULL),(7,'Deportivo Táchira Fútbol Club','1970','El “Carrusel Aurinegro”, como es conocido popularmente el Deportivo Táchira F.C., es una institución con 40 años de historia, engalanada por los siete títulos de Campeón obtenidos en el fútbol profesional venezolano, las diecisiete participaciones en Copa Libertadores de América, tres en Copa Conmebol, dos incursiones en Copa Sudamericana y otras presentaciones destacadas en competiciones nacionales e internacionales.',1,146,NULL),(8,'Deportivo Petare Fútbol Club','2010','Con el apoyo estratégico de la Alcaldía de Sucre, nació el Deportivo Petare, registrando su primer partido en el fútbol profesional venezolano el 8 de agosto de 2010 ante Deportivo Anzoátegui en el Estadio José Antonio Anzoátegui de Puerto La Cruz e incursionando en el ámbito internacional al enfrentar a Cerro Porteño de Paraguay en la fase previa de Copa Libertadores 2011, un cupo que había adquirido la franquicia anterior gracias al tercer lugar en la tabla acumulada durante la temporada 2009/2010.',1,149,NULL),(9,'Deportivo La Guaira','2013','Deportivo La Guaira nace con el objetivo de tener un Estado, un estadio y una afición.',1,150,NULL),(10,'Metropolitanos Fútbol Club','2012','Metropolitanos de Caracas FC es un equipo de fútbol profesional venezolano que tiene como proyecto consolidarse en el balompié rentado de nuestro país. En senda ascendente, el equipo poco a poco va camino a convertirse en una institución sólida en la estructura deportiva que hace vida en Venezuela.',1,151,NULL),(11,'Aragua Fútbol Club','1987','Más que un equipo de fútbol profesional, Aragua Fútbol Club es una institución que busca enaltecer los valores deportivos en la entidad aragüeña. Desde grandes profesionales que han marcado diferencia en el balompié profesional, hasta jóvenes que comienzan a dar sus primeros pasos pensando en convertirse en futuros jugadores de la Vinotinto, el elenco con sede en la “Ciudad Jardín” de Venezuela quiere escribir una gran historia en el país, trabajando desde las bases para formar a los próximos protagonistas del futuro.',1,152,NULL),(12,'Trujillanos Fútbol Club','1981','Trujillanos Fútbol Club fue fundado el 25 de agosto de 1981 y en su octava temporada de segunda división conquista el título y asciende a la máxima categoría del balompié profesional venezolano, para estrenarse en la 1989/1990.',1,153,NULL),(13,'Estudiantes de Mérida Fútbol Club','1971','Todo comienza cuando Mérida conquista los dos Campeonatos Juveniles efectuados en esta ciudad durante los años de 1969 y 1970. Esto prendió la fiebre para aspirar contar con un equipo profesional de fútbol en la ciudad turística y estudiantil por excelencia de Venezuela. Los difuntos Don José Arano (vasco de nacimiento) y Luis Ghersi Govea, realizan los primeros contactos legales ante la Liga Mayor de Fútbol y la Federación Venezolana de Fútbol (FVF). ',1,154,NULL),(14,'Zamora Fútbol Club','1977','El 2 de febrero de 1977 nace oficialmente el Zamora. Su primera Junta Directiva estuvo integrada por Rafael Rosales Peña como Presidente, Diputado al Congreso para ese entonces, Giovanni Rizzeto, Humberto Mazzei, Antonio Marinelli, Carlos Ferro, Julio Figueroa y Lindolfo Villafañe.',1,155,NULL),(15,'Llaneros de Guanare Fútbol Club','1984','Fundado el 26 de agosto de 1984 (aunque Llaneros de Guanare como tal existe desde el año 1953, ganando en aquel entonces la Primera Copa Flor Chalbaud de Pérez Jiménez, torneo disputado como amateur).Tiene como su sede el estadio Rafael Calles Pinto, un campo que ha sido complicado para muchos equipos y que cuenta con una capacidad aproximada para 13 mil espectadores. Actualmente es el equipo más viejo del fútbol venezolano sin cambiar de nombre',1,156,NULL),(16,'Zulia Fútbol Club','2005','Nació en el 30 de junio de 2005 con el firme propósito de trascender y lograr grandes cosas dentro del fútbol profesional venezolano. Tras varios años de lucha y arduo trabajo, el equipo petrolero ascendió a la máxima categoría del balompié nacional. ',1,157,NULL);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_cancha`
--

DROP TABLE IF EXISTS `club_cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_cancha` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `club_id` int(10) unsigned NOT NULL,
  `cancha_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_club_has_cancha_cancha1_idx` (`cancha_id`),
  KEY `fk_club_has_cancha_club1_idx` (`club_id`),
  CONSTRAINT `fk_club_has_cancha_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_club_has_cancha_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_cancha`
--

LOCK TABLES `club_cancha` WRITE;
/*!40000 ALTER TABLE `club_cancha` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_cancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_premio`
--

DROP TABLE IF EXISTS `club_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_premio` (
  `club_id` int(10) unsigned NOT NULL,
  `premio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`club_id`,`premio_id`),
  KEY `fk_club_has_premio_premio1_idx` (`premio_id`),
  KEY `fk_club_has_premio_club1_idx` (`club_id`),
  CONSTRAINT `fk_club_has_premio_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_club_has_premio_premio1` FOREIGN KEY (`premio_id`) REFERENCES `premio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_premio`
--

LOCK TABLES `club_premio` WRITE;
/*!40000 ALTER TABLE `club_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competicion`
--

DROP TABLE IF EXISTS `competicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competicion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `descripcion` text,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `direccion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_competicion_asociacion1_idx` (`asociacion_id`),
  KEY `fk_competicion_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_competicion_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_competicion_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competicion`
--

LOCK TABLES `competicion` WRITE;
/*!40000 ALTER TABLE `competicion` DISABLE KEYS */;
INSERT INTO `competicion` VALUES (1,'Liga Movistar 1ra División',1,'Liga de Fútbol de la Primera División del Fútbol Profesional Venezolano.',9,69),(2,'Liga Movistar 2da División',1,'Torneo que organiza la FVF para aquellos equipos que buscan el ascenso a la 1ra División.',9,74),(3,'3ra División',1,'tercera liga del sistema.',NULL,75),(5,'La cuarta',1,'la cuarta liga',3,101),(6,'Liga César del Vecchio',1,'Liga que hace vida en la Gran Caracas, no federada.',NULL,83),(7,'Serie Nacional Sub 18 y Sub 20',1,'Torneo que realiza FVF para los equipos filiales que pertenecen a la 1ra División del Fúven.',NULL,84),(8,'Copa Sudamericana',1,'La Copa Sudamericana es el segundo torneo en importancia a nivel de clubes organizado por la Confederación Sudamericana de Fútbol.',10,85),(9,'Copa Libertadores',1,'En América del Sur los partidos oficiales entre clubes de distintos países nacieron en 1900, cuando Francis Chevallier Boutell asumió la presidencia de la Asociación del  Fútbol Argentino (AFA). Creó la Copa Competencia y donó el trofeo para ser disputado entre los clubes de Buenos Aires, Rosario y Montevideo que se inscribieran para participar.',10,86),(10,'La  novena',1,'La novena',3,87),(11,'La decima',1,'la decima',2,88),(12,'La undecima',1,'La undecima',NULL,102),(13,'la duodécima',1,'',NULL,113);
/*!40000 ALTER TABLE `competicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competicion_arbitro`
--

DROP TABLE IF EXISTS `competicion_arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competicion_arbitro` (
  `competicion_id` int(10) unsigned NOT NULL,
  `arbitro_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`competicion_id`,`arbitro_id`),
  KEY `fk_competicion_has_arbitro_arbitro1_idx` (`arbitro_id`),
  KEY `fk_competicion_has_arbitro_competicion1_idx` (`competicion_id`),
  CONSTRAINT `fk_competicion_has_arbitro_arbitro1` FOREIGN KEY (`arbitro_id`) REFERENCES `arbitro` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_competicion_has_arbitro_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competicion_arbitro`
--

LOCK TABLES `competicion_arbitro` WRITE;
/*!40000 ALTER TABLE `competicion_arbitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `competicion_arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descripcion` text,
  `patrocinante_id` int(10) unsigned DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `jugador_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contrato_patrocinante1_idx` (`patrocinante_id`),
  KEY `fk_contrato_asociacion1_idx` (`asociacion_id`),
  KEY `fk_contrato_club1_idx` (`club_id`),
  KEY `fk_contrato_equipo1_idx` (`equipo_id`),
  KEY `fk_contrato_jugador1_idx` (`jugador_id`),
  KEY `fk_contrato_competicion1_idx` (`competicion_id`),
  CONSTRAINT `fk_contrato_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_jugador1` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_patrocinante1` FOREIGN KEY (`patrocinante_id`) REFERENCES `patrocinante` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convocado`
--

DROP TABLE IF EXISTS `convocado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `convocado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jugador_id` int(10) unsigned NOT NULL,
  `camiseta` int(11) DEFAULT NULL,
  `titular` int(11) DEFAULT NULL COMMENT '0: suplente',
  `tiempo_jugado` time DEFAULT NULL,
  `posicion_id` int(10) unsigned NOT NULL,
  `convocatoria_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_convocado_jugador1_idx` (`jugador_id`),
  KEY `fk_convocado_posicion1_idx` (`posicion_id`),
  KEY `fk_convocado_convocatoria1_idx` (`convocatoria_id`),
  CONSTRAINT `fk_convocado_convocatoria1` FOREIGN KEY (`convocatoria_id`) REFERENCES `convocatoria` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocado_jugador1` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocado_posicion1` FOREIGN KEY (`posicion_id`) REFERENCES `posicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convocado`
--

LOCK TABLES `convocado` WRITE;
/*!40000 ALTER TABLE `convocado` DISABLE KEYS */;
/*!40000 ALTER TABLE `convocado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convocatoria`
--

DROP TABLE IF EXISTS `convocatoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `convocatoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equipo_id` int(10) unsigned NOT NULL,
  `partido_id` int(10) unsigned NOT NULL,
  `formacion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_convocatoria_equipo1_idx` (`equipo_id`),
  KEY `fk_convocatoria_partido1_idx` (`partido_id`),
  KEY `fk_convocatoria_formacion1_idx` (`formacion_id`),
  CONSTRAINT `fk_convocatoria_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_formacion1` FOREIGN KEY (`formacion_id`) REFERENCES `formacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_convocatoria_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convocatoria`
--

LOCK TABLES `convocatoria` WRITE;
/*!40000 ALTER TABLE `convocatoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `convocatoria` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'Los Teques.','No tiene',NULL,NULL,3),(3,'La Redoma Palo Negro.','No tiene',NULL,NULL,16),(4,'Estadio \"Pachencho\" Romero.','No tiene',NULL,NULL,14),(5,'El Obelisco. Barquisimeto.','No tiene',NULL,NULL,1),(13,'Ujano','www.comc.vv',NULL,NULL,1),(14,'asdasdasdasd',NULL,NULL,NULL,1),(15,'Cruz Blanca',NULL,NULL,NULL,1),(16,NULL,NULL,NULL,NULL,NULL),(17,'asddassa',NULL,NULL,NULL,12),(18,'asddassa',NULL,NULL,NULL,12),(19,'asddassa',NULL,NULL,NULL,12),(20,'sdadsasdasdasd',NULL,NULL,NULL,2),(22,'ddssdsd',NULL,NULL,NULL,1),(23,'Aqui y alla',NULL,NULL,NULL,8),(24,'Catia.',NULL,NULL,NULL,3),(25,'Puerto Ordaz, Estado Bolívar.',NULL,NULL,NULL,15),(26,'Calle 245',NULL,NULL,NULL,12),(27,'Prados del Este',NULL,NULL,NULL,3),(28,'Av La Salle con calle Lima, Torre Phelps, Piso 19, Of 19-D, Los Caobos',NULL,NULL,NULL,3),(29,'Cruz Blanca',NULL,NULL,NULL,1),(30,'dfdfdfdfdf',NULL,NULL,NULL,2),(31,'dasdasdasd',NULL,NULL,NULL,2),(32,'ffffffffffffffff',NULL,NULL,NULL,2),(33,'Blabla',NULL,NULL,NULL,2),(34,'Valparaiso',NULL,NULL,NULL,9),(35,'Valparaiso',NULL,NULL,NULL,9),(36,'Holaaa',NULL,NULL,NULL,9),(37,'Valparaiso',NULL,NULL,NULL,9),(38,'Pueblo pequeño',NULL,NULL,NULL,2),(39,'Valparaiso',NULL,NULL,NULL,9),(40,'Pueblo pequeño',NULL,NULL,NULL,2),(41,'Cumbres de Curumo',NULL,NULL,NULL,3),(42,NULL,NULL,NULL,NULL,NULL),(43,NULL,NULL,NULL,NULL,NULL),(44,NULL,NULL,NULL,NULL,NULL),(45,NULL,NULL,NULL,NULL,NULL),(48,'Plaza venezu','asdasd',NULL,NULL,2),(49,'Plaza venezuela','asdasd',NULL,NULL,2),(50,'Plaza venezuela','asdasd',NULL,NULL,2),(51,'Estadio Misael Delgado.','No Tiene',NULL,NULL,2),(52,'Ujano, al lado de juxos','www.comc.vv',NULL,NULL,1),(53,'Ujano, al lado de juxos','www.comc.vv',NULL,NULL,1),(54,'Cota 905, Cocodrilos Sport Park','www.comc.vv',NULL,NULL,3),(57,'blabla',NULL,NULL,NULL,1),(58,'Plaza venezuela','asdasd',NULL,NULL,2),(59,'Plaza venezuela','asdasd',NULL,NULL,2),(60,'Polideportivo Cachamay.........','www.delcampofc.com',NULL,NULL,3),(61,'Plaza venezuela','asdasd',NULL,NULL,2),(62,'Plaza venezuela','asdasd',NULL,NULL,2),(63,'Plaza venezuela','asdasd',NULL,NULL,2),(64,'Las Mercedes.','asdasd',NULL,NULL,2),(65,'La Pastora.',NULL,NULL,NULL,3),(66,'La guarimba norte',NULL,NULL,NULL,12),(67,'Cualquier otra','www.laprimera.com',NULL,NULL,3),(68,'Cualquier otra','www.laprimera.com',NULL,NULL,3),(69,'Torre Mega II, PHB Sabana Grande.','www.laprimera.com',NULL,NULL,3),(70,'Calle 5 con 26',NULL,NULL,NULL,12),(71,'Cerca de la Playa colorada.',NULL,NULL,NULL,10),(72,'en algun lugar de la mancha','',NULL,NULL,2),(73,'en algun lugar de la mancha','',NULL,NULL,2),(74,'Torre Mega II, PHB Sabana Grande.','No Tiene',NULL,NULL,3),(75,'cerca de la estación del metro de bellas artes','wwww.la3ra.com',NULL,NULL,3),(77,'otra mas','fdsfsdf',NULL,NULL,3),(78,'otra mas larga','fdsfsdf',NULL,NULL,3),(83,'La Guacamaya, Las Mercedes.','No Tiene',NULL,NULL,3),(84,'Torre Mega II, PHB Sabana Grande.','No Tiene',NULL,NULL,3),(85,'Asunción, Paraguay','',NULL,NULL,26),(86,'Asunción, Paraguay','www.conmebol.com',NULL,NULL,26),(87,'la novena ','',NULL,NULL,3),(88,'lkjhasdkjh','',NULL,NULL,3),(89,'otra mas larga','fdsfsdf',NULL,NULL,3),(90,'otra mas larga','fdsfsdf',NULL,NULL,3),(91,'otra mas larga','fdsfsdf',NULL,NULL,3),(92,'otra mas larga','fdsfsdf',NULL,NULL,3),(93,'otra mas larga','fdsfsdf',NULL,NULL,3),(94,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(95,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(96,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(97,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(98,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(99,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(100,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(101,'otra mas larga y mas larga','fdsfsdf',NULL,NULL,3),(102,'fdsfsdf','sdfsdafs',NULL,NULL,3),(103,'Ciudad Universitaria, Los Chaguaramos',NULL,NULL,NULL,3),(104,'Las Mercedes, Calle La Cinta',NULL,NULL,NULL,3),(105,'Aqui ',NULL,NULL,NULL,8),(106,'Aqui ',NULL,NULL,NULL,8),(107,'Estadio Brigido Iriarte-El Paraíso','No Tiene',NULL,NULL,3),(108,'Altamira',NULL,NULL,NULL,3),(109,'rancho bajo',NULL,NULL,NULL,9),(110,'Santa Rosa',NULL,NULL,NULL,3),(111,'La Tahona.',NULL,NULL,NULL,3),(112,'Estadio Florentino Oropeza.','No Tiene',NULL,NULL,18),(113,'','',NULL,NULL,14),(114,'La Tahona.',NULL,NULL,NULL,3),(115,'La Pastora',NULL,NULL,NULL,3),(116,'Prados del Este',NULL,NULL,NULL,3),(117,'',NULL,NULL,NULL,14),(118,'Torre Mega, Sábana Grande, Caracas.','No Tiene',NULL,NULL,3),(119,'Calle Venezuela.',NULL,NULL,NULL,3),(120,'Trujillo.',NULL,NULL,NULL,14),(121,'Buenos Aires.',NULL,NULL,NULL,13),(122,'La Trinidad.',NULL,NULL,NULL,3),(123,'Cota 905.',NULL,NULL,NULL,3),(124,'Centro Italo.',NULL,NULL,NULL,3),(125,'Barrio Obrero.',NULL,NULL,NULL,3),(126,'Nuevo Horizonte.',NULL,NULL,NULL,3),(127,'Machiques.',NULL,NULL,NULL,14),(128,'Obelisco.',NULL,NULL,NULL,1),(129,'Terrazas del Avila.',NULL,NULL,NULL,3),(130,'Pueblo Nuevo, San Cristóbal',NULL,NULL,NULL,12),(131,'Av. Caracas con Av. Guayanas Ciudad Guayana 8050',NULL,NULL,NULL,15),(132,'Asunción, Paraguay','www.conmebol.com',NULL,NULL,26),(133,'Metropolitano de Cabudare','No tiene',NULL,NULL,1),(134,'Una dirección loca',NULL,NULL,NULL,14),(135,'una direccion loca',NULL,NULL,NULL,12),(138,'',NULL,NULL,NULL,NULL),(139,'',NULL,NULL,NULL,NULL),(140,'Av. Estadio, Caracas.',NULL,NULL,NULL,3),(141,'Parque del Este.',NULL,NULL,NULL,3),(142,'',NULL,NULL,NULL,3),(143,'',NULL,NULL,NULL,1),(144,'',NULL,NULL,NULL,1),(145,'',NULL,NULL,NULL,1),(146,'Pueblo Nuevo.','',NULL,NULL,12),(147,'Guasdualito, Estado Apure, Venezuela.',NULL,NULL,NULL,20),(148,'Zulia',NULL,NULL,NULL,14),(149,'Municipio Petare','http://deportivopetarefc.com',NULL,NULL,3),(150,'La Guacamaya, Caracas.','http://www.deportivolaguaira.com',NULL,NULL,28),(151,'Brígido Iriarte.','http://www.metropolitanosfc.com',NULL,NULL,3),(152,'Estadio Hermanos Ghersi','http://araguafc.com',NULL,NULL,16),(153,'Valera ','http://trujillanos-fc.com.ve',NULL,NULL,20),(154,'Metropolitano de Mérida','No Tiene',NULL,NULL,23),(155,'La Carolina','http://zamorafc.com',NULL,NULL,19),(156,'Rafael Calles Pinto','No Tiene',NULL,NULL,22),(157,'Pachencho Romero','http://fczulia.com',NULL,NULL,14);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `email` VALUES (1,'f.v.f@gobierno.ve.gob',1),(2,'felix@futbol.com',3),(4,'hola@gmgmm.com',4),(6,'sadd@hola.mundo',3),(9,'paz@gmailcc.com',5),(10,'amor@gmailcc.com',5),(11,'ndand@gmail.com',13),(12,'32112@gmail.com',13),(13,'eaidfad@gmail.com',48),(14,'eaidfad@gmail.com',49),(15,'eaidfad@gmail.com',50),(16,'rwerwe@sdfdsf.com',50),(18,'32112@gmail.com',52),(19,'ndand@gmail.com',52),(20,'32112@gmail.com',53),(21,'ndand@gmail.com',53),(22,'kjhkjfdh@lkjsdflkj.com',54),(23,'32112@gmail.com',54),(24,'ndand@gmail.com',54),(25,'eaidfad@gmail.com',58),(26,'rwerwe@sdfdsf.com',58),(27,'eaidfad@gmail.com',59),(28,'eaidfad@gmail.com',59),(29,'rwerwe@sdfdsf.com',59),(30,'delcampo@delcampofc.com',60),(31,'eaidfad@gmail.com',61),(32,'eaidfad@gmail.com',61),(33,'rwerwe@sdfdsf.com',61),(34,'rwerwe@sdfdsf.com',62),(35,'eaidfad@gmail.com',62),(36,'eaidfad@gmail.com',62),(37,'eaidfad@gmail.com',62),(38,'eaidfad@gmail.com',63),(39,'eaidfad@gmail.com',63),(40,'eaidfad@gmail.com',63),(41,'rwerwe@sdfdsf.com',63),(46,'kjghsdfkjh@kjhsdfjh.com',68),(47,'lasegunda@loquesea.com',73),(49,'elmail@loquesea.com',91),(50,'dgdfgfd@gsaidjk.com',97),(51,'effdsfds@hanmil.com',101),(52,'asocdcapital@gmail.com',107),(95,'asd@asd.com',51),(140,'rwerwe@sdfdsf.com',64),(143,'eaidfad@gmail.com',64),(590,'asdasd@gmail.com',1);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Caracas FC',NULL,1,'CFC','equipo1402021658699',1,8),(2,'Caracas FC',NULL,1,'CFC','equipo1402021703533',1,4),(3,'Caracas FC',NULL,1,'CFC','equipo1402021740524',1,2),(4,'Mineros de Guayana',NULL,1,'MG','equipo1402622997906',5,8),(5,'Atlético Venezuela',NULL,1,'AV','equipo1402591927696',3,8),(6,'Carabobo FC',NULL,1,'CFC','equipo1402587565494',4,8),(7,'Deportivo Lara',NULL,1,'DL','equipo1402591051293',6,8),(8,'Deportivo Táchira',NULL,1,'DT','equipo1402612985397',7,8),(9,'Deportivo Petare',NULL,1,'DP','equipo1402627801355',8,8),(10,'Deportivo La Guaira',NULL,1,'DLG','equipo1402672462121',9,8),(11,'Metropolitanos FC',NULL,1,'MFC','equipo1402673999368',10,8),(12,'Aragua FC',NULL,1,'AFC','equipo1402682551125',11,8),(13,'Trujillanos FC',NULL,1,'TFC','equipo1402685157612',12,8),(14,'Estudiantes de Mérida',NULL,1,'EDM','equipo1402685615575',13,8),(15,'Zamora FC',NULL,1,'ZFC','equipo1402686196261',14,8),(16,'Llaneros de Guanare ',NULL,1,'LLDG','equipo1402690090358',15,8),(17,'Zulia FC',NULL,1,'ZFC','equipo1402690661403',16,8);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_cancha`
--

DROP TABLE IF EXISTS `equipo_cancha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_cancha` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equipo_id` int(10) unsigned NOT NULL,
  `cancha_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo_has_cancha_cancha1_idx` (`cancha_id`),
  KEY `fk_equipo_has_cancha_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_has_cancha_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_cancha_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_cancha`
--

LOCK TABLES `equipo_cancha` WRITE;
/*!40000 ALTER TABLE `equipo_cancha` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_cancha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_has_jugador`
--

DROP TABLE IF EXISTS `equipo_has_jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_has_jugador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jugador_id` int(10) unsigned NOT NULL,
  `equipo_id` int(10) unsigned NOT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_has_equipo_equipo2_idx` (`equipo_id`),
  KEY `fk_jugador_has_equipo_jugador2_idx` (`jugador_id`),
  CONSTRAINT `fk_jugador_has_equipo_equipo2` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_has_equipo_jugador2` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_has_jugador`
--

LOCK TABLES `equipo_has_jugador` WRITE;
/*!40000 ALTER TABLE `equipo_has_jugador` DISABLE KEYS */;
INSERT INTO `equipo_has_jugador` VALUES (10,2,1,'2014-01-07','2014-01-22',0),(11,4,1,'2014-01-04','2014-01-23',0),(12,3,1,'2014-01-05','2014-01-05',0),(13,1,1,'2014-01-01','2014-01-13',0),(14,13,1,'2014-01-14','2014-01-05',0),(15,1,2,NULL,'2014-01-05',0),(16,3,2,NULL,'2014-01-16',0),(17,4,2,NULL,'2014-01-05',0),(18,2,2,'2014-01-20','2014-01-12',0),(19,1,3,'2014-01-16',NULL,NULL),(20,2,1,'2014-01-07','2014-01-23',0),(21,3,3,NULL,'2014-01-14',0),(22,2,3,NULL,'2014-01-05',0),(23,2,1,NULL,'2014-01-05',0),(24,3,3,NULL,'2014-01-05',0),(25,1,3,NULL,'2014-01-05',0),(26,22,1,'2014-01-03',NULL,1),(27,1,4,'2014-01-05',NULL,1),(28,2,4,'2014-01-05',NULL,1),(29,3,4,'2014-01-05',NULL,1),(30,4,5,'2014-01-05',NULL,1),(31,13,5,'2014-01-05',NULL,1),(32,26,1,'2014-01-05',NULL,1),(33,27,1,'2014-01-05',NULL,1),(34,28,1,'2014-01-05',NULL,1),(35,29,1,'2014-01-05',NULL,1),(36,30,1,'2014-01-05',NULL,1),(37,31,1,'2014-01-05',NULL,1),(38,32,1,'2014-01-05',NULL,1),(39,33,1,'2014-01-05',NULL,1),(40,34,1,'2014-01-05',NULL,1),(41,35,1,'2014-01-05',NULL,1),(42,18,5,'2014-01-12',NULL,1),(43,23,5,'2014-01-12',NULL,1),(44,36,8,'2014-01-12',NULL,1);
/*!40000 ALTER TABLE `equipo_has_jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_in_liga`
--

DROP TABLE IF EXISTS `equipo_in_liga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_in_liga` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `competicion_id` int(10) unsigned NOT NULL,
  `equipo_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_equipo_in_liga_competicion1_idx` (`competicion_id`),
  KEY `fk_equipo_in_liga_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_in_liga_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_in_liga_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_in_liga`
--

LOCK TABLES `equipo_in_liga` WRITE;
/*!40000 ALTER TABLE `equipo_in_liga` DISABLE KEYS */;
INSERT INTO `equipo_in_liga` VALUES (1,1,1,1),(2,1,4,1),(3,2,2,0),(4,2,2,1),(5,5,1,1),(6,5,4,0),(7,1,5,1),(8,1,6,1),(9,1,7,1),(10,1,8,1),(11,5,4,1),(12,5,5,1),(13,5,6,1),(14,5,7,1),(15,5,8,1),(16,5,9,1),(17,1,10,1),(18,1,11,1),(19,1,9,1),(20,1,12,1),(21,1,13,1),(22,1,14,1),(23,1,15,1),(24,1,16,1),(25,1,17,1);
/*!40000 ALTER TABLE `equipo_in_liga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_premio`
--

DROP TABLE IF EXISTS `equipo_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_premio` (
  `equipo_id` int(10) unsigned NOT NULL,
  `premio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`equipo_id`,`premio_id`),
  KEY `fk_equipo_has_premio_premio1_idx` (`premio_id`),
  KEY `fk_equipo_has_premio_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_has_premio_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_premio_premio1` FOREIGN KEY (`premio_id`) REFERENCES `premio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_premio`
--

LOCK TABLES `equipo_premio` WRITE;
/*!40000 ALTER TABLE `equipo_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_titulo`
--

DROP TABLE IF EXISTS `equipo_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_titulo` (
  `equipo_id` int(10) unsigned NOT NULL,
  `titulo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`equipo_id`,`titulo_id`),
  KEY `fk_equipo_has_titulo_titulo1_idx` (`titulo_id`),
  KEY `fk_equipo_has_titulo_equipo1_idx` (`equipo_id`),
  CONSTRAINT `fk_equipo_has_titulo_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_has_titulo_titulo1` FOREIGN KEY (`titulo_id`) REFERENCES `titulo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_titulo`
--

LOCK TABLES `equipo_titulo` WRITE;
/*!40000 ALTER TABLE `equipo_titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fase`
--

DROP TABLE IF EXISTS `fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `tipo_fase` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `temporada_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fase_temporada1_idx` (`temporada_id`),
  CONSTRAINT `fk_fase_temporada1` FOREIGN KEY (`temporada_id`) REFERENCES `temporada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'Apertura 2014',0,1,1),(2,'Fase de Grupos',NULL,1,2),(3,'Clasificatoria',0,1,4),(4,'Semifinal',1,1,4),(5,'Final',1,1,4),(6,'Clausura 2015',0,1,1),(7,'Serie Final',1,1,1),(8,'Liguilla Pre-Sudamericana',1,1,1),(9,'Primera Fase',1,1,10),(10,'Apertura 2014',0,1,3),(11,'Ascenso 2015',0,1,3),(12,'Promoción y Permanencia 2015',0,1,3),(13,'Fase de Grupos',0,1,10),(14,'Primera Fase',1,1,11),(15,'Segunda Fase',1,1,11),(16,'Octavos de Final',1,1,11),(17,'Cuartos de Final',1,1,11),(18,'Semifinal',1,1,11),(19,'Primera Fase',1,1,12),(20,'Segunda Fase',0,1,12),(21,'Octavos de Final',1,1,12),(22,'Cuartos de Final',1,1,12),(23,'Semifinal',1,1,12),(24,'Fase Apertura',0,1,13),(26,'Fase Clausura ',0,1,13),(27,'Final',1,1,13),(28,'Semi Final',1,1,13),(29,'Fase 1',0,1,13),(30,'Fase  007',1,1,13),(31,'Grupo de Clasificatoria ',0,1,13),(32,'Final',1,1,12),(33,'Final',1,1,11);
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacion`
--

DROP TABLE IF EXISTS `formacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formacion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacion`
--

LOCK TABLES `formacion` WRITE;
/*!40000 ALTER TABLE `formacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `formacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `fase_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_fase1_idx` (`fase_id`),
  CONSTRAINT `fk_grupo_fase1` FOREIGN KEY (`fase_id`) REFERENCES `fase` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Grupo 1',1,3),(2,'Apertura 2014',1,1),(3,'Grupo 2',1,3),(4,'Grupo B',0,1),(5,'Clausura 2015',1,6),(6,'Grupo A',1,9),(7,'Grupo B',1,9),(8,'Grupo Centro Oriente',1,10),(9,'Grupo Centro Occidente',1,10),(10,'Ascenso 2015',1,11),(11,'Grupo Oriental',1,12),(12,'Grupo Central',1,12),(13,'Grupo Occidental',1,12),(14,'Grupo A',1,13),(15,'Grupo B',1,13),(16,'Grupo 1',1,20),(17,'Grupo 2',1,20),(18,'Grupo 3',1,20),(19,'Grupo 4',1,20),(20,'Grupo 5',1,20),(21,'Grupo 6',1,20),(22,'Grupo 7',1,20),(23,'Grupo 8',1,20),(24,'Grupo A',1,24),(25,'Grupo B',1,24),(26,'Grupo A',1,26),(27,'Grupo B',1,26);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagen`
--

DROP TABLE IF EXISTS `imagen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagen` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `nombre` varchar(150) DEFAULT NULL,
  `is_logo` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `cancha_id` int(10) unsigned DEFAULT NULL,
  `patrocinante_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `persona_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_imagen_asociacion1_idx` (`asociacion_id`),
  KEY `fk_imagen_cancha1_idx` (`cancha_id`),
  KEY `fk_imagen_patrocinante1_idx` (`patrocinante_id`),
  KEY `fk_imagen_equipo1_idx` (`equipo_id`),
  KEY `fk_imagen_persona1_idx` (`persona_id`),
  KEY `fk_imagen_club1_idx` (`club_id`),
  KEY `fk_imagen_competicion1_idx` (`competicion_id`),
  CONSTRAINT `fk_imagen_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_patrocinante1` FOREIGN KEY (`patrocinante_id`) REFERENCES `patrocinante` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_imagen_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagen`
--

LOCK TABLES `imagen` WRITE;
/*!40000 ALTER TABLE `imagen` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornada` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `grupo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jornada_grupo1_idx` (`grupo_id`),
  CONSTRAINT `fk_jornada_grupo1` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` VALUES (1,1,NULL,1,1),(4,2,NULL,1,1),(5,1,'Fecha 1',1,2),(6,2,NULL,1,2),(7,3,NULL,1,2),(8,4,NULL,1,2),(9,5,NULL,1,2),(10,6,NULL,1,2),(11,7,NULL,1,2),(12,8,NULL,1,2),(13,9,NULL,1,2),(14,10,NULL,1,2),(15,11,NULL,1,2),(16,12,NULL,1,2),(17,13,NULL,1,2),(18,14,NULL,1,2),(19,15,NULL,1,2),(20,16,NULL,1,2),(21,17,NULL,1,2),(22,1,NULL,1,5),(23,2,NULL,1,5),(24,3,NULL,1,5),(25,4,NULL,1,5),(26,5,NULL,1,5),(27,6,NULL,1,5),(28,7,NULL,1,5),(29,8,NULL,1,5),(30,9,NULL,1,5),(31,10,NULL,1,5),(32,1,NULL,1,6),(33,1,NULL,1,7),(34,2,NULL,1,6),(35,2,NULL,1,7),(36,1,NULL,1,8),(37,1,NULL,1,9),(38,1,NULL,1,11),(39,1,NULL,1,12),(40,1,NULL,1,13),(41,1,NULL,1,14),(42,1,NULL,1,15),(43,1,NULL,1,16),(44,1,NULL,1,26),(45,2,NULL,1,26),(46,3,NULL,1,26),(47,1,NULL,1,27),(48,2,NULL,1,27),(49,3,NULL,1,27),(50,3,'Fecha 3',1,1);
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `persona_id` int(10) unsigned NOT NULL,
  `posicion_id` int(10) unsigned DEFAULT NULL,
  `camiseta` int(11) DEFAULT NULL,
  `zurdo` int(11) DEFAULT NULL COMMENT 'zurdo: 0, para derecho; 1; para zurdo; 2 ambidiestro',
  `altura` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `agente_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_persona1_idx` (`persona_id`),
  KEY `fk_jugador_posicion1_idx` (`posicion_id`),
  KEY `fk_jugador_agente1_idx` (`agente_id`),
  CONSTRAINT `fk_jugador_agente1` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_posicion1` FOREIGN KEY (`posicion_id`) REFERENCES `posicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,11,8,18,1,1.72,62,1,NULL),(2,12,7,8,1,1.78,87,1,NULL),(3,13,6,4,1,1.86,84,1,NULL),(4,14,2,12,1,1.72,62,1,NULL),(13,29,8,10,0,1.78,70,1,NULL),(18,37,1,14,1,0,0,1,NULL),(19,38,1,0,1,0,0,1,NULL),(20,39,NULL,0,1,0,0,1,NULL),(21,40,NULL,0,1,0,0,1,NULL),(22,47,2,1,1,1.86,72,1,NULL),(23,48,9,24,1,1.86,72,0,NULL),(24,49,8,17,1,1.86,72,0,NULL),(26,51,3,6,0,1.86,78,1,NULL),(27,52,4,2,1,1.78,70,1,NULL),(28,53,6,4,1,1.82,72,1,NULL),(29,54,6,24,1,1.8,80,1,NULL),(30,55,8,13,1,1.69,59,1,NULL),(31,56,7,15,1,1.83,75,1,NULL),(32,57,8,18,2,1.69,60,1,NULL),(33,58,8,17,0,1.79,70,1,NULL),(34,59,1,9,1,1.81,75,1,NULL),(35,60,1,27,1,1.8,71,1,NULL),(36,73,2,1,1,1.86,86,1,NULL);
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador_premio`
--

DROP TABLE IF EXISTS `jugador_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador_premio` (
  `jugador_id` int(10) unsigned NOT NULL,
  `premio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`jugador_id`,`premio_id`),
  KEY `fk_jugador_has_premio_premio1_idx` (`premio_id`),
  KEY `fk_jugador_has_premio_jugador1_idx` (`jugador_id`),
  CONSTRAINT `fk_jugador_has_premio_jugador1` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_premio_premio1` FOREIGN KEY (`premio_id`) REFERENCES `premio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador_premio`
--

LOCK TABLES `jugador_premio` WRITE;
/*!40000 ALTER TABLE `jugador_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugador_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador_titulo`
--

DROP TABLE IF EXISTS `jugador_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador_titulo` (
  `jugador_id` int(10) unsigned NOT NULL,
  `titulo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`jugador_id`,`titulo_id`),
  KEY `fk_jugador_has_titulo_titulo1_idx` (`titulo_id`),
  KEY `fk_jugador_has_titulo_jugador1_idx` (`jugador_id`),
  CONSTRAINT `fk_jugador_has_titulo_jugador1` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_titulo_titulo1` FOREIGN KEY (`titulo_id`) REFERENCES `titulo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador_titulo`
--

LOCK TABLES `jugador_titulo` WRITE;
/*!40000 ALTER TABLE `jugador_titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugador_titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `llave`
--

DROP TABLE IF EXISTS `llave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `llave` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `fase_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_llave_fase1_idx` (`fase_id`),
  CONSTRAINT `fk_llave_fase1` FOREIGN KEY (`fase_id`) REFERENCES `fase` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llave`
--

LOCK TABLES `llave` WRITE;
/*!40000 ALTER TABLE `llave` DISABLE KEYS */;
INSERT INTO `llave` VALUES (1,'Final Campeón Apertura vs Campeón Clausura',1,7),(2,'Octavos',1,4),(3,'Llave Ida',1,9),(4,'Llave Vuelta',1,9),(5,'Zona Sur-Llave G1',1,14),(6,'Zona Sur-Llave G2',1,14),(7,'Zona Sur-Llave G3',1,14),(8,'Zona Sur-Llave G4',1,14),(9,'Zona Sur-Llave G5',1,14),(10,'Zona Sur-Llave G6',1,14),(11,'Zona Sur-Llave G7',1,14),(12,'Zona Sur-Llave G8',1,14),(13,'Zona Norte-Llave G9',1,14),(14,'Zona Norte-Llave G10',1,14),(15,'Zona Norte-Llave G11',1,14),(16,'Zona Norte-Llave G12',1,14),(17,'Zona Norte-Llave G13',1,14),(18,'Zona Norte-Llave G14',1,14),(19,'Zona Norte-Llave G15',1,14),(20,'Zona Norte-Llave G16',1,14),(21,'Llave O1',1,15),(22,'Llave O2',1,15),(23,'Llave O3',1,15),(24,'Llave O4',1,15),(25,'Llave O5',1,15),(26,'Llave O6',1,15),(27,'Llave O7',1,15),(28,'Llave O8',1,15),(29,'Llave O9',1,15),(30,'Llave O10',1,15),(31,'Llave O11',1,15),(32,'Llave O12',1,15),(33,'Llave O13',1,15),(34,'Llave O14',1,15),(35,'Llave O15',1,15),(36,'Llave A',1,16),(37,'Llave B',1,16),(38,'Llave C',1,16),(39,'Llave D',1,16),(40,'Llave E',1,16),(41,'Llave F',1,16),(42,'Llave G',1,16),(43,'Llave H',1,16),(44,'Llave S1',1,17),(45,'Llave S2',1,17),(46,'Llave S3',1,17),(47,'Llave S4',1,17),(48,'Semifinal F1',1,18),(49,'Semifinal F2',1,18),(50,'Llave G1',1,19),(51,'Llave G2',1,19),(52,'Llave G3',1,19),(53,'Llave G4',1,19),(54,'Llave G5',1,19),(55,'Llave G6',1,19),(56,'A1',1,27),(57,'A3',1,27),(59,'A1',1,28),(60,'A3',1,28),(61,'Final',1,33);
/*!40000 ALTER TABLE `llave` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `observaciones` text,
  `equipo_local_id` int(10) unsigned DEFAULT NULL,
  `equipo_visitante_id` int(10) unsigned DEFAULT NULL,
  `equipo_local_text` varchar(45) DEFAULT NULL,
  `equipo_visitante_text` varchar(45) DEFAULT NULL,
  `cancha_id` int(10) unsigned DEFAULT NULL,
  `tx_tv` int(11) DEFAULT NULL,
  `tx_radio` int(11) DEFAULT NULL,
  `asistencia` int(11) DEFAULT NULL,
  `status_partido_id` int(10) unsigned NOT NULL,
  `llave_id` int(10) unsigned DEFAULT NULL,
  `jornada_id` int(10) unsigned DEFAULT NULL,
  `goles_equipo_local` int(11) DEFAULT NULL,
  `goles_equipo_visitante` int(11) DEFAULT NULL,
  `goles_local_prorroga` int(11) DEFAULT NULL,
  `goles_visitante_prorroga` int(11) DEFAULT NULL,
  `goles_local_penalties` int(11) DEFAULT NULL,
  `goles_visitante_penalties` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_equipo1_idx` (`equipo_local_id`),
  KEY `fk_partido_equipo2_idx` (`equipo_visitante_id`),
  KEY `fk_partido_cancha1_idx` (`cancha_id`),
  KEY `fk_partido_status_partido1_idx` (`status_partido_id`),
  KEY `fk_partido_llave1_idx` (`llave_id`),
  KEY `fk_partido_jornada1_idx` (`jornada_id`),
  CONSTRAINT `fk_partido_cancha1` FOREIGN KEY (`cancha_id`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_equipo1` FOREIGN KEY (`equipo_local_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_equipo2` FOREIGN KEY (`equipo_visitante_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_jornada1` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_llave1` FOREIGN KEY (`llave_id`) REFERENCES `llave` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_status_partido1` FOREIGN KEY (`status_partido_id`) REFERENCES `status_partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,'2014-06-26','16:00:00',NULL,NULL,1,8,NULL,NULL,1,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2014-06-01','14:00:00',NULL,NULL,NULL,NULL,'1ro Grupo A','2do Grupo B',NULL,NULL,NULL,NULL,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_arbitro`
--

DROP TABLE IF EXISTS `partido_arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_arbitro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `partido_id` int(10) unsigned NOT NULL,
  `arbitro_id` int(10) unsigned NOT NULL,
  `tipo_arbitro_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_has_arbitro_arbitro1_idx` (`arbitro_id`),
  KEY `fk_partido_has_arbitro_partido1_idx` (`partido_id`),
  KEY `fk_partido_has_arbitro_tipo_arbitro1_idx` (`tipo_arbitro_id`),
  CONSTRAINT `fk_partido_has_arbitro_arbitro1` FOREIGN KEY (`arbitro_id`) REFERENCES `arbitro` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_arbitro_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_arbitro_tipo_arbitro1` FOREIGN KEY (`tipo_arbitro_id`) REFERENCES `tipo_arbitro` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_arbitro`
--

LOCK TABLES `partido_arbitro` WRITE;
/*!40000 ALTER TABLE `partido_arbitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_evento`
--

DROP TABLE IF EXISTS `partido_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_evento` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `partido_id` int(10) unsigned NOT NULL,
  `evento_id` int(10) unsigned NOT NULL,
  `minuto` int(11) DEFAULT NULL,
  `jugador1_id` int(10) unsigned DEFAULT NULL,
  `jugador2_id` int(10) unsigned DEFAULT NULL,
  `staff_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_has_evento_evento1_idx` (`evento_id`),
  KEY `fk_partido_has_evento_partido1_idx` (`partido_id`),
  KEY `fk_partido_evento_jugador1_idx` (`jugador1_id`),
  KEY `fk_partido_evento_jugador2_idx` (`jugador2_id`),
  KEY `fk_partido_evento_staff1_idx` (`staff_id`),
  CONSTRAINT `fk_partido_evento_jugador1` FOREIGN KEY (`jugador1_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_jugador2` FOREIGN KEY (`jugador2_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_evento_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_evento_evento1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_has_evento_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_evento`
--

LOCK TABLES `partido_evento` WRITE;
/*!40000 ALTER TABLE `partido_evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrocinante`
--

DROP TABLE IF EXISTS `patrocinante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrocinante` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `is_marca_equipacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrocinante`
--

LOCK TABLES `patrocinante` WRITE;
/*!40000 ALTER TABLE `patrocinante` DISABLE KEYS */;
/*!40000 ALTER TABLE `patrocinante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'administrador'),(2,'supervisor'),(3,'editor'),(4,'consultor');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (11,'Alejandro','Guerra','M',NULL,'11954911','2014-01-03','Venezuela','(4352) 425.4345','(1232) 412.2212','felixe@gasd.com',NULL,'jugador1401896753856',24),(12,'Edgar','Jiménez','M','','4.615.101','2014-01-03','Venezuela','(1111) 111.1111','(2222) 222.2222','fggaffa@gmail.com',NULL,'jugador1401897475576',25),(13,'Julio','Machado','M','','54615101','2014-01-03','Venezuela','(3333) 333.3333','(9897) 879.7944','fggaffa@gmail.com',NULL,'jugador1401902052007',26),(14,'Wilber','Jiménez','M',NULL,'754615101','2014-01-03','Venezuela','(2510) 214.5111','(0412) 958.4111','fggaffa@gmail.com',NULL,'jugador1401902762483',27),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'José','Ángel','M',NULL,'18111459','1987-01-30','Venezuela','(0212) 442.6577','04143517291',NULL,NULL,NULL,29),(18,'Hola ','Cha','',NULL,'123456','2014-01-01','Algeria','(1231) 313.1313','32131313','jose@asd.com',NULL,NULL,30),(19,'asdasda','dadada','M',NULL,'18123123','2014-01-15','Algeria','(1231) 123.1321','123123123',NULL,NULL,NULL,31),(20,'ererere','asdasdasd','M',NULL,'12312313',NULL,'American Samoa','(1333) 111.3333','12312313',NULL,NULL,NULL,32),(21,'asdasda','asdasdasd','F',NULL,'1231231','2014-01-01','American Samoa','(1232) 123.1321','123123123',NULL,NULL,NULL,33),(29,'Daniel','Febles','M',NULL,'7.987.098','2014-01-14','Venezuela','(1231) 221.2112','(1231) 212.1221','FAHOLA@GMAIL.COM',NULL,'jugador1401903078988',41),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,42),(31,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,43),(32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,44),(33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,45),(36,'Hola','Chao','F',NULL,'1234','2014-01-01','','(1231) 231.2312','(1231) 231.2312','asd@asd.com',NULL,NULL,57),(37,'Héctor','Pérez','M','\"Tico\"','1997114','2014-01-15','Venezuela','','','jua.qdm@cc.com',NULL,'jugador1400599266685',65),(38,'Mario ','Calderon','M',NULL,'54543','2004-01-01','Venezuela','','','aismd@giasi.com',NULL,'jugador1400599621479',66),(39,'Juan ','Don Juan ','',NULL,'4847989','2014-01-08','Curacao','','','juan.dongua@gotmail.com',NULL,'jugador1400600677688',70),(40,'Oscar ','Can','M',NULL,'8778989','2006-01-10','Bolivia','','','oso.peligroso@gmail.com',NULL,'jugador1400601111982',71),(41,'María ','Marchena','M',NULL,'45544','2014-01-20','Cambodia','(1231) 231.3131','(1231) 231.2312','asd@asd.com',NULL,NULL,105),(42,'María ','Marchena','M',NULL,'45544','2014-01-20','Cambodia','(1231) 231.3131','(1231) 231.2312','asd@asd.com',NULL,NULL,106),(43,'Miguel','Cordero','M',NULL,'12345','2014-01-01','Venezuela','(1212) 455.1521','(1231) 123.1231','asd@asd.com',1,'staff1402516375312',108),(44,'Pedro','Romero','M',NULL,'12345','2014-01-01','Saint Helena','(1212) 455.1521','(1231) 123.1231','asd@asd.com',NULL,NULL,109),(45,'Rafael','Esquivel','M',NULL,'54321','2014-01-08','Venezuela','(1231) 231.2312','(1231) 231.2312','ja@asd.com',1,'staff1402517804445',110),(46,'Elio','Quintal','M',NULL,'3.948.489','2014-01-06','Venezuela','(0212) 234.5676','(0414) 345.5678','quintal@gmail.com',NULL,NULL,111),(47,'Alain','Baroja','M',NULL,'17.368.678','2004-01-23','Venezuela','(0212) 635.8281','(0414) 479.9203','alainb@gmail.com',NULL,'jugador1401832693296',114),(48,'Anthony','Uribe','M','','15.987.309','2004-01-09','Venezuela','','','wjime@gmail.com',NULL,'jugador1402592242123',115),(49,'Héctor','Pérez','M','\"Tico\"','23.983.098','2004-01-02','Venezuela','','','wjime@gmail.com',NULL,'jugador1402592763057',116),(51,'Rubert ','Quijada','M',NULL,'18.987.287','2004-01-03','Venezuela','','','',NULL,'jugador1402015952252',119),(52,'Francisco','Carabalí','M',NULL,'17.298.298','2004-01-03','Venezuela','','','',NULL,'jugador1402017510558',120),(53,'Roberto ','Tucker','M',NULL,'14.876.234','2004-01-02','Argentina','','','',NULL,'jugador1402017709663',121),(54,'Andrés','Sánchez','M',NULL,'14.762.367','2004-01-14','Venezuela','','','',NULL,'jugador1402017862356',122),(55,'Rómulo','Otero','M',NULL,'19.789.098','2004-01-25','Venezuela','','','',NULL,'jugador1402017984309',123),(56,'Ricardo','Andreutti','M',NULL,'17.276.298','2004-01-30','Venezuela','','','',NULL,'jugador1402018117174',124),(57,'Luis','González','M',NULL,'22.098.987','2004-01-29','Venezuela','','','',NULL,'jugador1402018421581',125),(58,'Félix ','Cásseres','M',NULL,'19.873.398','2004-01-27','Venezuela','','','',NULL,'jugador1402018608049',126),(59,'Dany','Cure','M',NULL,'21.897.267','2004-01-14','Venezuela','','','',NULL,'jugador1402018734123',127),(60,'Edder ','Farías','M',NULL,'15.874.398','2004-01-17','Venezuela','','','',NULL,'jugador1402018893912',128),(61,'Eduardo ','Saragó','M',NULL,'12.987.365','2004-01-03','Venezuela','','','',1,NULL,129),(63,'Antonio','Del Corral','M',NULL,'23456789','1975-01-11','Venezuela','','','',NULL,'staff1402435154453',135),(66,'Juanita','Alimaña','F',NULL,'3216547','1974-01-03','Venezuela','','','',NULL,'arbitro1402515131700',138),(67,'Otrova','Gomaz','M',NULL,'3215487','1981-01-24','Venezuela','','','',NULL,'vacio',139),(68,'Miguel Ángel','Romero','M',NULL,'16.338.764','2004-01-01','Venezuela','','','',NULL,'staff1402517116764',141),(69,'Roberto','Cara e Piedra','M',NULL,'','1969-01-02','Venezuela','','','',NULL,'arbitro1402521028284',142),(71,'Felix','Angulo','M',NULL,'20.233.321',NULL,'','','','',NULL,'vacio',144),(72,'Felix','Angulo','M',NULL,'',NULL,'Venezuela','','','',NULL,'vacio',145),(73,'José David','Contreras','M','','22.765.909','1994-01-20','Venezuela','','','',NULL,'jugador1402622024156',147),(74,'José','Argote','M',NULL,'14.876.287','1980-01-22','Venezuela','','','',NULL,'vacio',148);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posicion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion`
--

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
INSERT INTO `posicion` VALUES (1,'Delantero',1),(2,'Arquero',1),(3,'Lateral izquierdo',1),(4,'Lateral derecho',1),(6,'Defensor Central',1),(7,'Volante de Marca',1),(8,'Volante Creativo',1),(9,'Atacante',1),(10,'Defensa',1),(11,'Mediocampista',1),(12,'Volante',1),(13,'otra posición',1),(14,'nueva posición',1);
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premio`
--

DROP TABLE IF EXISTS `premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premio` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premio`
--

LOCK TABLES `premio` WRITE;
/*!40000 ALTER TABLE `premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `red_social`
--

DROP TABLE IF EXISTS `red_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `red_social` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario` varchar(80) DEFAULT NULL,
  `persona_id` int(10) unsigned DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `tipo_red_social_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_red_social_persona1_idx` (`persona_id`),
  KEY `fk_red_social_asociacion1_idx` (`asociacion_id`),
  KEY `fk_red_social_club1_idx` (`club_id`),
  KEY `fk_red_social_competicion1_idx` (`competicion_id`),
  KEY `fk_red_social_equipo1_idx` (`equipo_id`),
  KEY `fk_red_social_tipo_red_social1_idx` (`tipo_red_social_id`),
  CONSTRAINT `fk_red_social_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_red_social_tipo_red_social1` FOREIGN KEY (`tipo_red_social_id`) REFERENCES `tipo_red_social` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `red_social`
--

LOCK TABLES `red_social` WRITE;
/*!40000 ALTER TABLE `red_social` DISABLE KEYS */;
INSERT INTO `red_social` VALUES (6,'ddssdd',NULL,NULL,NULL,NULL,NULL,2),(7,'ddssdd',NULL,NULL,NULL,NULL,NULL,1),(12,'felix180',11,NULL,NULL,NULL,NULL,2),(15,'felix180',13,NULL,NULL,NULL,NULL,1),(16,'gfff',14,NULL,NULL,NULL,NULL,1),(18,'joseamromero',17,NULL,NULL,NULL,NULL,2),(19,'jose.a.m.romero',17,NULL,NULL,NULL,NULL,1),(20,'joseamromero',17,NULL,NULL,NULL,NULL,3),(22,'sasd',19,NULL,NULL,NULL,NULL,2),(23,'hhhhhh',20,NULL,NULL,NULL,NULL,1),(24,'ddddasdasd',21,NULL,NULL,NULL,NULL,1),(25,'felix189',12,NULL,NULL,NULL,NULL,2),(30,'TUKA180',12,NULL,NULL,NULL,NULL,3),(33,'Hola',14,NULL,NULL,NULL,NULL,1),(35,'elhey',11,NULL,NULL,NULL,NULL,1),(44,'jaja',36,NULL,NULL,NULL,NULL,1),(45,'@alainbaroja',47,NULL,NULL,NULL,NULL,2),(46,'@alainbaroja',47,NULL,NULL,NULL,NULL,2),(47,'prueba1',43,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `red_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `persona_id` int(10) unsigned NOT NULL,
  `cargo_id` int(10) unsigned DEFAULT NULL,
  `asociacion_id` int(10) unsigned DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `competicion_id` int(10) unsigned DEFAULT NULL,
  `club_id` int(10) unsigned DEFAULT NULL,
  `partido_id` int(10) unsigned DEFAULT NULL,
  `agente_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_staff_asociacion1_idx` (`asociacion_id`),
  KEY `fk_staff_persona1_idx` (`persona_id`),
  KEY `fk_staff_equipo1_idx` (`equipo_id`),
  KEY `fk_staff_competicion1_idx` (`competicion_id`),
  KEY `fk_staff_club1_idx` (`club_id`),
  KEY `fk_staff_cargo1_idx` (`cargo_id`),
  KEY `fk_staff_partido1_idx` (`partido_id`),
  KEY `fk_staff_agente1_idx` (`agente_id`),
  CONSTRAINT `fk_staff_agente1` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_asociacion1` FOREIGN KEY (`asociacion_id`) REFERENCES `asociacion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_partido1` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (6,1,NULL,NULL,43,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,0,NULL,'2014-01-10',43,1,NULL,NULL,NULL,1,NULL,NULL),(10,1,NULL,NULL,43,2,NULL,NULL,NULL,3,NULL,NULL),(11,1,NULL,NULL,45,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,1,NULL,NULL,45,1,NULL,NULL,NULL,5,NULL,NULL),(13,1,NULL,NULL,46,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,0,NULL,'2014-01-11',46,4,NULL,NULL,NULL,1,NULL,NULL),(15,1,NULL,NULL,61,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,0,NULL,'2014-01-11',61,6,NULL,NULL,NULL,1,NULL,NULL),(17,0,NULL,'2014-01-11',43,1,NULL,1,NULL,NULL,NULL,NULL),(18,0,NULL,'2014-01-04',45,3,NULL,NULL,1,NULL,NULL,NULL),(19,0,NULL,'2014-01-11',43,2,NULL,NULL,1,NULL,NULL,NULL),(20,0,NULL,'2014-01-11',45,4,1,NULL,NULL,NULL,NULL,NULL),(22,1,NULL,NULL,63,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,1,NULL,NULL,61,6,NULL,1,NULL,NULL,NULL,NULL),(24,1,NULL,NULL,68,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,1,NULL,NULL,43,5,NULL,1,NULL,NULL,NULL,NULL),(26,1,NULL,NULL,68,3,NULL,1,NULL,NULL,NULL,NULL),(27,1,NULL,NULL,45,4,9,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_has_premio`
--

DROP TABLE IF EXISTS `staff_has_premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_has_premio` (
  `staff_id` int(10) unsigned NOT NULL,
  `premio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`staff_id`,`premio_id`),
  KEY `fk_staff_has_premio_premio1_idx` (`premio_id`),
  KEY `fk_staff_has_premio_staff1_idx` (`staff_id`),
  CONSTRAINT `fk_staff_has_premio_premio1` FOREIGN KEY (`premio_id`) REFERENCES `premio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_has_premio_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_has_premio`
--

LOCK TABLES `staff_has_premio` WRITE;
/*!40000 ALTER TABLE `staff_has_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_has_premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_has_titulo`
--

DROP TABLE IF EXISTS `staff_has_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_has_titulo` (
  `staff_id` int(10) unsigned NOT NULL,
  `titulo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`staff_id`,`titulo_id`),
  KEY `fk_staff_has_titulo_titulo1_idx` (`titulo_id`),
  KEY `fk_staff_has_titulo_staff1_idx` (`staff_id`),
  CONSTRAINT `fk_staff_has_titulo_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_has_titulo_titulo1` FOREIGN KEY (`titulo_id`) REFERENCES `titulo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_has_titulo`
--

LOCK TABLES `staff_has_titulo` WRITE;
/*!40000 ALTER TABLE `staff_has_titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_has_titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_partido`
--

DROP TABLE IF EXISTS `status_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_partido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` int(11) DEFAULT NULL COMMENT '0: no comenzado\n1: primer tiempo\n2: segundo tiempo\n3: finalizado',
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_partido`
--

LOCK TABLES `status_partido` WRITE;
/*!40000 ALTER TABLE `status_partido` DISABLE KEYS */;
INSERT INTO `status_partido` VALUES (1,0,' ');
/*!40000 ALTER TABLE `status_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `telefono` varchar(45) NOT NULL,
  `direccion_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_telefono_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_telefono_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=620 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (1,'(0412) 938.0356',1),(2,'(0412) 938.0355',3),(3,'(0251) 251.6541',3),(7,'(0212) 456.7891',4),(31,'(0251) 111.1154',5),(34,'(4565) 444.4444',13),(37,'(4867) 645.6456',13),(39,'(5418) 654.6455',48),(40,'(0216) 549.8721',49),(41,'(5418) 654.6455',49),(42,'(5418) 654.6455',50),(43,'(3265) 548.7987',51),(44,'(4565) 444.4444',52),(45,'(4867) 645.6456',52),(46,'(3213) 213.2132',52),(47,'(3216) 549.8765',53),(48,'(4565) 444.4444',53),(49,'(4867) 645.6456',53),(50,'(4565) 444.4444',54),(51,'(4867) 645.6456',54),(52,'(5418) 654.6455',58),(53,'(5418) 654.6455',58),(54,'(5418) 654.6455',59),(55,'(0212) 456.7845',60),(56,'(5418) 654.6455',61),(57,'(5418) 654.6455',61),(58,'(5418) 654.6455',62),(59,'(5418) 654.6455',63),(60,'(5418) 654.6455',64),(61,'(3216) 549.8765',69),(62,'(3216) 549.8765',74),(63,'(6549) 876.5432',78),(66,'(6546) 549.8798',83),(67,'(8979) 865.4213',84),(68,'(9876) 513.2165',85),(69,'(5821) 278.1559',86),(70,'(6549) 876.5432',89),(71,'(6549) 876.5132',89),(72,'(6549) 876.5432',90),(73,'(6549) 876.5432',90),(74,'(6549) 876.5432',91),(75,'(6549) 876.5432',92),(76,'(6549) 876.5432',92),(77,'(6549) 876.5432',93),(78,'(6549) 876.5433',93),(79,'(6549) 876.5432',94),(80,'(6549) 876.5432',94),(81,'(6549) 876.5432',95),(82,'(6549) 876.5432',95),(83,'(6549) 876.5432',96),(84,'(6549) 876.5432',96),(85,'(6549) 876.5432',97),(86,'(6549) 876.5432',98),(87,'(6549) 876.5432',98),(88,'(6549) 876.5432',99),(89,'(6549) 876.5432',99),(90,'(6549) 876.5432',99),(91,'(6549) 876.5432',100),(92,'(6549) 876.5432',100),(93,'(6549) 876.5432',100),(94,'(6549) 876.5432',101),(95,'(6549) 876.5432',101),(96,'(0212) 781.2131',101),(97,'(0212) 546.9875',102),(98,'(0212) 576.9867',107),(618,'(5649) 876.5432',64),(619,'(1564) 545.6456',51);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporada`
--

DROP TABLE IF EXISTS `temporada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporada` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `competicion_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temporada_competicion1_idx` (`competicion_id`),
  CONSTRAINT `fk_temporada_competicion1` FOREIGN KEY (`competicion_id`) REFERENCES `competicion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporada`
--

LOCK TABLES `temporada` WRITE;
/*!40000 ALTER TABLE `temporada` DISABLE KEYS */;
INSERT INTO `temporada` VALUES (1,'Temporada 2014-2015','2014-01-10','2015-01-31',1,1),(2,'Temporada 2013-2014','2013-01-18','2014-01-25',1,1),(3,'Temporada 2014-2015','2014-01-09','2015-01-02',1,2),(4,'Temporada 2014-2015','2014-01-05','2014-01-15',1,5),(5,'Temporada 13','2014-01-14','2014-01-16',1,2),(6,'hhffg','2014-01-13','2014-01-15',1,2),(7,'hola 2','2014-01-05','2014-01-06',1,2),(8,'Tempordad 2012-2013','2014-01-01','2014-01-20',1,1),(9,'temporada 2014 -2015','2014-01-01','2015-01-01',1,3),(10,'Temporada 2015','2016-01-02','2015-01-25',1,6),(11,'Copa Sudamericana 2014','2014-01-19','2014-01-16',1,8),(12,'Copa Libertadores 2015','2015-01-29','2015-01-06',1,9),(13,'Temporada 2013-2014',NULL,NULL,1,5),(14,'Temporada 2012-2013',NULL,NULL,1,5);
/*!40000 ALTER TABLE `temporada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporada_categoria`
--

DROP TABLE IF EXISTS `temporada_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporada_categoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `temporada_id` int(10) unsigned NOT NULL,
  `categoria_id` int(10) unsigned NOT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temporada_has_categoria_categoria1_idx` (`categoria_id`),
  KEY `fk_temporada_has_categoria_temporada1_idx` (`temporada_id`),
  CONSTRAINT `fk_temporada_has_categoria_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_temporada_has_categoria_temporada1` FOREIGN KEY (`temporada_id`) REFERENCES `temporada` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporada_categoria`
--

LOCK TABLES `temporada_categoria` WRITE;
/*!40000 ALTER TABLE `temporada_categoria` DISABLE KEYS */;
INSERT INTO `temporada_categoria` VALUES (36,9,4,NULL,NULL),(37,9,2,NULL,NULL),(40,1,8,NULL,NULL),(41,3,9,NULL,NULL),(42,4,1,NULL,NULL),(43,4,1,NULL,NULL),(45,2,8,NULL,NULL),(46,8,8,NULL,NULL),(47,11,8,NULL,NULL),(48,12,8,NULL,NULL);
/*!40000 ALTER TABLE `temporada_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_arbitro`
--

DROP TABLE IF EXISTS `tipo_arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_arbitro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_arbitro`
--

LOCK TABLES `tipo_arbitro` WRITE;
/*!40000 ALTER TABLE `tipo_arbitro` DISABLE KEYS */;
INSERT INTO `tipo_arbitro` VALUES (1,'Principal'),(2,'Asistente 1'),(3,'Asistente 2'),(4,'Cuarto Arbitro'),(5,'Asesor Arbitral'),(6,'Internacionales'),(7,'Nacionales'),(8,'Regionales');
/*!40000 ALTER TABLE `tipo_arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_red_social`
--

DROP TABLE IF EXISTS `tipo_red_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_red_social` (
  `id` int(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_red_social`
--

LOCK TABLES `tipo_red_social` WRITE;
/*!40000 ALTER TABLE `tipo_red_social` DISABLE KEYS */;
INSERT INTO `tipo_red_social` VALUES (1,'facebook','http://www.facebook.com'),(2,'twitter','http://www.twitter.com'),(3,'instagram','http://www.instagram.com');
/*!40000 ALTER TABLE `tipo_red_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titulo`
--

DROP TABLE IF EXISTS `titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titulo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `id_temporada` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titulo`
--

LOCK TABLES `titulo` WRITE;
/*!40000 ALTER TABLE `titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencia`
--

DROP TABLE IF EXISTS `transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencia` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jugador_id` int(10) unsigned NOT NULL,
  `equipo_provedor_id` int(10) unsigned NOT NULL,
  `equipo_receptor_id` int(10) unsigned NOT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_egreso` date DEFAULT NULL,
  `alta_baja` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_has_equipo_equipo1_idx` (`equipo_provedor_id`),
  KEY `fk_jugador_has_equipo_jugador1_idx` (`jugador_id`),
  KEY `fk_transferencia_equipo1_idx` (`equipo_receptor_id`),
  CONSTRAINT `fk_jugador_has_equipo_equipo1` FOREIGN KEY (`equipo_provedor_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_has_equipo_jugador1` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_transferencia_equipo1` FOREIGN KEY (`equipo_receptor_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia`
--

LOCK TABLES `transferencia` WRITE;
/*!40000 ALTER TABLE `transferencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencia` ENABLE KEYS */;
UNLOCK TABLES;

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
  `status` int(11) NOT NULL,
  `perfil_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_perfil1_idx` (`perfil_id`),
  CONSTRAINT `fk_user_perfil1` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Juan','Admin','jadmin','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','jadmin@loquesea.com','1234567',1,1),(4,'Joseba','Baroja','jbaroja','7609c9ba3862fc9a8508a1d53cc2a77d2301961fb9b016a4aca4ea0f66bff3bb','jbaroja@loquesea.com','(0212) 781.2131',1,2),(9,'Ruben','Sarabia','rsarabia','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','rsarabia@spontecorp.com','(0212) 781.5591',1,4),(10,'Gustavo','Veraz','vera','525ba009c0050360237e8952a3ba6b475617c97a1fa6629bee0ea43b36dd4b63','gustavo1@vera.com','(0212) 546.4987',1,1),(11,'Alejandro','Barreras','lbarrera','456789','luis.barrera@lkhjdsf.com','(0212) 555.5555',1,2),(12,'Luis','Barrera','lbarrera','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','luis.barrera@lkhjdsf.com','(0212) 555.5555',1,2),(13,'Pedro','Perez','pperez','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','pperez@lhgdsf.com','(0212) 789.4561',0,1),(14,'David','Rey','dreyes','c3380624269d6e6024318bc1c86f3a338a2ed8af9eef8ac64a4e9170e0806a4a','dreyes@uywter.com','(0212) 555.7894',1,1),(15,'Felix','Angulo','feliz','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','felix@fads.com','(0254) 255.1246',1,1),(16,'Jose','Pepote','jose123','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','pepe1@pepe.com','(0123) 456.7858',1,3),(17,'Jacinto','Milagro','milagro','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','milagro@loquesea.com','(1234) 567.9873',1,2),(18,'Teresa','Willians','teresa','3c7a11e1d847fd96928abf4984f382220a5f96a491a652ea24df6b4e25103b78','dasd@jhds.com','(0123) 456.7984',0,4),(19,'Jesús','García Regalado','jegaregalado','9fd065c617367af70bd81e9c9713af56472d0bc2fb77e0ea24ba685c1be7f8a5','jegaregalado@gmail.com','(0212) 761.6775',1,2);
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

-- Dump completed on 2014-06-13 16:08:16
