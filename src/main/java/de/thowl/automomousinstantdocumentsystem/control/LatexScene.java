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
