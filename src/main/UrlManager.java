package main;

public class UrlManager {

	/**
	 * 			�berpr�ft ob die URL mit den Steamcommunity links Startet
	 *
	 * @param pUrl
	 * @return
	 */

	public boolean �berpr�fenUrl(String pUrl)
	{
		String valid = "http://steamcommunity.com/id/";
		String validId = "http://steamcommunity.com/profiles/";

		if (pUrl.startsWith(valid) || pUrl.startsWith(validId))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String korrigierenUrl(String pUrl)
	{
		String lUrl = pUrl;

		if (!pUrl.endsWith("/"))
		{
			lUrl = pUrl + "/";
		}

		return lUrl;
	}

}
