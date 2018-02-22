package main.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

	@FXML
	private TableView<?> tvListe;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private Button btHinzufügen;

    @FXML
    private TextField tfHinzufügen;

}
