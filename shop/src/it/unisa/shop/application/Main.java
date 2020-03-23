package it.unisa.shop.application;

import it.unisa.shop.entity.Dipendente;
import it.unisa.shop.entity.Utente;

public class Main {

	public static void main(String[] args) {
		Input in = new Input();

		
		String username = in.username();
		String password = in.password();
		
		int success = 0;
		
		Dipendente admin = null;
		Utente utente = null;
		
		while(success == 0) {
			admin = Login_Application.Login(username, password);
			if(admin != null) {
				if(admin.isAdmin()) {
					success = 1;
				}else {
					System.out.println("Se un " + admin.getRuolo());
					success = 1;
				}
					
			}else {
				utente = Login_Application.Login1(username, password);
				if(utente != null) {
					success = 1;
				}else {
					System.out.println("Riprovare...");
					System.out.println("Inserire Username: ");
					username = in.username();
					
					System.out.println("Inserire Password: ");
					
					password = in.password();
					
				}
			}
		}
		do {
			if(admin == null || admin.isAdmin() == false) {
				Menu.createMenu(1);
				Database_Operation.SelectorUtente(2, Integer.parseInt(in.operationSelection()), utente);

			}
			else if(admin != null || admin.isAdmin()==true) {
				Menu.createMenu(2);
				Database_Operation.SelectorAmministratore(1, Integer.parseInt(in.operationSelection()), admin);

			}
			System.out.println("\nVuoi tornare al menù principale? in caso di risposta negativa l'applicazione si chiuderà [Si/No] [?]\b\b");

		}while(in.continueExecute() == 0);
		
		System.out.println("\nUscita...");
		in.close();
	}

}
