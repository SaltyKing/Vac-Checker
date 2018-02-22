package detail;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class DetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btHinzufügen;


    @FXML
    void initialize() {
        assert btHinzufügen != null : "fx:id=\"btHinzufügen\" was not injected: check your FXML file 'DetailView.fxml'.";


    }

}
