/*
    OPERAZIONE 1: Acquisto di un prodotto con accredito di punti sulla tessera socio
*/
    /*
        Creazione di un ordine vuoto, con id_utente e data odierna
    */

    INSERT INTO ordine (data, utente) VALUES (timestamp( ? ), ?)

    /*
       Ricerca di un prodotto, da eseguire più volte se si acquistano diversi prodotti
    */

    SELECT id_prodotto FROM prodotto WHERE titolo  = ?

    /*
        Prodotti acquistati, da eseguire più volte se si acquistano diversi prodotti
    */
    INSERT INTO composizione (ordine, quantità, prodotto) VALUES ((SELECT max(id_ordine) FROM ordine ), ? , ? )

    /*
        Query per il calcolo del prezzo_totale di un ordine (QUERY di inserimento prodotto)
        Eseguire dopo aver creato l'ordine con i prodotti dfefiniti nella tabella composizione
    */
    UPDATE ordine as t SET prezzo_totale = (
        SELECT sum(singolo) as prezzo_totale  FROM 
            (SELECT  id_ordine, quantità, (prezzo * quantità) as singolo 
            FROM (prodotto as t1 INNER JOIN composizione as t2 on t1.id_prodotto = t2.prodotto) INNER JOIN ordine as t3 on t2.ordine = t3.id_ordine 
            GROUP BY id_prodotto, id_ordine) AS first
        WHERE id_ordine = (SELECT max(ordine) FROM composizione)
        GROUP BY id_ordine)
    WHERE id_ordine = (SELECT max(ordine) FROM composizione)

    /*
        Aggiornamento degli acquisti effettuati
    */
    UPDATE utente SET acquisti_effettuati = acquisti_effettuati + 1 WHERE id_utente = ?

    /*
        Inserimento nella tabella spedizione con stato = in preparazione
    */
    INSERT INTO spedizione (stato, ordine) VALUES("in preparazione", (SELECT max(id_ordine) FROM ordine))

/*
    Fine creazione ordine
*/

/*
    OPERAZIONE 2: QUERY per la cancellazione dell'ordine
*/

    /*
        Cancellazione di un ordine (se possibile)
    */
    DELETE t1 FROM ordine as t1 INNER JOIN spedizione as t2 on t1.id_ordine = t2.ordine WHERE stato <> "In consegna" AND stato <> "In spedizione" AND id_ordine = ?;

    /*
        Aggiornamento degli acquisti effettuati
    */
    UPDATE utente SET acquisti_effettuati = acquisti_effettuati - 1 WHERE id_utente = ?;

/*
    FINE
*/

/*  
    OPERAZIONE 3: Inserimento di un nuovo prodotto
*/
INSERT INTO `shop`.`prodotto` (`prezzo`, `volume`, `produttore`, `genere`, `publisher`, `piattaforma`, `titolo`, `dipendente`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);

/*  
    OPERAZIONE 4:  Stampa giornaliera del numero di articoli venduti 
*/
SELECT sum(acquisti_effettuati) as Prodotti_Venduti FROM shop.utente;

/*  
    OPERAZIONE 5:  Visualizzazione dei 10 prodotti più acquistati
*/
SELECT quantità, titolo FROM ( composizione as t1 INNER JOIN prodotto as t2 on t1.prodotto = t2.id_prodotto ) ORDER BY quantità desc LIMIT 10

/*  
    OPERAZIONE 6:  Visualizzazione degli ordini effettuati da un utente
*/
SELECT id_ordine, titolo
FROM (shop.ordine as t1 INNER JOIN Utente as t2 on t1.utente = t2.id_utente) 
INNER JOIN ( composizione as t3 INNER JOIN prodotto as t4 on t3.prodotto = t4.id_prodotto) on t3.ordine = t1.id_ordine
WHERE username = ?;

/*  
    OPERAZIONE 7:  Visualizzazione di un ordine in base al numero
*/
SELECT id_ordine, titolo 
FROM (shop.ordine as t1 INNER JOIN Utente as t2 on t1.utente = t2.id_utente)  
INNER JOIN ( composizione as t3 INNER JOIN prodotto as t4 on t3.prodotto = t4.id_prodotto) on t3.ordine = t1.id_ordine 
WHERE id_ordine = ?

/*  
    OPERAZIONE 8:  Creazione di un nuovo utente e assegnazione di una carta socio
*/
INSERT INTO `shop`.`utente` (`numero_tessera`, `nome`, `cognome`, `username`, `password`,  `rinnovo_tessera`, `indirizzo`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)

/*  
    OPERAZIONE 9:  Modifica del ruolo di un lavoratore
*/
UPDATE dipendente SET ruolo= ? , isAdmin= ? WHERE username = ?

/*  
    OPERAZIONE 10:  Assunzione di un nuovo lavoratore
*/
INSERT INTO `shop`.`dipendente` (`nome`, `cognome`, `username`, `password`, `ruolo`, `isAdmin`) VALUES (?, ?, ?, ?, ?, ?)

/*  
    OPERAZIONE 11:  Rinnovo di una tessera socio
*/
UPDATE utente as t1 INNER JOIN (SELECT rinnovo_tessera FROM utente WHERE username = ?) as t2 SET t1.rinnovo_tessera = ? WHERE username = ?

/*  
    OPERAZIONE 12:  Visualizzazione dei punti socio
*/
SELECT punti_socio FROM utente WHERE username= ?

/*  
    OPERAZIONE 13:  Visualizzazione dello stato di una spedizione
*/
SELECT stato FROM (spedizione as t1 INNER JOIN  ordine as t2 on t1.ordine = t2.id_ordine) INNER JOIN utente as t3 on utente = id_utente WHERE username= ? AND id_ordine= ?

/*  
    OPERAZIONE 14:  Visualizzazione del fatturato mensile
*/
SELECT SUM(prezzo_totale) as fatturatoMensile FROM ordine WHERE data < timestamp( ? ) AND data > timestamp( ? )

/*  
    OPERAZIONE 15:  Visualizzazione dei tre migliori utenti con il maggior numero di acquisti
*/
SELECT username FROM utente ORDER BY punti_socio desc LIMIT 3

/*  
    OPERAZIONE 16:  Attribuzione di sconti basati sui punti socio
*/
SELECT username, prezzo_totale as prezzo FROM utente INNER JOIN ordine on id_utente = utente  WHERE data > timestamp( ? ) AND data < timestamp( ? ) ORDER BY prezzo desc LIMIT 3
