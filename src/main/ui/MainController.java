package main.ui;

import main.DateiManager;
import main.UrlManager;
import main.item.User;
//import main.UrlManager;
//import variablen.Variablen;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.Timer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{

	private DateiManager lDateiManager = new DateiManager();
//	private Variablen cVariablen = new Variablen();
	private UrlManager lUrlManager = new UrlManager();
//	private UserManager lUserManager = new UserManager();

	@FXML
	private TableView<User> tvListe;

    @FXML
    private TableColumn<User, String> tcName;

    @FXML
    private TableColumn<User, String> tcStatus;

    @FXML
    private Button btHinzuf�gen;

    @FXML
    private TextField tfHinzuf�gen;

	Timer timer = new Timer(300000, new ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			timer.stop();
			System.out.println("[Debug] Nach 5 Minuten erneuert");
			erneuernTabelle.start();
			timer.start();
		}
	});

	Timer erneuernTabelle = new Timer(1, new ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			erneuernTabelle.stop();
			System.out.println("[Debug] Tabelle erneuern");
			hinzuf�genTabelle();
			timer.stop();
			timer.start();
		}
	});

    public void btHinzuf�genAction(ActionEvent event)
    {
    	String lUrl = tfHinzuf�gen.getText().toString().trim();

    	if (!lUrl.isEmpty())
    	{
    		tfHinzuf�gen.setStyle("-fx-border-color: null");

    		if (lUrlManager.�berpr�fenUrl(lUrl))
    		{
    			try {
					lDateiManager.schreibenUrl(lUrl);
				} catch (IOException e) {
					System.err.println("[Debug] Die URL Datei konnte nicht beschrieben werden");
				}
    		}

    		tfHinzuf�gen.clear();
    		erneuernTabelle.start();
    	}
    	else
    	{
    		tfHinzuf�gen.setStyle("-fx-border-color: red");
    	}
    }

    private void hinzuf�genTabelle()
    {
    	tvListe.getItems().clear();

    	ArrayList<User> lListe;
		try {
			lListe = lDateiManager.lesenUrlDatei();
			for (User lUser : lListe)
			{
				tvListe.getItems().add(lUser);
			}
		} catch (IOException e) {
			System.err.println("[Debug] Es ist ein fehler aufgetreten (hinzuf�genTabelle)");
		}
    }

    public ObservableList<User> userListe()
    {
    	ObservableList<User> lUserListe = FXCollections.observableArrayList();

    	return lUserListe;
    }

    private void erstellenTabelle()
    {
    	tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tcStatus.setCellValueFactory(new PropertyValueFactory<>("banStatus"));

    	tvListe.setItems(userListe());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		lDateiManager.�berpr�fenDateien();
		erstellenTabelle();

		erneuernTabelle.start();
		}

}
