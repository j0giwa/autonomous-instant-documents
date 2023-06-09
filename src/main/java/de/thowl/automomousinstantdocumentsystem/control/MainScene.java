package de.thowl.automomousinstantdocumentsystem.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainScene extends MasterController {

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
	 * This method contains the logic behind the "Generate" Button Executed
	 * when button is pressed
	 * 
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnGenerateDocumentClick(ActionEvent event) {
		final String type = super.cmbType.getSelectionModel()
				.getSelectedItem();
		// TODO: add location in settings
		final String destination = "/home/jogiwa/Downloads";
		final int amount = super.validateInt(txtAmount.getText());
		final int chapters = super.validateInt(txtChapters.getText());
		final boolean shuffle = chkShuffle.isArmed();
		if (type == null || amount <= 0 || chapters <= 0) {
			return;
		}
		Thread generation = new Thread(() -> {
			Latex latex = new Latex();
			appendToTextArea("[ INFO ]  Generating " + amount
					+ " Document(s) of type '" + type
					+ "' with " + chapters
					+ " chapters...\n");
			logger.info("Generating {} Document(s) of type '{}' with {} chapters.",
					amount, type, chapters);
			latex.generate(type, destination, amount, chapters,
					shuffle);
			appendToTextArea("[ INFO ]  Generation complete\n");
			logger.info("Generation complete");
		});
		generation.start();
	}
}
