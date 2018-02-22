package Variablen;

public class Variablen {

	private final String cPfad = System.getenv("AppData") + "\\Vac-Checker";

	private final String cUrl = cPfad + "\\url.txt";

	//
	//				Getters und Setters
	//

	public String getcPfad() {
		return cPfad;
	}

	public String getcUrl() {
		return cUrl;
	}


}
