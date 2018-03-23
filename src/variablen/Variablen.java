package variablen;

public class Variablen {

	//
	//				Dateinamen
	//

	private final String cUrlDateiName = "url.txt";
	private final String cHinzugefügterNameDatei = "name.txt";
	private final String cUserVerzeichnis = "User";
	private final String cKommentarDateiName = "Kommentar.txt";

	//
	//				Pfade
	//

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";
	private final String cUrl = cPfad + "\\" + cUrlDateiName ;
	private final String cUser = cPfad + "\\" + cUserVerzeichnis;
	private final String cHinzugefügterName = cUser + "\\KURZURL\\" + cHinzugefügterNameDatei;
	private final String cKommentar = cPfad + "\\" + cKommentarDateiName;
	
	//
	//				DB
	//
	
	private final String cDBName = "benutzer";
	private final String cTabelle = "benutzer";
	
	//
	//				Getters für Dateinamen
	//

	public String getcUrlDateiName() {
		return cUrlDateiName;
	}

	public String getcHinzugefügterNameDatei() {
		return cHinzugefügterNameDatei;
	}

	public String getcUserVerzeichnis(){
		return cUserVerzeichnis;
	}
	
	public String getcKommentarDateiName() {
		return cKommentarDateiName;
	}

	//
	//				Getters für Pfade
	//

	public String getcPfad() {
		return cPfad;
	}

	public String getcUrl() {
		return cUrl;
	}

	public String getcUser() {
		return cUser;
	}

	public String getcKommentar() {
		return cKommentar;
	}
	
	public String getcHinzugefügterName(String pKurzUrl) {
		return cHinzugefügterName.replace("KURZURL", pKurzUrl);
	}


	//
	//				Getters für DB
	//
	
	public String getcDBName() {
		return cDBName;
	}
	
	public String getcTabelle() {
		return cTabelle;
	}
	
}

