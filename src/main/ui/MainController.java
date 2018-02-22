package main.ui;

import main.DateiManager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController implements Initializable{

	private DateiManager lDateiManager = new DateiManager();

	@FXML
	private TableView<?> tvListe;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private Button btHinzuf�gen;

    @FXML
    private TextField tfHinzuf�gen;

    public void btHinzuf�genAction(ActionEvent event)
    {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		lDateiManager.�berpr�fenDateien();

	}

}
