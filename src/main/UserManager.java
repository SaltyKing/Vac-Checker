package main;

public class UserManager {

	private static DateiManager lDateiManager = new DateiManager();
	private static UrlManager lUrlManager = new UrlManager();

	public static String vergleichenNamen(String pName, String pUrl)
	{
		String lName = pName;
		String lKurzUrl = lUrlManager.kürzenUrl(pUrl);
		String lNameGelesen = lDateiManager.lesenName(lKurzUrl);

		if (lNameGelesen.matches("Datei Fehler"))
		{
			return "";
		}

		if (lName.matches(lNameGelesen))
		{
			return "";
		}
		else
		{
			return lNameGelesen;
		}

	}

}
