package variablen;

public class Variablen {

	//
	//				Dateinamen
	//

	private final String cUrlDatei = "url.txt";

	//
	// 			Pfade
	//

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";
	private final String cUrl = cPfad + "\\" + cUrlDatei ;

	//
	//				Getters und Setters f�r Dateinamen
	//

	public String getcUrlDatei() {
		return cUrlDatei;
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

}

