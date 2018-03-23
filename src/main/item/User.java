package main.item;

import java.util.Date;

public class User {

	private int id;
	private String url;
	private String name;
	private String firstName;
	private String banStatus;
	private String kurzUrl;
	private Date hinzugefuegt;
	
	public User()
	{
		this.id = 1;
		this.url = "";
		this.name = "";
		this.firstName = "";
		this.banStatus = "";
		this.kurzUrl = "";
		this.hinzugefuegt = null;
	}

	public User(int pID, String pURL, String pName, String pFirstName, String pBanStatus, String pKurzUrl, Date pHinzugefuegt) {
		this.id = pID;
		this.url = pURL;
		this.name = pName;
		this.firstName = pFirstName;
		this.banStatus = pBanStatus;
		this.kurzUrl = pKurzUrl;
		this.hinzugefuegt = pHinzugefuegt;
	}

	//
	//			Getters und Setters
	//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBanStatus() {
		return banStatus;
	}

	public void setBanStatus(String banStatus) {
		this.banStatus = banStatus;
	}

	public String getKurzUrl() {
		return kurzUrl;
	}

	public void setKurzUrl(String kurzUrl) {
		this.kurzUrl = kurzUrl;
	}

	public Date getHinzugefuegt() {
		return hinzugefuegt;
	}

	public void setHinzugefuegt(Date hinzugefuegt) {
		this.hinzugefuegt = hinzugefuegt;
	}


}
