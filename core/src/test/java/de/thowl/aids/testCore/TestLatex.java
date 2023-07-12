
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

package de.thowl.aids.testCore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.aids.core.Latex;

public class TestLatex {

	private static final String SNIPPETS_DIR = "./src/test/resources/latex/test/chapters";
	private static final String TEMP_DIR = "./temp";
	private static final String TEST_TYPE = "test";
	private static final int TEST_CHAPTERS = 2;

	private Latex latex;

	@BeforeEach
	public void setUp() {
		createDirectories();
		latex = new Latex();
	}

	/**
	 * Tests the {@link Latex#gatherSnippets(String, int, boolean)} method
	 * to ensure it correctly gathers snippets from the specified directory
	 * and randomizes the order when requested.
	 */
	@Test
	public void test_gatherSnippets_ShouldGatherSnippetsAndRandomizeOrder() {
    String type = TEST_TYPE;
		int chapters = TEST_CHAPTERS;
		boolean randomize = true;
		latex.gatherSnippets(type, chapters, randomize);
		assertNotNull(latex.getHeader());
		assertEquals(chapters, latex.getSnippets().size());
		assertNotNull(latex.getFooter());
	}

	/**
	 * Tests the {@link Latex#concat(String)} method to ensure it correctly
	 * concatenates the snippets and generates the source file.
	 */
	@Test
	public void test_concat_ShouldConcatenateSourceFile() {
		String type = TEST_TYPE;
		String workingDir = TEMP_DIR + File.separator + "concatTest";
		latex.gatherSnippets(type, TEST_CHAPTERS, false);
		latex.concat(type, workingDir);
		File sourceFile = new File(workingDir + File.separator + type + ".tex");
		assertTrue(sourceFile.exists());
	}

	/**
	 * Tests the {@link Latex#compile(String, String)} method to ensure it
	 * correctly compiles the LaTeX document from the source file and
	 * generates the output in the specified destination.
	 */
	@Test
	public void test_compile_ShouldCompile() {
		String type = TEST_TYPE;
		String workingDir = TEMP_DIR + File.separator + "singleCompileTest";
		latex.gatherSnippets(type, TEST_CHAPTERS, false);
		latex.concat(type, workingDir);
		latex.compile(type, workingDir);
		File outputFile = new File(workingDir + File.separator + type + ".pdf");
		assertTrue(outputFile.exists());
	}

	/**
	 * Tests the {@link Latex#generate(String, String, int, int, boolean)}
	 * method to ensure it correctly generates the specified number of
	 * documents with shuffled snippets.
	 */
	@Test
	public void test_generate_ShouldGenerateDocuments() {
		String type = TEST_TYPE;
		String destination = TEMP_DIR;
		int amount = 3;
		int chapters = TEST_CHAPTERS;
		boolean shuffle = true;
		latex.generate(type, destination, amount, chapters, shuffle);
		for (int i = 1; i <= amount; i++) {
			File documentFolder = new File(
					destination + File.separator + "foldername" + i);
			File outputFile = new File(documentFolder.getPath()
					+ File.separator + type + ".pdf");
			assertTrue(outputFile.exists());
		}
	}

	private void createDirectories() {
		try {
			Files.createDirectories(Paths.get(SNIPPETS_DIR));
			Files.createDirectories(Paths.get(TEMP_DIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
