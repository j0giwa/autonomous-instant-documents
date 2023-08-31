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

	// @Test TODO: could get this to work, deactivated for now.
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

	// @Test TODO: could get this to work, deactivated for now.
	public void test_clickOnBogusElement() {
		assertThrows(FxRobotException.class, () -> clickOn("#iDoNotExist"));
	}
}
