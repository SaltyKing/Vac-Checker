package variablen;

public class Variablen {

	//
	//				Dateinamen
	//

	private final String cUrlDateiName = "url.txt";

	//
	// 			Pfade
	//

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";
	private final String cUrl = cPfad + "\\" + cUrlDateiName ;

	//
	//				Getters und Setters f�r Dateinamen
	//

	public String getcUrlDateiName() {
		return cUrlDateiName;
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

