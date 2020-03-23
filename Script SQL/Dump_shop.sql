CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shop`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `composizione` (
  `ordine` int(11) NOT NULL,
  `quantità` varchar(45) DEFAULT NULL,
  `prodotto` int(11) NOT NULL,
  KEY `id_prodotto_idx` (`prodotto`),
  KEY `id_ordine_1_idx` (`ordine`),
  CONSTRAINT `id_ordine_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_prodotto_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
INSERT INTO `composizione` VALUES (1,'2',6),(1,'1',8),(1,'1',3),(2,'2',12),(3,'1',14),(4,'1',22),(5,'1',1),(5,'1',2),(5,'1',17),(5,'1',16),(6,'1',18),(6,'1',6),(14,'1',5);
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dipendente` (
  `id_dipendente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `ruolo` varchar(45) DEFAULT NULL,
  `isAdmin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_dipendente`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES (1,'Lorenzo Paolo','Cocchinone','Laurus','12345678','Amministratore',1),(2,'Mattia','Mascolo','MMascolo','12345678','Addetto Vendite',0),(3,'Giovanni','Rossi','GRossi','12345678','Amministratore',1),(4,'Silvia','Ferrari','SFerrari','12345678','Amministratore',1),(5,'Francesco','Lerro','FLerro','12345678','Addetto Vendite',0),(6,'Mario','Rossi','MRossi','12345678','Addetto Vendite',0),(7,'Francesca','Bianchi','FBiacnhi','12345678','Manager',0);
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ordine` (
  `id_ordine` int(11) NOT NULL AUTO_INCREMENT,
  `prezzo_totale` int(11) DEFAULT NULL,
  `data` timestamp NULL DEFAULT NULL,
  `utente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  UNIQUE KEY `id_ordine_UNIQUE` (`id_ordine`),
  KEY `id_utente_idx` (`utente`),
  CONSTRAINT `id_utente` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,149,'2019-07-11 09:25:10',6),(2,50,'2019-02-11 11:05:23',2),(3,60,'2019-05-30 13:20:52',7),(4,25,'2018-12-20 15:05:25',5),(5,618,'2019-06-30 08:45:11',2),(6,229,'2020-07-12 14:07:57',6),(14,499,'2020-07-12 14:09:17',6);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prodotto` (
  `id_prodotto` int(11) NOT NULL AUTO_INCREMENT,
  `prezzo` int(11) DEFAULT NULL,
  `volume` int(11) DEFAULT '0',
  `produttore` varchar(45) DEFAULT NULL,
  `genere` varchar(45) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `piattaforma` varchar(45) DEFAULT NULL,
  `titolo` varchar(45) DEFAULT NULL,
  `admin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_prodotto`),
  UNIQUE KEY `id_prodotto_UNIQUE` (`id_prodotto`),
  KEY `id_dipendente_idx` (`admin`),
  CONSTRAINT `id_dipendente` FOREIGN KEY (`admin`) REFERENCES `dipendente` (`id_dipendente`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,239,25,'Nintendo','Console','Nintendo','Switch','Switch Lite',1),(2,299,10,'Sony','Console','Sony','PS4','Play Station 4 Slim',1),(3,70,50,'Kojima Production','Action','Sony','PS4','Death Stranding',3),(4,20,5,'Game Freak','RPG','Nintendo','3DS','Pokémon X',4),(5,499,31,'Microsoft','Console','Microsoft','Xbox One','Xbox one X',4),(6,30,2,'343 Industries','FPS','Microsoft','Xbox One','Halo 5: Guardians',1),(7,19,3,'Bungie','FPS','Bungie','PS4','Destiny 2',4),(8,19,5,'Bungie','FPS','Bungie','Xbox One','Destiny 2',3),(9,45,56,'SIE Bend Studio','TPS','Sony','PS4','Days Gone',3),(10,20,12,'Santa Monica Studios','Action','Sony','PS4','God of War Remastered',1),(11,40,30,'Square Enix','MMORPG','Square Enix','PC','Final Fantasy XIV Online',4),(12,25,99,'Square Enix','CARD ABBONAMENTO','Square Enix','PC/PS4','Final Fantasy XIV 60 DAYS',1),(13,19,6,'Nintendo','Pellicola Protettiva','Nintendo','Switch','Pellicola Protetiva Switch',3),(14,60,2,'Sony','Controller','Sony','PS4','Controller PS4',1),(15,129,5,'Microsoft','Cuffie','Microsoft','Xbox one','Cuffie Xbox PRO',4),(16,30,2,'Nintendo','Custodia','Nintendo','Switch','Custodia Tema Zelda',3),(17,50,6,'Capcom','Action','Capcom','PS4','Devil May Cry 5',1),(18,199,4,'Microsoft','Console','Microsoft','Xbox one','Xbox One s',1),(19,10,6,'Square Enix','Action','Square Enix','Ps4','Final Fantasy XV',1),(20,45,9,'Square Enix','RPG','Square Enix','Switch','Final Fantasy X',1),(21,15,5,'Nintendo','Action','Nintendo','3DS','The Legend Of Zelda: Ocarina of Time',1),(22,25,6,'Ark Layer','Picchiaduro','Bandai Namco','Xbox one','Dragon Ball fighterZ',1);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spedizione`
--

DROP TABLE IF EXISTS `spedizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `spedizione` (
  `numero_spedizione` int(11) NOT NULL AUTO_INCREMENT,
  `corriere` varchar(45) DEFAULT NULL,
  `numero_tracking` varchar(45) DEFAULT '0',
  `stato` varchar(45) DEFAULT NULL,
  `ordine` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_spedizione`),
  UNIQUE KEY `numero_spedizione_UNIQUE` (`numero_spedizione`),
  KEY `id_ordine_idx` (`ordine`),
  CONSTRAINT `id_ordine_2` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spedizione`
--

LOCK TABLES `spedizione` WRITE;
/*!40000 ALTER TABLE `spedizione` DISABLE KEYS */;
INSERT INTO `spedizione` VALUES (1,'Bartolini','BR14258CX85693TO','In Consegna',1),(2,'GLS','BR12556CX52858TO','In Spedizione',2),(4,NULL,'0','in preparazione',14);
/*!40000 ALTER TABLE `spedizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utente` (
  `id_utente` int(11) NOT NULL AUTO_INCREMENT,
  `numero_tessera` int(11) DEFAULT '0',
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `acquisti_effettuati` int(11) DEFAULT '0',
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `punti_socio` int(11) DEFAULT '0',
  `rinnovo_tessera` timestamp NULL DEFAULT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_utente`),
  UNIQUE KEY `id_utente_UNIQUE` (`id_utente`),
  UNIQUE KEY `numero_tessera_UNIQUE` (`numero_tessera`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,1505,'Francesco','Rossi',25,'Frossi98','12345678',2540,'2020-07-10 22:00:00','Via Roma n°54, Caserta','f.rossi@gmail.com'),(2,1606,'Marria','Musso',30,'MMusso','12345678',3000,'2020-07-10 22:00:00','Via Firenze n°2, Milano','m.musso@outlook.iy'),(3,1052,'Marco','Ferrero',5,'MFerrero','12345678',250,'2020-07-10 22:00:00','Via Napoli n°152, Pordenone','m.ferrero@icloud.com'),(4,2396,'Laura','Palumbo',89,'LPalumbo','12345678',8000,'2020-07-10 22:00:00','Via Catania n°56, Bologna','l.palumbo@gmail.com'),(5,9632,'Silvia','Giallo',23,'SGiallo','12345678',2236,'2020-07-10 22:00:00','Via Milano n°36, Pisa','s.giallo@gmail.com'),(6,1000,'Lorenzo Paolo','Cocchinone',506,'LCocchinone','12345678',100000,'2022-07-11 00:00:00','Via Trieste n°4, Recale','l.cocchinone@gmail.com'),(7,5263,'Elena','Bianco',6,'EBianco','12345678',123,'2020-07-10 22:00:00','Via genova n°563, Torino','e.bianco@gmail.com'),(8,5862,'Francesco','Rossi',0,'FRossi','12345678',0,'2020-07-11 13:12:06','Via Pisa n°2, Bari','f.rossi@icloud.com');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-12 17:55:10
