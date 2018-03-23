package variablen;

public class Variablen {

	//
	//				Dateinamen
	//

	private final String cUrlDateiName = "url.txt";
	private final String cHinzugefügterNameDatei = "name.txt";
	private final String cUserVerzeichnis = "User";

	//
	// 			Pfade
	//

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";
	private final String cUrl = cPfad + "\\" + cUrlDateiName ;
	private final String cUser = cPfad + "\\" + cUserVerzeichnis;
	private final String cHinzugefügterName = cUser + "\\KURZURL\\" + cHinzugefügterNameDatei;

	//
	//				Getters und Setters für Dateinamen
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

	//
	//				Getters und Setters für Pfade
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

	public String getcHinzugefügterName(String pKurzUrl) {
		return cHinzugefügterName.replace("KURZURL", pKurzUrl);
	}

}

