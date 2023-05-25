package de.thowl.automomousInstantdocumentSystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the Gui of the Programm
 * Is just a loader for an fxml-file
 * The controling logic can be found in the <emph>Conttroller</emph> class
 * 
 * @author Jonas Schwind
 * @version 1.0.3
 * 
 * @see de.thowl.automomousInstantdocumentSystem.control.Controller
 */
public class Gui extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScene.fxml"));
		root.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());
		primaryStage.setTitle("Automomous Instantdocument System");
		primaryStage.setScene(new Scene(root, 760, 490));
		primaryStage.show();
	}
}
