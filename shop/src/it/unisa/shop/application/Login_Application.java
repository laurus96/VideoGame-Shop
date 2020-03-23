package it.unisa.shop.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.shop.MySql.MySql;
import it.unisa.shop.entity.Dipendente;
import it.unisa.shop.entity.Utente;

public class Login_Application {
	
	private static String READ_USER = "SELECT id_utente, nome, username, cognome, punti_socio, rinnovo_tessera FROM utente WHERE username= ? AND password= ?";
	private static String READ_ADMIN = "SELECT id_dipendente, nome, cognome, isAdmin, ruolo FROM dipendente WHERE username= ? AND password= ?";
	
	public static Dipendente Login(String username, String password) {
		Dipendente admin = null;
		Connection con = null;
		PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
        	con = MySql.createConnection();
        	statement = con.prepareStatement(READ_ADMIN);
        	statement.setString(1, username);
        	statement.setString(2, password);
        	statement.execute();
        	result = statement.getResultSet();
        	
        	while(result.next()) {
        		admin = new Dipendente(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4), result.getString(5));
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
        
        return admin;
	}
	
	public static Utente Login1(String username, String password) {
		Utente user = null;
		Connection con = null;
		PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
        	con = MySql.createConnection();
        	statement = con.prepareStatement(READ_USER);
        	statement.setString(1, username);
        	statement.setString(2, password);
        	statement.execute();
        	result = statement.getResultSet();
        	
        	while(result.next()) {
        		user = new Utente(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getTimestamp(6));
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
        
        return user;
	}

	
}
