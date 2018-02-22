package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ui/MainView.fxml"));
			primaryStage.setTitle("Vac Checker");
			Scene scene = new Scene(root, 590, 390);
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
