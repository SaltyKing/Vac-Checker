package main.item;

public class User {

	private String url;
	private String name;
	private String banStatus;

	public User()
	{
		this.url = "";
		this.name = "";
		this.banStatus = "";
	}

	public User(String pURL, String pName, String pBanStatus) {
		super();
		url = pURL;
		name = pName;
		banStatus = pBanStatus;
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

	public String getBanStatus() {
		return banStatus;
	}

	public void setBanStatus(String banStatus) {
		this.banStatus = banStatus;
	}


}
