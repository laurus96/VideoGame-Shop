package it.unisa.shop.entity;

public class Dipendente {
	
	/**
	 * 
	 */
	public Dipendente() {
		super();
	}

	//Costruttore riservato agli amministratori dello shop
	public Dipendente(int id_dipendente, String nome, String cognome, boolean isAdmin, String ruolo) {
		super();
		this.id_dipendente = id_dipendente;
		this.nome = nome;
		this.cognome = cognome;
		this.isAdmin = isAdmin;
		this.ruolo = ruolo;
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getRuolo() {
		return ruolo;
	}


	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}	
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	
	
	public int getId_dipendente() {
		return id_dipendente;
	}

	public void setId_dipendente(int id_dipendente) {
		this.id_dipendente = id_dipendente;
	}
	
	
	private String nome;
	private String cognome;
	private String ruolo;
	private boolean	isAdmin;
	private int id_dipendente;
	

}
