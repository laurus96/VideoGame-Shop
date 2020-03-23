package it.unisa.shop.application;

public class Menu {
	
	public static void createMenu(int val) {
		
		if(val == 1) {
			System.out.println("******************** Benvenuto nel menu cliente ********************\n");
			
			System.out.println("[1]  "+ OP1);
			System.out.println("[2]  "+ OP2);
			System.out.println("[3]  "+ OP5);
			System.out.println("[4]  "+ OP7);
			System.out.println("[5]  "+ OP12);
			System.out.println("[6]  "+ OP13);

			
			System.out.println("\n******************** Scegliere quale operazione svolgere ********************");
		}
		
		if(val == 2) {
			System.out.println("******************** Benvenuto nel menu dell'amministratore ********************\n");
			
			System.out.println("[1]  "+ OP3);
			System.out.println("[2]  "+ OP4);
			System.out.println("[3]  "+ OP6);
			System.out.println("[4]  "+ OP8);
			System.out.println("[5]  "+ OP9);
			System.out.println("[6]  "+ OP10);
			System.out.println("[7]  "+ OP11);
			System.out.println("[8]  "+ OP14);
			System.out.println("[9]  "+ OP15);
			System.out.println("[10]  "+ OP16);

			
			System.out.println("\n******************** Scegliere quale operazione svolgere ********************");
		}
	}
	
	
	
	private static String OP1 = "Acquisto di un prodotto\n";
	private static String OP2 = "Cancellazione un ordine\n";
	private static String OP3 = "Inserimento di un nuovo prodotto (ADMIN ONLY)\n";
	private static String OP4 = "Report sulle vendite (ADMIN ONLY)\n";
	private static String OP5 = "Visualizza i 10 prodotti più acquistati\n";
	private static String OP6 = "Visualizza degli ordini effettuati da un utente (ADMIN ONLY)\n";
	private static String OP7 = "Visualizza un ordine\n";
	private static String OP8 = "Registrazione nuovo utente (ADMIN ONLY)\n";
	private static String OP9 = "Promozione di un lavoratore (ADMIN ONLY)\n";
	private static String OP10 = "Registrazione di un nuovo dipendente (ADMIN ONLY)\n";
	private static String OP11 = "Rinnovo della tessera socio (ADMIN ONLY)\n";
	private static String OP12 = "Visualizza i punti accomulati sulla tua card\n";
	private static String OP13 = "Visualizza lo stato di una spedizione\n";
	private static String OP14 = "Visualizza il fatturato di questo mese (ADMIN ONLY)\n";
	private static String OP15 = "Visualizza i migliori acquirenti (ADMIN ONLY)\n";
	private static String OP16 = "Assegnazione sconti (ADMIN ONLY)\n";


	

}
