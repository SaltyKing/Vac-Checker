package main.item;

public class User {

	private String url;
	private String name;
	private boolean banStatus;

	public User()
	{
		this.url = "";
		this.name = "";
		this.banStatus = false;
	}

	public User(String pURL, String pName, boolean pBanStatus) {
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

	public boolean isBanStatus() {
		return banStatus;
	}

	public void setBanStatus(boolean banStatus) {
		this.banStatus = banStatus;
	}


}
