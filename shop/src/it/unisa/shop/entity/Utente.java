package it.unisa.shop.entity;

import java.sql.Timestamp;
import java.util.Calendar;

public class Utente {
	
	
	/**
	 * 
	 */
	public Utente() {
		super();
	}

	//Costruttore riservato ai clienti dello shop
	public Utente(int id_utente, String nome, String username, String cognome, int punti_socio, Timestamp rinnovo_tessera) {
		super();
		this.id_utente = id_utente;
		this.nome = nome;
		this.username = username;
		this.cognome = cognome;
		this.punti_socio = punti_socio;
		this.rinnovo_tessera = Calendar.getInstance();
		this.rinnovo_tessera.setTimeInMillis(rinnovo_tessera.getTime());
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
	public int getPunti_socio() {
		return punti_socio;
	}
	public void setPunti_socio(int punti_socio) {
		this.punti_socio = punti_socio;
	}
	public int getRinnovo_tessera() {
		return rinnovo_tessera.get(Calendar.YEAR);
	}
	public void setRinnovo_tessera(Calendar rinnovo_tessera) {
		this.rinnovo_tessera = rinnovo_tessera;
	}
	
	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	
	/**
	 * @return the numero_tessera
	 */
	public int getNumero_tessera() {
		return numero_tessera;
	}

	/**
	 * @param numero_tessera the numero_tessera to set
	 */
	public void setNumero_tessera(int numero_tessera) {
		this.numero_tessera = numero_tessera;
	}

	/**
	 * @return the acquisti_effettuati
	 */
	public int getAcquisti_effettuati() {
		return acquisti_effettuati;
	}

	/**
	 * @param acquisti_effettuati the acquisti_effettuati to set
	 */
	public void setAcquisti_effettuati(int acquisti_effettuati) {
		this.acquisti_effettuati = acquisti_effettuati;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String nome;
	private String cognome;
	private int numero_tessera;
	private int acquisti_effettuati;
	private String username;
	private String password;
	private String indirizzo;
	private String email;
	
	
	private int punti_socio;
	private Calendar rinnovo_tessera;
	
	private int id_utente;


}
