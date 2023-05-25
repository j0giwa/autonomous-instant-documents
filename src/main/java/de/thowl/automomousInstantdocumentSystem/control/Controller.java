package de.thowl.automomousInstantdocumentSystem.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    // Sidebar
    @FXML
    private Button btnMainScene;
    @FXML
    private Button btnLatexScene;
    @FXML
    private Button btnDatabaseScene;

    // Button area
    @FXML
    private Label lblAmount;
    @FXML
    private Label lblChapters;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtChapters;
    @FXML
    private CheckBox chkShuffle;
    @FXML
    private Button btnGenerateDocument;

    // Multipurpose TextArea
    @FXML
    private TextArea txtMultipurposeTextArea;

    // Events
    @FXML
    protected void btnMainSceneClick() {
        // TODO: Add logic
    }

    @FXML
    protected void btnLatexSceneClick() {
        // TODO: Add logic
    }

    @FXML
    protected void btnDatabaseSceneClick() {
        // TODO: Add logic
    }

    /**
     * This method contains the logic behind the "Generate" Button on the GUI
     */
    @FXML
    protected void btnGenerateDocumentClick() {
        String type = "test"; // TODO: Add dropdown
        String destination = "/home/jogiwa/Downloads"; // TODO: add location in settings
        int amount = Integer.parseInt(txtAmount.getText());
        int chapters = Integer.parseInt(txtChapters.getText());
        boolean shuffle = chkShuffle.isArmed();
        Latex latex = new Latex();
        latex.generate(type, destination, amount, chapters, shuffle);
    }
}
