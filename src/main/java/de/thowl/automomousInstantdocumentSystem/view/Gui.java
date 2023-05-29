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

package de.thowl.automomousInstantdocumentSystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the Gui of the Programm
 * Is just a loader for an fxml-file
 * The controling logic can be found in the <em>Conttroller</em> class
 * 
 * @author Jonas Schwind
 * @version 1.0.3
 * 
 * @see de.thowl.automomousInstantdocumentSystem.control.Controller
 */
public class Gui extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScene.fxml"));
		root.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());
		primaryStage.setTitle("Automomous Instantdocument System");
		primaryStage.setScene(new Scene(root, 760, 490));
		primaryStage.show();
	}
}
