package it.unisa.shop.application;

import java.util.Scanner;

import it.unisa.shop.entity.Dipendente;
import it.unisa.shop.entity.Prodotto;
import it.unisa.shop.entity.Utente;

public class Input {
	
	public Input() {
		super();
		this.myObj = new Scanner(System.in);
	}
	
	public void close() {
		this.myObj.close();
	}
	
	public Prodotto Data_product() {
		Prodotto p = new Prodotto();
		
		System.out.println("Inserire titolo: ");
		p.setTitolo(this.myObj.nextLine());
		
		System.out.println("Inserire produttore: ");
		p.setProduttore(this.myObj.nextLine());
		
		System.out.println("Inserire genere: ");
		p.setGenere(this.myObj.nextLine());
		
		System.out.println("Inserire piattaforma: ");
		p.setPiattaforma(this.myObj.nextLine());
		
		System.out.println("Inserire publisher: ");
		p.setPublisher(this.myObj.nextLine());
		
		System.out.println("Inserire numero copie in magazzino: ");
		p.setVolume(Integer.parseInt(this.myObj.nextLine()));
		
		System.out.println("Inserire prezzo: ");
		p.setPrezzo(Integer.parseInt(this.myObj.nextLine()));
		
		return p;
		
	}
	
	public Utente Data_utente() {
		Utente u = new Utente();
		
		System.out.println("Inserire Nome: ");
		u.setNome(this.myObj.nextLine());
		
		System.out.println("Inserire Cognome: ");
		u.setCognome(this.myObj.nextLine());
		
		System.out.println("Inserire Numero tessera: ");
		u.setNumero_tessera(Integer.parseInt(this.myObj.nextLine()));
		
		System.out.println("Inserire username: ");
		u.setUsername(this.myObj.nextLine());
		
		System.out.println("Inserire password: ");
		u.setPassword(this.myObj.nextLine());
		
		System.out.println("Inserire indirizzo di spedizione: ");
		u.setIndirizzo(this.myObj.nextLine());
		
		System.out.println("Inserire email: ");
		u.setEmail(this.myObj.nextLine());
		
		return u;
		
	}

	public Dipendente Data_Dipendente() {
		Dipendente d = new Dipendente();
		
		System.out.println("Inserire Nome: ");
		d.setNome(this.myObj.nextLine());
		
		System.out.println("Inserire Cognome: ");
		d.setCognome(this.myObj.nextLine());
		
		d.setRuolo(nuovoRuolo());
		
		return d;
	}
	
	public String username() {
		System.out.println("Inserire Username: ");
		return this.myObj.nextLine();
	}
	
	public String password() {
		System.out.println("Inserire password: ");
		return this.myObj.nextLine();
	}
	
	public int continueExecute() {
		if(this.myObj.nextLine().compareToIgnoreCase("No") == 0)
			return 1;
		
		return 0;
		
	}
	
	public String operationSelection() {
		return this.myObj.nextLine();
	}
	
	public String nuovoRuolo() {
		System.out.println("Inserire nuovo ruolo: ");
		return this.myObj.nextLine();
	}
	
	public int numeroOrdine() {
		System.out.println("Inserire numero ordine: ");
		return Integer.parseInt(this.myObj.nextLine());
	}

	public String inputProdotto() {
		System.out.println("Inserire il prodotto che si desidera acquistare: ");
		return this.myObj.nextLine();
	}

	public int volume() {
		System.out.println("Scegliere la quantità: ");
		return Integer.parseInt(this.myObj.nextLine());
	}
	private Scanner myObj;
}
