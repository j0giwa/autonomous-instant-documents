package de.thowl.aids.testGui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.testfx.api.FxRobotException;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TestMainScene extends TestFxBase {

	final String COMBOBOX_ID = "#cmbType";
	final String CHAPTER_ID = "#txtChapters";
	final String AMOUNT_ID = "#txtAmount";
	final String BUTTON_ID = "#btnGenerateDocument";

	// @Test
	public void test_GuiInitialization() {
		ComboBox type = lookup(COMBOBOX_ID).query();
		TextField txtAmount = lookup(AMOUNT_ID).query();
		TextField txtChapters = lookup(CHAPTER_ID).query();
		Button btnGenerateDocument = lookup(BUTTON_ID).query();
		assertNotNull(txtAmount);
		assertNotNull(type);
		assertNotNull(txtChapters);
		assertNotNull(btnGenerateDocument);
	}

	// @Test
	public void test_clickOnBogusElement() {
		assertThrows(FxRobotException.class, () -> clickOn("#iDoNotExist"));
	}
}
