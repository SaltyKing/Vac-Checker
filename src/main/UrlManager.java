package main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.item.User;


public class UrlManager {

	private DBManager lDBManager = new DBManager();
	
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

	public User getUserDaten(String pUrl) throws IOException
	{
		Document lDoc = Jsoup.connect(pUrl).userAgent("Mozilla/17.0").get();

		User lUser = new User(pUrl, getNamen(lDoc), UserManager.vergleichenNamen(getNamen(lDoc), pUrl), getBanDaten(lDoc), k�rzenUrl(pUrl));
		
		return lUser;
	}

	private String getNamen(Document pURL)
	{
		Elements lElement = pURL.select("div.persona_name");

		for (Element name : lElement)
		{
			String lName = name.getElementsByTag("span").first().text();
			
			// Daten Hinzuf�gen zur SQL Tabelle (muss hier sein, damit der erste Name vorhanden ist) !! TEMPOR�R !!
			
//			DBManager.hinzuf�genBenutzer(lNameTemp, pUrlString, k�rzenUrl(pUrlString));
			
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
			System.out.println("N�scht");
			}
		}

		return "Nicht Gebannt";
	}

	public String k�rzenUrl(String pURL)
	{
		String kurzeURL;

		if (pURL.startsWith("http://steamcommunity.com/id/"))
		{
			kurzeURL = pURL.substring(29, pURL.length() - 1);
			return kurzeURL;
		}
		else if (pURL.startsWith("http://steamcommunity.com/profiles/"))
		{
			kurzeURL = pURL.substring(35, pURL.length() - 1);
			return kurzeURL;
		}
		return "error";
	}

	public String entk�rzenURL(String pURL)
	{
		String langeURL;

		if (pURL.matches("^[0-9]*$"))
		{
			langeURL = "http://steamcommunity.com/profiles/" + pURL + "/";
			return langeURL;
		}
		else
		{
			langeURL = "http://steamcommunity.com/id/" + pURL + "/";
			return langeURL;
		}
	}

}
