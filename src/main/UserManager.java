package main;

public class UserManager {

//	private static DateiManager lDateiManager = new DateiManager();
//	private static UrlManager lUrlManager = new UrlManager();

	public static boolean vergleichenNamen(String pName, String pKurzUrl)
	{
		String lName = pName;
		String lNameGelesen = DBManager.lesenName(pKurzUrl);

		if (lName.matches(lNameGelesen))
		{
			return false;
		}
		else
		{
			return true;
		}

	}

}
