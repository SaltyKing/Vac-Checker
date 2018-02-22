package main;

import java.io.File;
import java.io.IOException;

import variablen.Variablen;

public class DateiManager {

	private Variablen cVariablen = new Variablen();

	/**
	 * 			Überprüft ob alle nötigen Dateien vorhanden sind
	 */

	public void überprüfenDateien()
	{
			File lRootVerzeichnis = new File(cVariablen.getcPfad());
			File lUrlDatei = new File(cVariablen.getcUrl());

			// Überprüft ob das Hauptverzeichnis vorhanden ist
			// und erstellt es gegebenenfalls

			if (lRootVerzeichnis.exists() && lRootVerzeichnis.isDirectory())
			{
				System.out.println("[Debug] Root Verzeichnis ist Vorhanden");
			}
			else
			{
				System.out.println("[Debug] Root Verzeichnis wurde erstellt");
				erstellenVerzeichnis("", "");
			}

			// Überprüft ob die Url.txt vorhanden ist und erstellt
			// diese gegebenenfalls

			if (lUrlDatei.exists() && lUrlDatei.isFile())
			{
				System.out.println("[Debug] URL Datei ist vorhanden");
			}
			else
			{
				System.out.println("[Debug] URL Datei wurde erstellt");
				try {
					erstellenDatei(cVariablen.getcUrlDatei(), "");
				} catch (IOException e) {
					System.err.println("[Debug] Es ist ein Fehler aufgetreten");
				}
			}
	}

	/**
	 *
	 * @param pVerzeichnisName   == Name des zu erstellendem Verzeichnisses
	 * @param pPfad							== Der Pfad ausgehend vom Hauptverzeichnis (cVariablen.getPfad();)
	 */

	public void erstellenVerzeichnis(String pVerzeichnisName, String pPfad)
	{
		String lPfad = cVariablen.getcPfad() +pfadKorrektur(pPfad) + pVerzeichnisName;

		File lVerzeichnis = new File(lPfad);

		if (!lVerzeichnis.mkdirs())
		{
			System.err.println("[Debug] Der Ordner konnte nicht erstellt werden");
		}
	}

	/**
	 *
	 * @param pDateiName			== Name der zu erstellenden Datei
	 * @param pPfad						== Der Pfad ausgehen vom Hauptverzeichnis (cVariablen.getPfad();)
	 * @throws IOException
	 */

	public void erstellenDatei(String pDateiName, String pPfad) throws IOException
	{
		String lPfad = cVariablen.getcPfad() + pfadKorrektur(pPfad) + pDateiName;

		File lDatei = new File(lPfad);

		if(!lDatei.createNewFile())
		{
			System.err.println("[Debug] Die Datei konnte nicht erstellt werden");
		}
	}

	/**
	 *
	 * 			Überprüft ob der angegebene Pfad mit "\\" anfängt und Aufhört
	 * 			und korrigiert diesen gegebenenfalls
	 *
	 * @param pPfad
	 * @return
	 */

	private String pfadKorrektur(String pPfad)
	{
		String lPfad = pPfad;

		if (!pPfad.startsWith("\\"))
		{
			lPfad = "\\" + pPfad;
		}

		if (!pPfad.endsWith("\\"))
		{
			lPfad += "\\";
		}

		return lPfad;
	}


}
