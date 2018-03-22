package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import variablen.Variablen;

public class DBManager {

	private static Variablen lVariablen = new Variablen();
	
	private static final String lDBName = lVariablen.getcDBName();
	private static final String lTabelle = lVariablen.getcTabelle();
	private static final String lDBVerbindung = "jdbc:derby:" + lDBName + ";create=true";
	
	private static Connection lVerbindung;
	
	private static Statement lStatement;
	private static PreparedStatement lVorbereitetStatement;
	private static ResultSet lErgebnis;
	
	private static String erstellenTabelle = "CREATE TABLE " + lTabelle + "(" + 
											 "UserID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," + 
											 "Name varchar(50) NOT NULL," + 
											 "firstName varchar(50) NOT NULL," +
											 "URL varchar(75) NOT NULL," + 
											 "shortURL varchar(20) NOT NULL," + 
											 "description varchar(255)" + 
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
	
	public static void hinzufügenBenutzer(String pName, String pUrl, String pShortUrl)
	{
		try {
			lVorbereitetStatement = getlVerbindung().prepareStatement("INSERT INTO " + lTabelle + " "
					+ "(Name, firstName, URL, shortUrl) "
					+ "VALUES (?, ?, ?, ?)");
			
			lVorbereitetStatement.setString(1, pName);
			lVorbereitetStatement.setString(2, pName);
			lVorbereitetStatement.setString(3, pUrl);
			lVorbereitetStatement.setString(4, pShortUrl);
			
			lVorbereitetStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void lesenTabelle()
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
