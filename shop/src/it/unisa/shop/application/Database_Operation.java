package it.unisa.shop.application;

import java.util.ArrayList;

import it.unisa.shop.entity.Dipendente;
import it.unisa.shop.entity.Prodotto;
import it.unisa.shop.entity.Utente;

public class Database_Operation {
	
	public static void SelectorAmministratore(int menu, int val, Dipendente admin) {
		
		Database_Query query = new Database_Query();
		Input in = new Input();
		
		switch(val) {
		
		case 1:
			System.out.println("\n\nInserimento di un nuovo prodotto\n");
			Prodotto p = in.Data_product();
			System.out.println("Prodotto inserito con chiave: " + query.creazioneProdotto(p, admin));
			
			break;
		case 2:
			System.out.println("\n\nReport sulle vendite\n");
			String s = query.ProdottiVenduti();
			System.out.println("Abbiamo venduto: " + s + " prodotti");
			break;
		case 3:
			System.out.println("\n\nVisualizza degli ordini effettuati da un utente\n");
			ArrayList<String> ordine = new ArrayList<String>();
			ordine = query.visualizzazioneOrdini();
			
			System.out.println("Ecco l'ordine: \n");

			for(String e : ordine) {
				System.out.println(e);
			}
			break;
		case 4:
			System.out.println("\n\nRegistrazione nuovo utente\n");
			Utente u = in.Data_utente();
			System.out.println("utente inserito con chiave: " + query.creazioneUtente(u));
			break;
		case 5:
			System.out.println("\n\nPromozione di un lavoratore");
			if(query.aggiornamentoRuolo() == true) {
				System.out.println("Aggiornamento completato con successo\n");
			}else{
				System.out.println("Aggiornamento fallito...\n");
			}
			break;
		case 6:
			System.out.println("\n\nRegistrazione di un nuovo dipendente\n");
			Dipendente d = in.Data_Dipendente();
			System.out.println("dipendente inserito con chiave: " + query.creazioneDipendente(d));
			break;
		case 7:
			System.out.println("\n\nRinnovo della tessera socio\n");
			if(query.aggiornamentoScadenza() == true) {
				System.out.println("Aggiornamento completato con successo\n");
			}else{
				System.out.println("Aggiornamento fallito...\n");
			}
			break;
		case 8:
			System.out.println("\n\nVisualizza il fatturato di questo mese\n");
			System.out.println("Fatturato di questo mese: " + query.fatturato());
			break;
		case 9:
			System.out.println("\n\nVisualizza i migliori acquirenti\n");
			
			ArrayList<String> bestUser = new ArrayList<String>();
			bestUser = query.miglioriClienti();
			
			System.out.println("I tre migliori acquirenti sono: \n");

			for(String e : bestUser) {
				System.out.println(e);
			}
			break;
		case 10:
			System.out.println("\n\nAssegnazione sconti");
			ArrayList<String> sconti = new ArrayList<String>();
			sconti = query.premiazioneUtenti();
			
			System.out.println("I tre migliori acquirenti del mese sono: \n");

			for(String e : sconti) {
				System.out.println(e);
			}
			break;
		default:
			System.out.println("Operazione non riconosciuta");
			break;
		}

		
	}


	public static void SelectorUtente(int menu, int val, Utente user) {
		
		Database_Query query = new Database_Query();		
		switch(val) {
		
		case 1:
			System.out.println("\n\nAcquisto di un prodotto\n");
			if(query.creazioneOrdine(user) == 1) {
				System.out.println("Ordine creato con successo. Grazie per averci scelto");
			}else {
				System.out.println("Errore");
			}
			break;
		case 2:
			System.out.println("\n\nCancella un ordine\n");
			if(query.cancellazioneOrdine(user) == 1) {
				System.out.println("Ordine canellato con successo.");
			}else {
				System.out.println("Errore");
			}
			break;
		case 3:
			System.out.println("\n\nVisualizza i 10 prodotti più acquistati\n");
			ArrayList<String> prodotti = new ArrayList<String>();
			prodotti = query.prodottiPopolari();
			
			System.out.println("\nI 10 prodotti più acquistati sono: \n");

			for(String e : prodotti) {
				System.out.println(e);
			}
			break;
		case 4:
			System.out.println("\n\nVisualizza un ordine\n");
			ArrayList<String> ordine = new ArrayList<String>();
			ordine = query.visualizzazioneOrdine();
			
			System.out.println("\nEcco l'ordine: \n");

			for(String e : ordine) {
				System.out.println(e);
			}
			break;
		case 5:
			System.out.println("\n\nVisualizza i punti accomulati sulla tua card\n");
			System.out.println(query.visualizzazionePunti(user));
			break;
		case 6:
			System.out.println("\n\nVisualizza lo stato di una spedizione\n");
			System.out.println("La spedizione è: " + query.statoSpedizione(user));
			break;
		default:
			System.out.println("Operazione non riconosciuta");
			break;
		
		}
	}

}
