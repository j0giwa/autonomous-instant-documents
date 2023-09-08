/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind, Martin Boschmann
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

package de.thowl.aids.gui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.thowl.aids.core.Json;
import de.thowl.aids.core.Latex;
import de.thowl.aids.core.OperatingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This class is the Controller of the MainScene in the GUI
 * @author Jonas Schwind
 */
public class MainScene extends Controller {

	private static final Logger logger = LogManager
			.getLogger(MainScene.class);

	@FXML
	private TextField txtAmount;
	@FXML
	private TextField txtChapters;
	@FXML
	private TextField txtFileName;
	@FXML
	private CheckBox chkShuffle;
	@FXML
	private Button btnGenerateDocument;
	@FXML
	private Button btnOpenEditor;

	@FXML
	private TextArea txtMultipurposeTextArea;

	/**
	 * Construcktor for this controller
	 * <p>
	 * NOTE: Does nothing
	 * </p>
	 */
	public MainScene() {

	}

	/**
	 * Appends a String to the TextArea <em>txtMultipurposeTextArea</em>
	 * 
	 * @param newString String to append
	 */
	private void appendToTextArea(String newString) {
		StringBuilder areaContent = new StringBuilder();
		areaContent.append(txtMultipurposeTextArea.getText());
		areaContent.append(newString);
		txtMultipurposeTextArea.setText(areaContent.toString());
	}

	/**
	 * Eventhandeler for the "Generate" Button. Executed when button is
	 * pressed
	 * 
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnGenerateDocumentClick(ActionEvent event) {
		OperatingSystem os = new OperatingSystem();
		Json json = new Json(os.getHomeDir() + File.separator + "settings.json");
		final String type = super.cmbType.getSelectionModel()
				.getSelectedItem();
		final String destination = json.getValue("settings", "defaultDestination");
		final int amount = super.validateInt(txtAmount.getText());
		final int chapters = super.validateInt(txtChapters.getText());
		final boolean shuffle = chkShuffle.isArmed();
		// NOTE: "Type" is a placeholder when no type is selceted.
		if (type.equals("Type")) {
			showErrorAlert("Invalid", "No type selected", null);
			return;
		}
		Thread generation = new Thread(() -> {
			Latex latex = new Latex();
			appendToTextArea("[ INFO ]  Generating " + amount + " '"
					+ type + "'-Document(s) with "
					+ chapters + " chapters...\n");
			logger.info("Generating {} '{}'-Documents with {} chapters.",
					amount, type, chapters);
			latex.generate(type, destination, amount, chapters,
					shuffle);
			appendToTextArea("[ INFO ]  Generation complete\n");
			logger.info("Generation complete");
		});
		generation.start();
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
		super.initialiseTypeDropdown();
	}
}
