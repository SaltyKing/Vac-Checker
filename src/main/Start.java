package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application {
	
	private static DBManager lDBManager = new DBManager();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ui/MainView.fxml"));
			primaryStage.setTitle("Vac Checker");
			Scene scene = new Scene(root, 590, 390);
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> DBManager.beendenTabelle());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBManager.setProperty();
		lDBManager.connect();
		lDBManager.lesenTabelle();
		launch(args);
	}
}
