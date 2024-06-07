package start;

import model.Utente;
import utility.StringUtility;

public class Main {

	public static void main(String[] args) { 
		
		Utente [] utentiDaJSON = StringUtility.fromJSON();
		
		for (Utente utente : utentiDaJSON) {
			if (utente.getNome().equals("Gianluigi") && utente.getCognome().equals("Sperti")) {
				utente.setEta(26);
				StringUtility.toXML(utente);
				StringUtility.toJSON(utente);
			}
		}
		
		Utente utenteDaXML = StringUtility.fromXML();
		System.out.println("\nUtente preso da file XML: " + utenteDaXML.toString());
	}
}
