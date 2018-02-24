package main;

public class UserManager {

	private static DateiManager lDateiManager = new DateiManager();
	private static UrlManager lUrlManager = new UrlManager();

	public static String vergleichenNamen(String pName, String pUrl)
	{
		String lName = pName;
		String lKurzUrl = lUrlManager.kürzenUrl(pUrl);
		String lNameGelesen = lDateiManager.lesenName(lKurzUrl);

		if (lName.matches(lNameGelesen))
		{
			return lName;
		}
		else
		{
			return lName +  " [" + lNameGelesen + "]";
		}

	}

}
