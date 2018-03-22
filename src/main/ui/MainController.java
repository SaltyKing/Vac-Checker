package main.ui;

import main.DBManager;
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

import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{

	private DateiManager lDateiManager = new DateiManager();
//	private Variablen cVariablen = new Variablen();
	private UrlManager lUrlManager = new UrlManager();
//	private UserManager lUserManager = new UserManager();

	private double gebannt = 0.0;
	private double nichtGebannt = 0.0;

	@FXML
	private TableView<User> tvListe;

    @FXML
    private TableColumn<User, String> tcName;

    @FXML
    private TableColumn<User, String> tcStatus;

    @FXML
    private Button btHinzufügen;

    @FXML
    private TextField tfHinzufügen;

    @FXML
    private Accordion acAccordion;

    @FXML
    private TitledPane tpStatistik;

    @FXML
    private PieChart pcChart;

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
			hinzufügenTabelle();
			timer.stop();
			timer.start();
		}
	});

    public void btHinzufügenAction(ActionEvent event)
    {
    	String lUrl = tfHinzufügen.getText().toString().trim();

    	if (!lUrl.isEmpty())
    	{
    		tfHinzufügen.setStyle("-fx-border-color: null");

    		if (lUrlManager.überprüfenUrl(lUrl))
    		{
    			try {
					lDateiManager.schreibenUrl(lUrl);
				} catch (IOException e) {
					System.err.println("[Debug] Die URL Datei konnte nicht beschrieben werden");
				}
    		}

    		tfHinzufügen.clear();
    		erneuernTabelle.start();
    	}
    	else
    	{
    		tfHinzufügen.setStyle("-fx-border-color: red");
    	}
    }

    private void hinzufügenTabelle()
    {
    	tvListe.getItems().clear();

    	ArrayList<User> lListe;
		try {
			lListe = lDateiManager.lesenUrlDatei();
			for (User lUser : lListe)
			{
				
				if (lUser.getFirstName() != "")
				{
					lUser.setName(lUser.getName() + " [" + lUser.getFirstName() + "]");
				}
				
//				DBManager.hinzufügenBenutzer(lUser.getName(), lUser.getUrl(), lUser.getKurzUrl());

				if (lUser.getBanStatus().matches("Nicht Gebannt"))
				{
					nichtGebannt++;
				}
				else
				{
					gebannt++;
				}

				tvListe.getItems().add(lUser);
			}
		} catch (IOException e) {
			System.err.println("[Debug] Es ist ein fehler aufgetreten (hinzufügenTabelle)");
		}

		pcChart.getData().get(0).setPieValue(nichtGebannt);
		pcChart.getData().get(1).setPieValue(gebannt);
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

	public Property<ObservableList<PieChart.Data>> pieChartData()
	{
		Data nichtGebannt = new Data("nicht Gebannt", 0);
	    nichtGebannt.setPieValue(0);
	    nichtGebannt.setName("nicht Gebannt");
	    Data gebannet = new Data("Gebannt", 0);
	    gebannet.setPieValue(0);
	    gebannet.setName("Gebannt");

		Property<ObservableList<PieChart.Data>> pieChartData = new SimpleListProperty<Data>(FXCollections.observableList(new ArrayList<Data>()));
		pieChartData.getValue().add(nichtGebannt);
		pieChartData.getValue().add(gebannet);

		return pieChartData;
	}

	private void erstellenStatistik()
	{
		pcChart.dataProperty().bind(pieChartData());

		pcChart.setPrefSize(450.0, 450.0);
		pcChart.getData().get(0).getNode().setStyle("-fx-pie-color: #778899");//Lightslategrey
		pcChart.getData().get(1).getNode().setStyle("-fx-pie-color: #ff4f4f");// Good-Red
		pcChart.setLegendVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		lDateiManager.überprüfenDateien();
		erstellenTabelle();
		erstellenStatistik();

		erneuernTabelle.start();
	}

}
