package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	 * 			�berpr�ft ob alle n�tigen Dateien vorhanden sind
	 */

	public void �berpr�fenDateien()
	{
			File lRootVerzeichnis = new File(cVariablen.getcPfad());
			File lUrlDatei = new File(cVariablen.getcUrl());
			File lUserVerzeichnis = new File(cVariablen.getcUser());

			// �berpr�ft ob das Hauptverzeichnis vorhanden ist
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

			// �berpr�ft ob die Url.txt vorhanden ist und erstellt
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

			// �berpr�ft ob der User ordner vorhanden ist und
			// erstellt diesen gegebenenfalls

			if (lUserVerzeichnis.exists() && lUserVerzeichnis.isDirectory())
			{
				System.out.println("[Debug] User Verzeichnis ist vorhanden");
			}
			else
			{
				System.out.println("[Debug] User Verzeichnis wurde erstellt");
				erstellenVerzeichnis(cVariablen.getcUserVerzeichnis(), "");
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
	 * 			�berpr�ft ob der angegebene Pfad mit "\\" anf�ngt und Aufh�rt
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

		schreibenHinzugef�gterName(lUrlManager.k�rzenUrl(pUrl), lUrlManager.getUserDaten(pUrl).getName());
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

	public ArrayList<User> lesenUrlDatei() throws IOException
	{
		String lPfad = cVariablen.getcUrl();
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

	/**
	 * 			Dateistruktur
	 *
	 * 			User \\ (gek�rzteUrl) \\ **
	 * @throws IOException
	 *
	 */

	private void schreibenHinzugef�gterName(String pKurzUrl, String pHinzugef�gterName) throws IOException
	{
		String lPfad = cVariablen.getcHinzugef�gterName(pKurzUrl);
		String lUser = cVariablen.getcUser() + "\\" + pKurzUrl;

		File lName = new File(lPfad);
		File lUserVerzeichnis = new File(lUser);

		if (!lUserVerzeichnis.exists() || !lUserVerzeichnis.isDirectory())
		{
			System.err.println("[Debug] Der Benutzer ist noch nicht vorhanden und wird erstellt");
			erstellenVerzeichnis(pKurzUrl, cVariablen.getcUserVerzeichnis());
		}

		if (!lName.exists() || !lName.isFile())
		{
			System.err.println("[Debug] Die Namensdatei des Benutzers ist noch nicht vorhanden und wird erstellt");
			erstellenDatei(cVariablen.getcHinzugef�gterNameDatei(), cVariablen.getcUserVerzeichnis() + "\\" + pKurzUrl);
		}

		FileWriter lFw = new FileWriter(lName);

		lFw.write(pHinzugef�gterName);

		lFw.close();
	}

	public String lesenName(String pKurzUrl)
	{
		String lPfad = cVariablen.getcHinzugef�gterName(pKurzUrl);
		String lName;

		try {
			BufferedReader lBr = new BufferedReader(new FileReader(new File(lPfad)));

			lName = lBr.readLine();
			lBr.close();

			return lName;
		} catch (FileNotFoundException e) {
			System.err.println("[Debug] Die Datei konnte nicht gefunden werden (lesenName)");
			return "Datei Fehler";
		} catch (IOException e) {
			System.err.println("[Debug] Es ist ein Fehler aufgetreten (lesenName)");
			return "Fehler";
		}


	}

}
