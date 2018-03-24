package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import main.item.User;
import variablen.Variablen;

public class DBManager {

	private static Variablen lVariablen = new Variablen();
	private static UrlManager lUrlManager = new UrlManager();
	
	private static final String lDBName = lVariablen.getcDBName();
	private static final String lTabelle = lVariablen.getcTabelle();
	private static final String lDBVerbindung = "jdbc:derby:" + lDBName + ";create=true";
	
	private static Connection lVerbindung;
	
	private static Statement lStatement;
	private static PreparedStatement lVorbereitetStatement;
	private static ResultSet lErgebnis;
	
	private static String erstellenTabelle = "CREATE TABLE " + lTabelle + "(" + 
							/* 1 */			 "UserID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," + 
							/* 2 */			 "Name varchar(50) NOT NULL," + 
							/* 3 */			 "firstName varchar(50) NOT NULL," +
							/* 4 */			 "URL varchar(75) NOT NULL," + 
							/* 5 */			 "shortURL varchar(20) NOT NULL," + 
							/* 6 */			 "description varchar(255)" + 
							/* 7 */			 "hinzugefuegt Date" +
											 ")";
	

	
	public static void connect()
	{
		try {
			lVerbindung = DriverManager.getConnection(lDBVerbindung);
			lStatement = getlVerbindung().createStatement();
			istTabelleVorhanden();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void hinzufügenBenutzer(String pUrl)
	{
		UrlManager lUrlManager = new UrlManager();
		
		try {
			User lUser = lUrlManager.getUserDaten(pUrl);
			
			String lName = lUser.getName();
			String lUrl = lUser.getUrl();
			String lKurzUrl = lUser.getKurzUrl();
			
			lVorbereitetStatement = getlVerbindung().prepareStatement("INSERT INTO " + lTabelle + " "
					+ "(Name, firstName, URL, shortUrl) "
					+ "VALUES (?, ?, ?, ?)");
			
			lVorbereitetStatement.setString(1, lName);
			lVorbereitetStatement.setString(2, lName);
			lVorbereitetStatement.setString(3, lUrl);
			lVorbereitetStatement.setString(4, lKurzUrl);
			
			lVorbereitetStatement.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> lesenTabelle()
	{

		ArrayList<User> lUserListe = new ArrayList<>();
		
		try {
			lErgebnis = lStatement.executeQuery("SELECT * FROM benutzer");
			
			while (lErgebnis.next())
			{
				User lUser = new User();
				
				lUser.setId(lErgebnis.getInt(1));
				lUser.setName(lErgebnis.getString(2));
				lUser.setFirstName(lErgebnis.getString(3));
				lUser.setUrl(lErgebnis.getString(4));
				lUser.setKurzUrl(lErgebnis.getString(5));
				lUser.setBanStatus(lUrlManager.getBanDaten(Jsoup.connect(lErgebnis.getString(4)).userAgent("Mozilla/17.0").get()));
				lUser.setHinzugefuegt(lErgebnis.getDate(7));
				
				lUserListe.add(lUser);
			}
			
			return lUserListe;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String lesenName(String pKurzUrl)
	{
		try {
			lVorbereitetStatement = getlVerbindung().prepareStatement("SELECT * FROM benutzer WHERE shortUrl = ?");
			
			lVorbereitetStatement.setString(1, pKurzUrl);
			
			lErgebnis = lVorbereitetStatement.executeQuery();
			
			lErgebnis.next();
			
			return lErgebnis.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Fehler";
		}
	}
	
	public static void ausgebenTabelle()
	{
		try {
			lErgebnis = getlStatement().executeQuery("SELECT * FROM benutzer");
			
			System.out.println("--------------------------");
			
			while (lErgebnis.next())
			{
				System.out.println(lErgebnis.getInt(1) + " " + lErgebnis.getString(2) + " " + lErgebnis.getString(3) + " " + lErgebnis.getString(4) + " " + lErgebnis.getString(5) + " " + lErgebnis.getString(6));
			}
			
			System.out.println("-------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void istTabelleVorhanden()
	{
		try {
			lStatement.executeQuery("SELECT * FROM " + lTabelle);
			System.out.println("[Debug] SQL Tabelle vorhanden!");
		} catch (SQLException e) {
			System.out.println("[Debug] SQL Tabelle wurde erstellt");
			try {
				lStatement.executeUpdate(erstellenTabelle);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public static final void beendenTabelle()
	{
		Variablen lVariablen = new Variablen();
		String lBeenden = "jdbc:derby:" + lVariablen.getcDBName() + ";shutdown=true";
		try {
			System.out.println("[Debug] Verbindung geschlossen");
			DriverManager.getConnection(lBeenden);
		} catch (SQLException e) {
			// Ignoriert, dass die Datenbank am Ende des Programms heruntergefahren wird
			if (!e.getMessage().matches("Database '" + lVariablen.getcDBName() + "' shutdown."))
			{
				e.printStackTrace();
			}
		}
	}
	
	public static final void setProperty()
	{
		System.setProperty("derby.system.home", System.getenv("AppData") + "\\Vac-Checker");
	}
	
	//
	// Getter und Setter
	//
	
	public static Statement getlStatement() {
		return lStatement;
	}

	public static Connection getlVerbindung() {
		return lVerbindung;
	}
}
