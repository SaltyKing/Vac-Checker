package main;

import java.io.File;

import variablen.Variablen;

public class DateiManager {

	private Variablen cVariablen = new Variablen();

	/**
	 * 			Überprüft ob alle nötigen Dateien vorhanden sind
	 */

	public void überprüfenDateien()
	{
			File lRootVerzeichnis = new File(cVariablen.getcPfad());

			if (lRootVerzeichnis.exists() && lRootVerzeichnis.isDirectory())
			{
				System.out.println("[Debug] Root Verzeichnis ist Vorhanden");
			}
			else
			{
				System.out.println("[Debug] Root Verzeichnis wurde erstellt");
				erstellenVerzeichnis("", "");
			}

	}

	/**
	 *
	 * @param pVerzeichnisName   == Name des zu erstellendem Verzeichnisses
	 * @param pPfad							== Der Pfad ausgehend von cVariablen.getPfad(); (Hauptverzeichnis)
	 */

	public void erstellenVerzeichnis(String pVerzeichnisName, String pPfad)
	{
		String lPfad = cVariablen.getcPfad() +pfadKorrektur(pPfad) + pVerzeichnisName;


		File lVerzeichnis = new File(lPfad);

		if (!lVerzeichnis.mkdirs())
		{
			System.err.println("[Debug] Die Datei konnte nicht erstellt werden");
		}
	}

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
