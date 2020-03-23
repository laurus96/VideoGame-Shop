package it.unisa.shop.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import it.unisa.shop.MySql.MySql;
import it.unisa.shop.entity.Dipendente;
import it.unisa.shop.entity.Prodotto;
import it.unisa.shop.entity.Utente;

public class Database_Query {
	
	/*
	 * QUERY per la creazione dell'ordine
	 */
	private static String CREATE_EMPTY_ORDER = "INSERT INTO ordine (data, utente) VALUES (timestamp( ? ), ?)";
	private static String SEARCH_PRODUCT = "SELECT id_prodotto FROM prodotto WHERE titolo  = ?";
	private static String ADD_PRODUCTS = "INSERT INTO composizione (ordine, quantità, prodotto) VALUES ((SELECT max(id_ordine) FROM ordine ), ? , ?)";
	private static String UPDATE_FINAL_PRICE = "UPDATE ordine as t SET prezzo_totale = (\r\n" + 
			"        SELECT sum(singolo) as prezzo_totale  FROM \r\n" + 
			"            (SELECT  id_ordine, quantità, (prezzo * quantità) as singolo \r\n" + 
			"            FROM (prodotto as t1 INNER JOIN composizione as t2 on t1.id_prodotto = t2.prodotto) INNER JOIN ordine as t3 on t2.ordine = t3.id_ordine \r\n" + 
			"            GROUP BY id_prodotto, id_ordine) AS first\r\n" + 
			"        WHERE id_ordine = (SELECT max(ordine) FROM composizione)\r\n" + 
			"        GROUP BY id_ordine)\r\n" + 
			"    WHERE id_ordine = (SELECT max(ordine) FROM composizione)"; 
	private static String UPDATE_NUMBER_PURCHASE = "UPDATE utente SET acquisti_effettuati = acquisti_effettuati + 1 WHERE id_utente = ?";
	private static String ADD_SHIPMENT = "INSERT INTO spedizione (stato, ordine) VALUES(\"in preparazione\", (SELECT max(id_ordine) FROM ordine))";
	/*
	 * FINE
	 */
	
	
	/*
	 * QUERY per la cancellazione dell'ordine
	 */
	private static String DELETE_ORDER = "DELETE t1 FROM ordine as t1 INNER JOIN spedizione as t2 on t1.id_ordine = t2.ordine WHERE stato <> \"In consegna\" AND stato <> \"In spedizione\" AND id_ordine = ?";
	private static String DECRESE_NUMBER_PURCHASE = "UPDATE utente SET acquisti_effettuati = acquisti_effettuati - 1 WHERE id_utente = ?";
	/*
	 * FINE
	 */
	
	/** La query per l'inserimento da parte di un admin di un prodotto nel catalogo */
	private static String NEW_PRODUCT = "INSERT INTO `shop`.`prodotto` (`prezzo`, `volume`, `produttore`, `genere`, `publisher`, `piattaforma`, `titolo`, `admin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	/** La query per il report sulle vendite */
	private static String PRODUCTS_SELL = "SELECT sum(acquisti_effettuati) as Prodotti_Venduti FROM shop.utente;";
	
	/** La query per la visualizzazione dei 10 prodotti più acquistati */
	private static String TEN_PRODUCT = "SELECT titolo FROM ( composizione as t1 INNER JOIN prodotto as t2 on t1.prodotto = t2.id_prodotto ) ORDER BY quantità desc LIMIT 10";
	
	/** La query per la visualizzazione di tutti gli ordini di un utente */
	private static String VIEW_ORDERS = "SELECT id_ordine, titolo FROM (shop.ordine as t1 INNER JOIN Utente as t2 on t1.utente = t2.id_utente)  INNER JOIN ( composizione as t3 INNER JOIN prodotto as t4 on t3.prodotto = t4.id_prodotto) on t3.ordine = t1.id_ordine WHERE username = ?";
	
	/** La query per la visualizzazione di un ordine da parte dell'utente */
	private static String VIEW_ORDER = "SELECT id_ordine, titolo FROM (shop.ordine as t1 INNER JOIN Utente as t2 on t1.utente = t2.id_utente)  INNER JOIN ( composizione as t3 INNER JOIN prodotto as t4 on t3.prodotto = t4.id_prodotto) on t3.ordine = t1.id_ordine WHERE id_ordine = ?";
	
	/** La query per la creazione di un nuovo utente */
	private static String NEW_USER = "INSERT INTO `shop`.`utente` (`numero_tessera`, `nome`, `cognome`, `username`, `password`,  `rinnovo_tessera`, `indirizzo`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	/** La query per la promozione di un impiegato */
	private static String ROLE_CHANGE = "UPDATE dipendente SET ruolo= ? , isAdmin= ? WHERE username = ?";

	/** La query per l'assunzione di un impiegato */
	private static String NEW_EMPLOYEE = "INSERT INTO `shop`.`dipendente` (`nome`, `cognome`, `username`, `password`, `ruolo`, `isAdmin`) VALUES (?, ?, ?, ?, ?, ?)";
	
	/** La query per l'aggiornamento della data di scadenza di una tessera */
	private static String RENEW_CARD = "UPDATE utente as t1 INNER JOIN (SELECT rinnovo_tessera FROM utente WHERE username = ?) as t2 SET t1.rinnovo_tessera = ? WHERE username = ?";
	
	/** La query per la visualizzazione dei punti socio di una tessera */
	private static String VIEW_POINT = "SELECT punti_socio FROM utente WHERE username= ?";
	
	/** La query per visualizzare lo stato di un ordine */
	private static String VIEW_STATE = "SELECT stato FROM (spedizione as t1 INNER JOIN  ordine as t2 on t1.ordine = t2.id_ordine) INNER JOIN utente as t3 on utente = id_utente WHERE username= ? AND id_ordine= ?";
	
	/** La query per visualizzare il fatturato mensile */
	private static String MONTHLY_TURNOVER = "SELECT SUM(prezzo_totale) as fatturatoMensile FROM ordine WHERE data > timestamp( ? ) AND data < timestamp( ? )";
	
	/** La query per visualizzare i tre migliori utenti con il maggior numero di punti socio */
	private static String BEST_THREE = "SELECT username FROM utente ORDER BY punti_socio desc LIMIT 3";
	
	/** La query per l'assegnazione dei premi ai migliori 3 acquirenti del mese */
	private static String ATTRIBUTION_DISCOUNTS = "SELECT username, prezzo_totale as prezzo FROM utente INNER JOIN ordine on id_utente = utente  WHERE data > timestamp( ? ) AND data < timestamp( ? ) ORDER BY prezzo desc LIMIT 3";
	
	
	public int creazioneProdotto(Prodotto prodotto, Dipendente admin) {
		Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            con = MySql.createConnection();
            statement = con.prepareStatement(NEW_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, prodotto.getPrezzo());
            statement.setInt(2, prodotto.getVolume());
            statement.setString(3, prodotto.getProduttore());
            statement.setString(4, prodotto.getGenere());
            statement.setString(5, prodotto.getPublisher());
            statement.setString(6, prodotto.getPiattaforma());
            statement.setString(7, prodotto.getTitolo());
            statement.setInt(8, admin.getId_dipendente());
           
            statement.execute();
            result = statement.getGeneratedKeys();
 
            if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
 
        return -1;
	}
	
	public String ProdottiVenduti() {
		String s = "";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(PRODUCTS_SELL);
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				s = result.getString(1);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		return s;
	}
	
	public int creazioneUtente(Utente user) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
            con = MySql.createConnection();
            statement = con.prepareStatement(NEW_USER, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, user.getNumero_tessera());
            statement.setString(2, user.getNome());
            statement.setString(3, user.getCognome());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            
            long nextYear = LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.UTC).toEpochMilli();
            
            statement.setTimestamp(6, new Timestamp(nextYear));
            
            statement.setString(7, user.getIndirizzo());
            statement.setString(8, user.getEmail());
           
            statement.execute();
            result = statement.getGeneratedKeys();
 
            if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
            }
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return -1;
		
	}
	
	public boolean aggiornamentoRuolo() {
		Input in = new Input();
		String username = in.username();
		String ruolo = in.nuovoRuolo();
		
		Connection con = null;
		PreparedStatement statement = null;
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(ROLE_CHANGE);
			
			statement.setString(1, ruolo);
			if(ruolo.compareTo("Amministratore") == 0) {
				statement.setBoolean(2, true);
			}else {
				statement.setBoolean(2, false);
			}
			
			statement.setString(3, username);
			
			statement.execute();
			
			return true;
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		return false;
		
	}

	public int creazioneDipendente(Dipendente employee) {
		Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Input in = new Input();
        
        try {
            con = MySql.createConnection();
            statement = con.prepareStatement(NEW_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, employee.getNome());
            statement.setString(2, employee.getCognome());
            statement.setString(3, in.username());
            statement.setString(4, in.password());
            statement.setString(5, employee.getRuolo());
            
            if(employee.getRuolo().compareTo("Amministratore") == 0) {
				statement.setBoolean(6, true);
			}else {
				statement.setBoolean(6, false);
			}
                
            statement.execute();
            result = statement.getGeneratedKeys();
 
            if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return -1;
	}
	
	public boolean aggiornamentoScadenza() {
		Input in = new Input();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Timestamp timestamp = null;
		String username = in.username();
		
		try {
			con = MySql.createConnection();
			
			statement = con.prepareStatement("SELECT rinnovo_tessera FROM utente WHERE username = ?");
			statement.setString(1, username);
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				timestamp = result.getTimestamp(1);
			}
			
			LocalDateTime scadenza = timestamp.toLocalDateTime();
			long next = scadenza.plusYears(1).toInstant(ZoneOffset.UTC).toEpochMilli();
			statement.close();
            
			
			statement = con.prepareStatement(RENEW_CARD);
			statement.setString(1, username);
            statement.setTimestamp(2, new Timestamp(next));
            statement.setString(3, username);
            
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return false;
	}

	public int visualizzazionePunti(Utente user) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(VIEW_POINT);
			statement.setString(1, user.getUsername());
			System.out.println(user.getUsername());
			statement.execute();
			result = statement.getResultSet();
			
			while(result.next()) {
				return result.getInt(1);
				
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		return 0;
	}
	
	public String statoSpedizione(Utente user) {
		Input in = new Input();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(VIEW_STATE);
			statement.setString(1, user.getUsername());
			statement.setInt(2, in.numeroOrdine());
			
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				return result.getString(1);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return "Spedizione non trovata";
	}

	public ArrayList<String> miglioriClienti(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		ArrayList<String> bestUser = new ArrayList<String>();
		
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(BEST_THREE);
			
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				bestUser.add(result.getString(1));
			}
			
			return bestUser;
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		return null;
	}

	public ArrayList<String> prodottiPopolari(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		ArrayList<String> p = new ArrayList<String>();
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(TEN_PRODUCT);
			
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				p.add(result.getString(1));
			}
			
			return p;
			
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		return null;
	}

	public ArrayList<String> visualizzazioneOrdini(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<String> ordine = new ArrayList<String>();
		Input in = new Input();
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(VIEW_ORDERS);
			
			statement.setString(1, in.username());
			
			statement.execute();
			result = statement.getResultSet();
			
			while(result.next()) {
				
				ordine.add("Numero ordine: " + result.getString(1));
				
				ordine.add(result.getString(2)+"\n");
			}
			
			return ordine;
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return null;
	}
	
	public ArrayList<String> visualizzazioneOrdine(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<String> ordine = new ArrayList<String>();
		Input in = new Input();
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(VIEW_ORDER);
			
			statement.setInt(1, in.numeroOrdine());
			
			statement.execute();
			result = statement.getResultSet();
			
			while(result.next()) {
				
				ordine.add("Numero ordine: " + result.getString(1));
				
				ordine.add(result.getString(2)+"\n");
			}
			
			return ordine;
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return null;
	}
	
	public int fatturato() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		LocalDateTime now = LocalDateTime.now();
		
		LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonthValue(), 1, 00, 00, 00);
		LocalDateTime finish = LocalDateTime.of(now.getYear(), now.getMonthValue(), 30, 00, 00, 00);

		long timestamp1 = start.toInstant(ZoneOffset.UTC).toEpochMilli();
		long timestamp2 = finish.toInstant(ZoneOffset.UTC).toEpochMilli();
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(MONTHLY_TURNOVER);
			statement.setTimestamp(1, new Timestamp(timestamp1));
			statement.setTimestamp(2, new Timestamp(timestamp2));
			
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				return result.getInt(1);
			}

			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return 0;

	}

	public ArrayList<String> premiazioneUtenti(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		ArrayList<String> p = new ArrayList<String>();
		
		LocalDateTime now = LocalDateTime.now();
		
		LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonthValue(), 1, 00, 00, 00);
		LocalDateTime finish = LocalDateTime.of(now.getYear(), now.getMonthValue(), 30, 00, 00, 00);

		long timestamp1 = start.toInstant(ZoneOffset.UTC).toEpochMilli();
		long timestamp2 = finish.toInstant(ZoneOffset.UTC).toEpochMilli();
		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(ATTRIBUTION_DISCOUNTS);
			
			statement.setTimestamp(1, new Timestamp(timestamp1));
			statement.setTimestamp(2, new Timestamp(timestamp2));
			
			statement.execute();
			
			result = statement.getResultSet();
			
			while(result.next()) {
				p.add(result.getString(1)+" con una spesa di: " + result.getInt(2));
				
			}
			
			return p;		
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		return null;
	}

	public int creazioneOrdine(Utente user) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		Input in = new Input();
		ArrayList<Integer> id = new ArrayList<Integer>();
		int buy = 0;
		int id_ordine = 0;
		
		try {
			con = MySql.createConnection();
			
			/*
			 * Creazione ordine vuoto
			 */
			statement = con.prepareStatement(CREATE_EMPTY_ORDER, Statement.RETURN_GENERATED_KEYS);
			
			long nextYear = LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.UTC).toEpochMilli();
			statement.setTimestamp(1, new Timestamp(nextYear)); 
			statement.setInt(2, user.getId_utente());
			statement.execute();
			
			result = statement.getGeneratedKeys();
			
            if (result.next() && result != null) {
            	id_ordine = result.getInt(1);
            } else {
            	 System.out.println("Errore...");
            }
            
            statement.close();
            result.close();
            /*
			 * ------------------------------------------------------------------------------------------------
			 */
            
            /*
			 * Ricerca dei prodotti da aggiungere all'ordine
			 */
        
            while(buy == 0) {
            	statement = con.prepareStatement(SEARCH_PRODUCT);
            	statement.setString(1, in.inputProdotto());
            	
            	statement.execute();
            	result = statement.getResultSet();
            	
            	if(result.next()) {
            		id.add(result.getInt(1));
            		System.out.println("Cercare un altro prodotto? [Si/No]");
            		buy = in.continueExecute();
            	}else {
            		System.out.println("Prodotto non in catalogo");
            		System.out.println("Cercare un altro prodotto? [Si/No]");
            		buy = in.continueExecute();
            	}
            	
            	statement.close();
                result.close();
            }
            
            
            /*
			 * ------------------------------------------------------------------------------------------------
			 */
            
            if(id.size() == 0) {
            	statement = con.prepareStatement("DELETE FROM ordine WHERE id_ordine = ?");
            	statement.setInt(1, id_ordine);
            	statement.execute();
            	return 0;
            }
            	
            
            /*
			 * Aggiunta dei prodotti
			 */
            for(int e : id) {
            	statement = con.prepareStatement(ADD_PRODUCTS);
            	
            	statement.setInt(1, in.volume());
            	statement.setInt(2, e);
            	
            	statement.execute();
            	
            	statement.close();
                result.close();
            }
            
            /*
			 * ------------------------------------------------------------------------------------------------
			 */
            
            /*
			 * calcolo del prezzo finale
			 */
            statement = con.prepareStatement(UPDATE_FINAL_PRICE);
            statement.execute();
            
            statement.close();
            
            /*
			 * ------------------------------------------------------------------------------------------------
			 */
            
            /*
			 * aggiornamento numero acquisti effettuati
			 */
           
            statement = con.prepareStatement(UPDATE_NUMBER_PURCHASE);
            statement.setInt(1, user.getId_utente());
            
            statement.execute();
            statement.close();
            
            statement = con.prepareStatement(ADD_SHIPMENT);
            statement.execute();
            
            
			
            return 1;
            
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		
		return 0;
	}
	
	public int cancellazioneOrdine(Utente user) {
		Connection con = null;
		PreparedStatement statement = null;
		Input in = new Input();

		
		try {
			con = MySql.createConnection();
			statement = con.prepareStatement(DELETE_ORDER);
			statement.setInt(1, in.numeroOrdine());
			
			statement.execute();
			
			statement.close();
			
			statement = con.prepareStatement(DECRESE_NUMBER_PURCHASE);
			statement.setInt(1, user.getId_utente());
			
			statement.execute();
			
			return 1;
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
		
		
		return 0;
	}

}

