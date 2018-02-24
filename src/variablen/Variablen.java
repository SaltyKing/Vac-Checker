package variablen;

public class Variablen {

	//
	//				Dateinamen
	//

	private final String cUrlDateiName = "url.txt";
	private final String cHinzugef�gterNameDatei = "name.txt";
	private final String cUserVerzeichnis = "User";

	//
	// 			Pfade
	//

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";
	private final String cUrl = cPfad + "\\" + cUrlDateiName ;
	private final String cUser = cPfad + "\\" + cUserVerzeichnis;
	private final String cHinzugef�gterName = cUser + "\\KURZURL\\" + cHinzugef�gterNameDatei;

	//
	//				Getters und Setters f�r Dateinamen
	//

	public String getcUrlDateiName() {
		return cUrlDateiName;
	}

	public String getcHinzugef�gterNameDatei() {
		return cHinzugef�gterNameDatei;
	}

	public String getcUserVerzeichnis(){
		return cUserVerzeichnis;
	}

	//
	//				Getters und Setters f�r Pfade
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

	public String getcHinzugef�gterName(String pKurzUrl) {
		return cHinzugef�gterName.replace("KURZURL", pKurzUrl);
	}

}
