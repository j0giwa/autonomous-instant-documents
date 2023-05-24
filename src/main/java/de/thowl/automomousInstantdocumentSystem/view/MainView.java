package de.thowl.automomousInstantdocumentSystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
		primaryStage.setTitle("Automomous Instantdocument System");
		primaryStage.setScene(new Scene(root, 765, 490));
		primaryStage.show();
	}
}
