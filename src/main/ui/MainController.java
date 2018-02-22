package main.ui;

import main.DateiManager;
import main.UrlManager;
import main.item.User;
import variablen.Variablen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
	private Variablen cVariablen = new Variablen();
	private UrlManager lUrlManager = new UrlManager();

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

    public void btHinzufügenAction(ActionEvent event)
    {
    	try {
			hinzufügenTabelle();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    private void hinzufügenTabelle() throws IOException
    {
    	ArrayList<User> lListe = lDateiManager.lesenUrlDatei(cVariablen.getcUrlDateiName(), "");

    	for (User lUser : lListe)
    	{
    		User lNutzer = new User();
    		lNutzer.setName(lUser.getName());
    		lNutzer.setBanStatus(lUser.getBanStatus());
    		tvListe.getItems().add(lNutzer);
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
		lDateiManager.überprüfenDateien();
		erstellenTabelle();
	}

}
