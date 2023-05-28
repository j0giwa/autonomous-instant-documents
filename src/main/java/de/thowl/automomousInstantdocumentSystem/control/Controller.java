/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind, Marvin Boschmann
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package de.thowl.automomousInstantdocumentSystem.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.thowl.automomousInstantdocumentSystem.model.Os;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This Class is the controller of the class <emph>Gui</emph>
 * 
 * @author Jonas Schwind
 * @version 0.1.2
 * @see de.thowl.automomousInstantdocumentSystem.view.Gui
 */
public class Controller implements Initializable {

	Alert errorAlert = new Alert(AlertType.ERROR);
	private Stage stage;
	private Scene scene;
	private Parent root;

	// Sidebar
	@FXML
	private Button btnMainScene;
	@FXML
	private Button btnLatexScene;
	@FXML
	private Button btnDatabaseScene;

	// Button area
	@FXML
	private ComboBox<String> cmbType;
	@FXML
	private TextField txtAmount;
	@FXML
	private TextField txtChapters;
	@FXML
	private TextField txtChatGptPrompt;
	@FXML
	private CheckBox chkShuffle;
	@FXML
	private Button btnGenerateDocument;
	@FXML
	private Button btnChatGptGo;

	// Multipurpose TextArea
	@FXML
	private TextArea txtMultipurposeTextArea;

	/**
	 * This method chages the scene to the specifiedd one
	 * 
	 * @param event Actionevent from the current scene
	 * @param name  scene to switch to
	 */
	private void switchToScene(ActionEvent event, String name) throws IOException {
		root = FXMLLoader.load(getClass().getClassLoader().getResource(name + ".fxml"));
		root.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method populates the Dropdownmenu <emph>cmbType</emph>
	 */
	private void initialiseTypeDropdown() {
		Os os = new Os();
		String homeDir = os.getHomeDir();
		String snippetsDir = homeDir + "/latex";
		File directory = new File(snippetsDir);
		String[] files = directory.list();
		ArrayList<String> dropdownItems = new ArrayList<String>();
		for (String file : files) {
			if (new File(snippetsDir + "/" + file).isDirectory()) {
				dropdownItems.add(file);
			}
		}
		cmbType.getItems().setAll(dropdownItems);
	}

	/**
	 * This metod appends a String to the TextArea
	 * <emph>txtMultipurposeTextArea</emph>
	 * 
	 * @param newString String to append
	 */
	private void appendToMultiPurposeTextArea(String newString) {
		StringBuilder areaContent = new StringBuilder();
		areaContent.append(txtMultipurposeTextArea.getText());
		areaContent.append(newString);
		txtMultipurposeTextArea.setText(areaContent.toString());
	}

	/**
	 * This method valtidates if an supposed integer is an actual integer
	 * 
	 * @param inputInt Integer to validate
	 * @return Integervalue (if int)
	 */
	private int validateInt(String inputInt) {
		int integer = 0;
		try {
			integer = Integer.parseInt(inputInt);
		} catch (NumberFormatException e) {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			errorAlert.setHeaderText("NumberFormatException");
			errorAlert.setContentText(stringWriter.toString());
			errorAlert.showAndWait();
			return 0;
		}
		return integer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Populate Dropdown menu
		try {
			initialiseTypeDropdown();
		} catch (NullPointerException e) {
			// Do nothing
		}
	}

	// Events
	@FXML
	private void btnMainSceneClick(ActionEvent event) {
		try {
			switchToScene(event, "MainScene");
		} catch (IOException e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			errorAlert.setHeaderText("NumberFormatException");
			errorAlert.setContentText(sw.toString());
			errorAlert.showAndWait();
			return;
		}
	}

	@FXML
	private void btnLatexSceneClick(ActionEvent event) {
		try {
			switchToScene(event, "LatexScene");
		} catch (IOException e) {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			errorAlert.setHeaderText("NumberFormatException");
			errorAlert.setContentText(stringWriter.toString());
			errorAlert.showAndWait();
			return;
		}
	}

	@FXML
	private void btnDatabaseSceneClick(ActionEvent event) {
		try {
			switchToScene(event, "DatabaseScene");
		} catch (IOException e) {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			errorAlert.setHeaderText("NumberFormatException");
			errorAlert.setContentText(stringWriter.toString());
			errorAlert.showAndWait();
			return;
		}
	}

	/**
	 * This method contains the logic behind the "Generate" Button on the GUI
	 * Exected when button is pressed
	 */
	@FXML
	private void btnGenerateDocumentClick(ActionEvent event) {
		final String type = cmbType.getSelectionModel().getSelectedItem();
		final String destination = "/home/jogiwa/Downloads"; // TODO: add location in settings
		final int amount = validateInt(txtAmount.getText());
		final int chapters = validateInt(txtChapters.getText());
		final boolean shuffle = chkShuffle.isArmed();
		if (type == null || amount <= 0 || chapters <= 0) {
			errorAlert.setHeaderText("Invalid Inputs");
			errorAlert.setContentText("Please check your inputs");
			errorAlert.showAndWait();
			return;
		}
		appendToMultiPurposeTextArea("[ INFO ] Starting new LaTeX instance\n");
		new Thread(() -> {
			Latex latex = new Latex();
			latex.generate(type, destination, amount, chapters, shuffle);
			Platform.runLater(() -> {
				appendToMultiPurposeTextArea(
						"[ INFO ]  Generating " + amount + " Documents with " + chapters
								+ " Chapters...\n");
			});
		}).start();
		appendToMultiPurposeTextArea("[ INFO ]  done!\n");
	}

	@FXML
	private void btnChatGptGoClick() {
		// Todo: add logic
	}
}
