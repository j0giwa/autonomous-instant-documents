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

/**
 * @author Martin Boschmann
 */
package de.thowl.aids.gui.controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * This class is the Controller of the DatabaseScene in the GUI
 */
public class DatabaseScene extends Controller {

  @FXML
  private Button btnImportDatabase;

  @FXML
  private Button btnExportDatabase;

  /**
   * Construcktor for this controller
   * <p>
   * NOTE: Does nothing
   * </p>
   */
  public DatabaseScene() {
    // Nothing
  }

  @FXML
  private void btnImportDatabaseClick(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    File csvFile = fileChooser.showOpenDialog(null);
    System.out.println(csvFile);
  }

  @FXML
  private void btnExportDatabaseClick(ActionEvent event) {
  }

}
