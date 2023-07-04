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

package de.thowl.aids.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.thowl.aids.core.Json;
import de.thowl.aids.core.OperatingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * This class is the Controller of the LatexScene in the GUI
 */
public class LatexScene extends MasterController {

	private static final Logger logger = LogManager
			.getLogger(LatexScene.class);

	private String latexdir;
	private String settingsFile;

	@FXML
	private TreeView<String> treeFileTree;
	@FXML
	private TextField txtFileName;
	@FXML
	private Button btnOpenEditor;

	/**
	 * Construcktor for this controller
	 * <p>
	 * NOTE: Does nothing
	 * </p>
	 */
	public LatexScene() {
		// Nothing
	}

	/**
	 * Retrieves the selected item's path in the TreeView.
	 *
	 * @param item The selected TreeItem in the TreeView.
	 * @return The path of the selected item.
	 */
	public String getSelectedTree(TreeItem<String> item) {
		if (item == null)
			return "";
		StringBuilder path = new StringBuilder(item.getValue());
		TreeItem<String> parentItem = item.getParent();
		while (parentItem != null) {
			path.insert(0, File.separator).insert(0,
					parentItem.getValue());
			parentItem = parentItem.getParent();
		}
		return path.toString();
	}

	/**
	 * Called to initialize this gui controller after its root element has
	 * been completely processed by JavaFX.
	 *
	 * @param location  The location used to resolve relative paths for the
	 *                  root object, or {@code null} if the location is not
	 *                  known.
	 * @param resources The resources used to localize the root object, or
	 *                  {@code null} if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		OperatingSystem os = new OperatingSystem();
		latexdir = os.getHomeDir() + File.separator + "latex";
		settingsFile = os.getHomeDir() + File.separator
				+ "settings.json";
		super.initialiseTypeDropdown();
	}

	/**
	 * Creates a TreeItem for the specified file, including its children (if
	 * it is a directory).
	 *
	 * @param file The file or directory.
	 * @return The TreeItem representing the file or directory.
	 */
	private TreeItem<String> createTreeItem(File file) {
		TreeItem<String> item = new TreeItem<>(file.getName());
		if (!file.isDirectory())
			return item;
		File[] files = file.listFiles();
		if (files != null) {
			for (File childFile : files) {
				TreeItem<String> child = createTreeItem(
						childFile);
				item.getChildren().add(child);
			}
		}
		return item;
	}

	/**
	 * Recursively expands TreeItems in the TreeView.
	 *
	 * @param rootItem The root TreeItem.
	 */
	private void expandItem(TreeItem<String> item) {
		if (item == null)
			return;
		// Recursion BS has to stay, items could resemble a filetree
		item.getChildren().forEach(this::expandItem);
		item.setExpanded(true);
	}

	/**
	 * Populates the TreeView with the file structure of the specified
	 * directory.
	 *
	 * @param directory The directory path.
	 */
	private void populateTreeView(String directory) {
		File rootDirectory = new File(directory);
		TreeItem<String> rootItem = createTreeItem(rootDirectory);
		treeFileTree.setRoot(rootItem);
		treeFileTree.setShowRoot(true);
		expandItem(rootItem);
	}

	/**
	 * Eventhandeler for the "type" combobox. Executed when an item is
	 * selected.
	 *
	 * @param event ActionEvent of the combobox
	 */
	@FXML
	private void cmbTypeSelection(ActionEvent event) {
		String item = cmbType.getSelectionModel().getSelectedItem();
		String directory = latexdir + File.separator + item;
		populateTreeView(directory);
	}

	/**
	 * Eventhandeler for the "Open in Editor" Button. Executed when the
	 * Button is clicked.
	 *
	 * @param event ActionEvent of the Button
	 */
	@FXML
	private void btnOpenEditorClick(ActionEvent event) {
		Json settings = new Json(settingsFile);
		String editor = settings.getValue("settings", "editor");
		TreeItem<String> item = treeFileTree.getSelectionModel()
				.getSelectedItem();
		if (item == null)
			return;
		String file = latexdir + File.separator + getSelectedTree(item);
		String[] editorCommand = { editor, file };
		try {
			logger.info("Opening file '{}' in {}", file, editor);
			Runtime.getRuntime().exec(editorCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
