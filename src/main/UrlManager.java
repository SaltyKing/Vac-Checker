package main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.item.User;


public class UrlManager {

	/**
	 * 			Überprüft ob die URL mit den Steamcommunity links Startet
	 *
	 * @param pUrl
	 * @return
	 */

	public boolean überprüfenUrl(String pUrl)
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

	public User getUserDaten(String pUrl) throws IOException
	{
		Document lDoc = Jsoup.connect(pUrl).userAgent("Mozilla/17.0").get();

		User lUser = new User(pUrl, getNamen(lDoc), getBanDaten(lDoc));

		return lUser;
	}

	private String getNamen(Document pURL)
	{
		Elements lElement = pURL.select("div.persona_name");

		for (Element name : lElement)
		{
			String lName = name.getElementsByTag("span").first().text();
			return lName;
		}
		return "Error (getNameData)";
	}

	private String getBanDaten(Document pURL)
	{
		Elements lElement = pURL.select("div.profile_ban_status");

		for (Element status : lElement)
		{
			if (status != null)
			{
				// TODO: String Analysieren und manipulieren
				String all = status.getAllElements().text();
				String days = all.substring(27, all.length() - 54);
				return "Gebannt seit " + days;
			}
			else
			{
			System.out.println("Nöscht");
			}
		}

		return "Nicht Gebannt";
	}

}
