package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.item.User;
import variablen.Variablen;

public class DateiManager {

	private Variablen cVariablen = new Variablen();
	private UrlManager lUrlManager = new UrlManager();

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
					erstellenDatei(cVariablen.getcUrlDateiName(), "");
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

		if (pPfad.isEmpty())
		{
			return pPfad + "\\";
		}

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

	public void schreibenUrl(String pUrl) throws IOException
	{
		String lUrl = lUrlManager.korrigierenUrl(pUrl);

		BufferedWriter lBw = new BufferedWriter(new FileWriter(new File(cVariablen.getcUrl()), true));

		if (istDateiBeschrieben(cVariablen.getcUrl()))
		{
			lBw.write("\n" + lUrl);
		}
		else
		{
			lBw.write(lUrl);
		}
		lBw.close();
	}

	private boolean istDateiBeschrieben(String pUrl)
	{
		File lDatei = new File(pUrl);

		if (lDatei.exists())
		{
			double size = lDatei.length();

			if (size == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			System.err.println("[Debug] Datei wurde nicht gefunden (istDateiBeschrieben)");
			return false;
		}
	}

	public ArrayList<User> lesenUrlDatei(String pDateiName, String pPfad) throws IOException
	{
		String lPfad = cVariablen.getcPfad() + pfadKorrektur(pPfad) + pDateiName;
		ArrayList<User> lUserListe = new ArrayList<>();

		BufferedReader lBr = new BufferedReader(new FileReader(new File(lPfad)));
		String lZeile;

		while ((lZeile = lBr.readLine()) != null)
		{
			System.out.println(lZeile);
			lUserListe.add(lUrlManager.getUserDaten(lZeile));
		}

		lBr.close();

		return lUserListe;
	}

}
