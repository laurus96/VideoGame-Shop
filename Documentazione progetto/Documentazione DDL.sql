CREATE SCHEMA `shop` ;

CREATE TABLE `composizione` (
  `ordine` int(11) NOT NULL,
  `quantità` varchar(45) DEFAULT NULL,
  `prodotto` int(11) NOT NULL,
  KEY `id_prodotto_idx` (`prodotto`),
  KEY `id_ordine_1_idx` (`ordine`),
  CONSTRAINT `id_ordine_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_prodotto_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
)

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
)

CREATE TABLE `ordine` (
  `id_ordine` int(11) NOT NULL AUTO_INCREMENT,
  `prezzo_totale` int(11) DEFAULT NULL,
  `data` timestamp NULL DEFAULT NULL,
  `utente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  UNIQUE KEY `id_ordine_UNIQUE` (`id_ordine`),
  KEY `id_utente_idx` (`utente`),
  CONSTRAINT `id_utente` FOREIGN KEY (`utente`) REFERENCES `utente` (`id_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) 

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
) 

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
)

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
)