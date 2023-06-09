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

package de.thowl.automomousinstantdocumentsystem.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.thowl.automomousinstantdocumentsystem.model.OperatingSystem;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * This Class is the controller of the class <em>Gui</em>
 * 
 * @author Jonas Schwind
 * @version 0.1.2
 * @see de.thowl.automomousinstantdocumentsystem.view.Gui
 */
public class MasterController implements Initializable {

	@FXML
	private Button btnMainScene;
	@FXML
	private Button btnLatexScene;
	@FXML
	private Button btnDatabaseScene;

	@FXML
	protected ComboBox<String> cmbType;

	/**
	 * Displays an error alert with the specified header and exception
	 * details.
	 *
	 * @param header Header text for the error alert
	 * @param e      Exception that occurred
	 */
	private void showErrorAlert(String header, Exception e) {
		Alert errorAlert = new Alert(AlertType.ERROR);
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		errorAlert.setHeaderText(header);
		errorAlert.setContentText(stringWriter.toString());
		errorAlert.showAndWait();
	}

	/**
	 * Changes the scene to the specified one.
	 *
	 * @param event The ActionEvent from the current scene
	 * @param name  The name of the scene to switch to
	 */
	private void switchToScene(ActionEvent event, String name) {
		try {
			URL resourceURL = getClass().getClassLoader()
					.getResource(name + ".fxml");
			URL stylesheetURL = getClass().getClassLoader()
					.getResource("styles.css");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(resourceURL);
			Parent root = fxmlLoader.load();
			root.getStylesheets()
					.add(stylesheetURL.toExternalForm());
			Stage stage = (Stage) ((Node) event.getSource())
					.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			showErrorAlert("Scene '" + name + "' not found", e);
		}
	}

	/**
	 * Populates the Dropdownmenu <em>cmbType</em>
	 */
	private void initialiseTypeDropdown() {
		OperatingSystem os = new OperatingSystem();
		String homeDir = os.getHomeDir();
		String snippetsDir = homeDir + "/latex";
		File directory = new File(snippetsDir);
		String[] files = directory.list();
		ArrayList<String> dropdownItems = new ArrayList<String>();
		for (String file : files) {
			if (new File(snippetsDir + File.separator + file)
					.isDirectory()) {
				dropdownItems.add(file);
			}
		}
		cmbType.getItems().setAll(dropdownItems);
	}

	/**
	 * Valtidates if an supposed integer is an actual integer
	 * <p>
	 * This is an grafical version of
	 * {@link de.thowl.automomousinstantdocumentsystem.Main#checkInt}
	 * </p>
	 * 
	 * @param inputInt Integer to validate
	 * @return Integervalue (if int)
	 */
	protected int validateInt(String inputInt) {
		int integer = 0;
		try {
			integer = Integer.parseInt(inputInt);
		} catch (NumberFormatException e) {
			showErrorAlert("NumberFormatException", e);
			return 0;
		}
		return integer;
	}

	/**
	 * Switches to the <em>Main</em> scene
	 * 
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnMainSceneClick(ActionEvent event) {
		switchToScene(event, "Main");
	}

	/**
	 * Switches to the <em>LaTeX</em> scene
	 * 
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnLatexSceneClick(ActionEvent event) {
		switchToScene(event, "Latex");
	}

	/**
	 * Switches to the <em>Database</em> scene
	 * 
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnDatabaseSceneClick(ActionEvent event) {
		switchToScene(event, "Database");
	}

	/**
	 * Called to initialize this gui controller after its root element has
	 * been completely processed by JavaFX.
	 * 
	 * @param location  The location used to resolve relative paths for the
	 *                  root object, or {@code null} if the location is not
	 *                  known.
	 * @param resources The resources used to localize the root object, or
	 *                  {@code null} if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initialiseTypeDropdown();
		} catch (NullPointerException e) {
			// Do nothing
		}
	}
}
