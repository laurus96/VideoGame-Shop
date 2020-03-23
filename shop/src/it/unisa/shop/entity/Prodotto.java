package it.unisa.shop.entity;

public class Prodotto {

	/**
	 * 
	 */
	public Prodotto() {
		super();
	}

	/**
	 * @param prezzo
	 * @param volume
	 * @param produttore
	 * @param genere
	 * @param publisher
	 * @param piattaforma
	 * @param titolo
	 * @param id_admin
	 */
	public Prodotto(int prezzo, int volume, String produttore, String genere, String publisher, String piattaforma,
			String titolo, int id_admin) {
		super();
		this.prezzo = prezzo;
		this.volume = volume;
		this.produttore = produttore;
		this.genere = genere;
		this.publisher = publisher;
		this.piattaforma = piattaforma;
		this.titolo = titolo;
		this.id_admin = id_admin;
	}

	/**
	 * @return the prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}

	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * @return the produttore
	 */
	public String getProduttore() {
		return produttore;
	}

	/**
	 * @param produttore the produttore to set
	 */
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}

	/**
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * @param genere the genere to set
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the piattaforma
	 */
	public String getPiattaforma() {
		return piattaforma;
	}

	/**
	 * @param piattaforma the piattaforma to set
	 */
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return the id_admin
	 */
	public int getId_admin() {
		return id_admin;
	}

	/**
	 * @param id_admin the id_admin to set
	 */
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}

	private int prezzo;
	private int volume;
	private String produttore;
	private String genere;
	private String publisher;
	private String piattaforma;
	private String titolo;
	
	private int id_admin;

}
