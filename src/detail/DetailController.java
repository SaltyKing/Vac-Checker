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
    private Button btHinzuf�gen;


    @FXML
    void initialize() {
        assert btHinzuf�gen != null : "fx:id=\"btHinzuf�gen\" was not injected: check your FXML file 'DetailView.fxml'.";


    }

}
