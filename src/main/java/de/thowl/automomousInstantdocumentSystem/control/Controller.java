package de.thowl.automomousInstantdocumentSystem.control;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.thowl.automomousInstantdocumentSystem.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This Class is the controller of the class <emph>Gui</emph>
 * 
 * @author Jonas Schwind
 * @version 0.1.2
 * @see de.thowl.automomousInstantdocumentSystem.view.Gui
 */
public class Controller implements Initializable {

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
    private CheckBox chkShuffle;
    @FXML
    private Button btnGenerateDocument;

    // Multipurpose TextArea
    @FXML
    private TextArea txtMultipurposeTextArea;

    /**
     * This Methodd populates the Dropdownmenu
     */
    private void initialiseDropdown() {
        String OS = Main.getOS();
        String snippetsDir = null;
        // determine OS specific file path
        if (OS.equals("Windows")) {
            snippetsDir = System.getenv("appdata") + "/aids/latex/";
        } else if (OS.equals("UNIX")) {
            snippetsDir = System.getenv("XDG_CONFIG_HOME") + "/aids/latex/";
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate Dropdown menu
        initialiseDropdown();
    }

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
     * Exected when button is pressed
     */
    @FXML
    protected void btnGenerateDocumentClick() {
        Alert errorAlert = new Alert(AlertType.ERROR);
        // Defining Document related Variables
        final String documentType = cmbType.getSelectionModel().getSelectedItem();
        final String documentDestination = "/home/jogiwa/Downloads"; // TODO: add location in settings
        int documentAmountInput = 0;
        int documentChaptersInput = 0;
        try {
            documentAmountInput = Integer.parseInt(txtAmount.getText());
            documentChaptersInput = Integer.parseInt(txtChapters.getText());
        } catch (NumberFormatException e) {
            // Display
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            errorAlert.setHeaderText("NumberFormatException");
            errorAlert.setContentText(sw.toString());
            errorAlert.showAndWait();
            return;
        }
        final int documentAmount = documentAmountInput;
        final int documentChapters = documentChaptersInput;
        final boolean shuffle = chkShuffle.isArmed();
        // Check Vars, Abort on invalid inputs
        if (documentType == null) {
            errorAlert.setHeaderText("Invalid Inputs");
            errorAlert.setContentText("Please specify a document type");
            errorAlert.showAndWait();
            return;
        }
        if (documentAmount <= 0 || documentChapters <= 0) {
            errorAlert.setHeaderText("Invalid Inputs");
            errorAlert.setContentText("Please specify a value higher than 0");
            errorAlert.showAndWait();
            return;
        }
        // Generate Latex Documents
        Thread t = new Thread(() -> {
            Latex latex = new Latex();
            latex.generate(documentType, documentDestination, documentAmount, documentChapters, shuffle);
            Platform.runLater(() -> {
                appendToMultiPurposeTextArea("[ INFO ] Starting new LaTeX instance\n");
                appendToMultiPurposeTextArea("[ INFO ]  Generating " + documentAmount +
                        " Documents with " + documentChapters + " Chapters...\n");
                appendToMultiPurposeTextArea("[ INFO ]  done!\n");
            });
        });
        t.start();
    }

    public void appendToMultiPurposeTextArea(String newString) {
        StringBuilder areaContent = new StringBuilder();
        areaContent.append(txtMultipurposeTextArea.getText());
        areaContent.append(newString);
        txtMultipurposeTextArea.setText(areaContent.toString());
    }
}
