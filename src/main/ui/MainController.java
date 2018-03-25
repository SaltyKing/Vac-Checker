package main.ui;

import main.DBManager;
import main.UrlManager;
import main.UserManager;
import main.item.User;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable{

//	private DateiManager lDateiManager = new DateiManager();
//	private Variablen cVariablen = new Variablen();
	private UrlManager lUrlManager = new UrlManager();
//	private UserManager lUserManager = new UserManager();
	
	public static User ausgewählterBenutzer = null;

	@FXML
	private TableView<User> tvListe;

    @FXML
    private TableColumn<User, String> tcName, tcStatus;

    @FXML
    private Button btHinzufügen, btTest;

    @FXML
    private TextField tfHinzufügen;

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
//				lDateiManager.schreibenUrl(lUrl);
				DBManager.hinzufügenBenutzer(lUrlManager.korrigierenUrl(lUrl));
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
//		lListe = lDateiManager.lesenUrlDatei();
		lListe = DBManager.lesenTabelle();
		for (User lUser : lListe)
		{
			
			if (UserManager.vergleichenNamen(lUser.getName(), lUser.getKurzUrl()))
			{
				lUser.setName(lUser.getName() + " [" + lUser.getFirstName() + "]");
				
			}
			
			
//			DBManager.hinzufügenBenutzer(lUser.getName(), lUser.getUrl(), lUser.getKurzUrl());
			
			tvListe.getItems().add(lUser);
		}

//		pcChart.getData().get(0).setPieValue(nichtGebannt);
//		pcChart.getData().get(1).setPieValue(gebannt);
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

//	public Property<ObservableList<PieChart.Data>> pieChartData()
//	{
//		Data nichtGebannt = new Data("nicht Gebannt", 0);
//	    nichtGebannt.setPieValue(0);
//	    nichtGebannt.setName("nicht Gebannt");
//	    Data gebannet = new Data("Gebannt", 0);
//	    gebannet.setPieValue(0);
//	    gebannet.setName("Gebannt");
//
//		Property<ObservableList<PieChart.Data>> pieChartData = new SimpleListProperty<Data>(FXCollections.observableList(new ArrayList<Data>()));
//		pieChartData.getValue().add(nichtGebannt);
//		pieChartData.getValue().add(gebannet);
//
//		return pieChartData;
//	}

//	private void erstellenStatistik()
//	{
//		pcChart.dataProperty().bind(pieChartData());
//
//		pcChart.setPrefSize(450.0, 450.0);
//		pcChart.getData().get(0).getNode().setStyle("-fx-pie-color: #778899");//Lightslategrey
//		pcChart.getData().get(1).getNode().setStyle("-fx-pie-color: #ff4f4f");// Good-Red
//		pcChart.setLegendVisible(false);
//	}

	public void öffnenErweitert(ActionEvent event)
	{
		User lUser = tvListe.getSelectionModel().getSelectedItem();
		
		try {
			
			if (lUser != null)
			{
				setAusgewählterBenutzer(lUser);
				
				Parent root = FXMLLoader.load(erweitert.ErweitertController.class.getResource("ErweitertView.fxml"));
				
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Erweiterte Ansicht");
				stage.setScene(new Scene(root, 590, 390));
				stage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		DBManager.hinzufügenBild(lUser);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
//		lDateiManager.überprüfenDateien();
		erstellenTabelle();

		erneuernTabelle.start();
//		hinzufügenTabelle();
	}

	public static User getAusgewählterBenutzer() {
		return ausgewählterBenutzer;
	}

	public static void setAusgewählterBenutzer(User ausgewählterBenutzer) {
		MainController.ausgewählterBenutzer = ausgewählterBenutzer;
	}

}
