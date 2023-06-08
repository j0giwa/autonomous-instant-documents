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

package de.thowl.automomousInstantdocumentSystem.testModel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.automomousinstantdocumentsystem.model.LatexSnippet;

public class TestLatexSnippet {

	private static final String FILE_PATH = "src/test/resources/latex/testSnipptet.tex";
	private static final String FILE_CONTENT = "\\documentclass{article}";

	private LatexSnippet latexSnippet;

	@BeforeEach
	public void setUp() {
		// Create a test .tex file
		createTestTexFile();
		// Initialize the LatexSnippet object with the test .tex file
		latexSnippet = new LatexSnippet(FILE_PATH);
	}

	/**
	 * Tests the {@link LatexSnippet#getFilePath()} method to ensure it
	 * returns the correct file path.
	 */
	@Test
	public void test_getFilePath_ShouldReturnFilePath() {
		String expectedFilePath = FILE_PATH;
		String filePath = latexSnippet.getFilePath();
		assertEquals(expectedFilePath, filePath);
	}

	/**
	 * Tests the {@link LatexSnippet#getFileContent()} method to ensure it
	 * returns the correct file content.
	 */
	@Test
	public void test_getFileContent_ShouldReturnFileContent() {
		String expectedFileContent = FILE_CONTENT;
		String fileContent = latexSnippet.getFileContent();
		assertEquals(expectedFileContent, fileContent);
	}

	/**
	 * Tests the {@link LatexSnippet#setFilepath(String)} method to ensure
	 * it correctly overwrites the file path.
	 */
	@Test
	public void test_setFilepath_ShouldOverwriteFilePath() {
		String newFilePath = "new_test.tex";
		latexSnippet.setFilepath(newFilePath);
		assertEquals(newFilePath, latexSnippet.getFilePath());
	}

	/**
	 * Tests the {@link LatexSnippet#setFilecontent(String)} method to
	 * ensure it correctly overwrites the file content.
	 */
	@Test
	public void test_setFilecontent_ShouldOverwriteFileContent() {
		String newFileContent = "\\documentclass{report}";
		latexSnippet.setFilecontent(newFileContent);
		assertEquals(newFileContent, latexSnippet.getFileContent());
	}

	private void createTestTexFile() {
		try {
			Files.write(Paths.get(FILE_PATH), FILE_CONTENT
					.getBytes(StandardCharsets.UTF_8));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
