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
-- Table structure for table `addetto_vendite`
--

DROP TABLE IF EXISTS `addetto_vendite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `addetto_vendite` (
  `dipendente_vendite` int(11) NOT NULL,
  PRIMARY KEY (`dipendente_vendite`),
  CONSTRAINT `id_personale_1` FOREIGN KEY (`dipendente_vendite`) REFERENCES `personale` (`id_personale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addetto_vendite`
--

LOCK TABLES `addetto_vendite` WRITE;
/*!40000 ALTER TABLE `addetto_vendite` DISABLE KEYS */;
/*!40000 ALTER TABLE `addetto_vendite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `composizione` (
  `utente` int(11) NOT NULL,
  `quantità` varchar(45) DEFAULT NULL,
  `prodotto` int(11) NOT NULL,
  PRIMARY KEY (`utente`,`prodotto`),
  KEY `id_prodotto_idx` (`prodotto`),
  CONSTRAINT `id_prodotto_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_utente` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impiegato_amministratore`
--

DROP TABLE IF EXISTS `impiegato_amministratore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `impiegato_amministratore` (
  `impiegato_amministratore` int(11) NOT NULL,
  PRIMARY KEY (`impiegato_amministratore`),
  CONSTRAINT `id_personale_2` FOREIGN KEY (`impiegato_amministratore`) REFERENCES `personale` (`id_personale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impiegato_amministratore`
--

LOCK TABLES `impiegato_amministratore` WRITE;
/*!40000 ALTER TABLE `impiegato_amministratore` DISABLE KEYS */;
/*!40000 ALTER TABLE `impiegato_amministratore` ENABLE KEYS */;
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
  `data` datetime DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  UNIQUE KEY `id_ordine_UNIQUE` (`id_ordine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personale`
--

DROP TABLE IF EXISTS `personale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personale` (
  `id_personale` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `prodotto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_personale`),
  KEY `id_prodotto_2_idx` (`prodotto`),
  CONSTRAINT `id_prodotto_2` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personale`
--

LOCK TABLES `personale` WRITE;
/*!40000 ALTER TABLE `personale` DISABLE KEYS */;
/*!40000 ALTER TABLE `personale` ENABLE KEYS */;
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
  `tipo` varchar(45) DEFAULT NULL,
  `produttore` varchar(45) DEFAULT NULL,
  `modello` varchar(45) DEFAULT NULL,
  `genere` varchar(45) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `piattaforma` varchar(45) DEFAULT NULL,
  `titolo` varchar(45) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_prodotto`),
  UNIQUE KEY `id_prodotto_UNIQUE` (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
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
  `numero_tracking` int(11) DEFAULT '0',
  `stato` varchar(45) DEFAULT NULL,
  `ordine` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_spedizione`),
  UNIQUE KEY `numero_spedizione_UNIQUE` (`numero_spedizione`),
  KEY `id_ordine_idx` (`ordine`),
  CONSTRAINT `id_ordine_2` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spedizione`
--

LOCK TABLES `spedizione` WRITE;
/*!40000 ALTER TABLE `spedizione` DISABLE KEYS */;
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
  `numero_tessera` int(11) NOT NULL DEFAULT '0',
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `acquisti_effettuati` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `punti_socio` int(11) DEFAULT '0',
  `rinnovo_tessera` year(4) DEFAULT '2000',
  `indirizzo` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ordine` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_utente`,`numero_tessera`),
  UNIQUE KEY `id_utente_UNIQUE` (`id_utente`),
  UNIQUE KEY `numero_tessera_UNIQUE` (`numero_tessera`),
  KEY `id_ordine_idx` (`ordine`),
  CONSTRAINT `id_ordine_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
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

-- Dump completed on 2019-07-10 23:48:01
