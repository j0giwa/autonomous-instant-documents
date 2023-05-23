package de.thowl.automomousInstantdocumentSystem.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button btnTestButton;

    @FXML
    public void btnTestButtonClick() {
        System.out.println("HI");
        btnTestButton.setText("DONT TOUCH ME");
    }
}
