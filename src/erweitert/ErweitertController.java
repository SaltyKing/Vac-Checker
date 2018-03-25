package erweitert;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.DBManager;
import main.item.User;
import main.ui.MainController;

public class ErweitertController implements Initializable{

    @FXML
    private Label lbName, lbLink, lbStatus, lbHinzugefügt;
    
    @FXML
    private ImageView ivBild;

    private void erstellenOberfläche()
    {
    	User lUser = MainController.getAusgewählterBenutzer();
		
		lbName.setText(lUser.getName());
		lbLink.setText(lUser.getKurzUrl());
		lbStatus.setText(lUser.getBanStatus());
		
		DateTimeFormatter formatEingabe = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
		LocalDate ld = LocalDate.parse( lUser.getHinzugefuegt().toString() , formatEingabe );
		
		DateTimeFormatter formatAusgabe = DateTimeFormatter.ofPattern( "dd.MM.uuuu");
		String neuesDatum = ld.format( formatAusgabe );
		
		lbHinzugefügt.setText(neuesDatum);
		
		Image a = DBManager.bekommenBild(lUser);
		
		ivBild.setImage(a);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		erstellenOberfläche();
	}

}
