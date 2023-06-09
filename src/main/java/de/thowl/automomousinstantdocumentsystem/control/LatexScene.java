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

import de.thowl.automomousinstantdocumentsystem.model.Json;
import de.thowl.automomousinstantdocumentsystem.model.OperatingSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LatexScene extends MasterController {

	@FXML
	private ComboBox<String> cmbType;
	@FXML
	private TextField txtFileName;
	@FXML
	private Button btnOpenEditor;

	@FXML
	private void btnOpenEditorClick() {
		OperatingSystem os = new OperatingSystem();
		String settingsFile = os.getHomeDir() + File.separator
				+ "settings.json";
		Json settings = new Json(settingsFile);
		String editor = settings.getValue("settings", "editor");
		String file = os.getHomeDir() + File.separator + "latex"
				+ File.separator
				+ cmbType.getSelectionModel().getSelectedItem()
				+ File.separator + txtFileName.getText();
		String[] editorCommand = { editor, file };
		try {
			Runtime.getRuntime().exec(editorCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
