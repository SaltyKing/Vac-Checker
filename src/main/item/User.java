package main.item;

public class User {

	private String url;
	private String name;
	private String firstName;
	private String banStatus;
	private String kurzUrl;

	public User()
	{
		this.url = "";
		this.name = "";
		this.firstName = "";
		this.banStatus = "";
		this.kurzUrl = "";
	}

	public User(String pURL, String pName, String pFirstName, String pBanStatus, String pKurzUrl) {
		this.url = pURL;
		this.name = pName;
		this.firstName = pFirstName;
		this.banStatus = pBanStatus;
		this.kurzUrl = pKurzUrl;

	}

	//
	//			Getters und Setters
	//

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


}
