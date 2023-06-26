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

package de.thowl.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * This class is a representation of a .tex file. It contains the Path and their
 * respective content of the file
 * 
 * @version 1.2.0
 * @author Jonas Schwwind
 */
public class LatexSnippet {

	private File file;
	private String filePath;
	private String fileContent;

	/**
	 * Constructor for objects of this class
	 * 
	 * @param filepath location of the .tex file
	 */
	public LatexSnippet(String filepath) {
		this.filePath = filepath;
		file = new File(this.filePath);
		try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			fileContent = new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the location of the file
	 * 
	 * @return path to the file
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Get the content of the file
	 * 
	 * @return path of the file
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * Overwrite the location of the file
	 * 
	 * @param filePath new path to the file
	 */
	public void setFilepath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Overwrite the content of the file
	 * 
	 * @param fileContent content of the file
	 */
	public void setFilecontent(String fileContent) {
		this.fileContent = fileContent;
	}
}
