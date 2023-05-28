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

import org.junit.jupiter.api.Test;

import de.thowl.automomousInstantdocumentSystem.model.LatexSnippet;

public class TestLatexSnippet {

	@Test
	void testGetFilepath() {
		String currentDir = System.getProperty("user.dir");
		String filepath = currentDir + "/src/test/resources/latex/test/header.tex";
		LatexSnippet snippet = new LatexSnippet(filepath);
		assertEquals(filepath, snippet.getFilepath());
	}

	@Test
	void testSetFilepath() {
		String currentDir = System.getProperty("user.dir");
		String filepath = currentDir + "/src/test/resources/latex/test/header.tex";
		LatexSnippet snippet = new LatexSnippet(filepath);
		String newFilepath = currentDir + "/src/test/resources/latex/test/header1.tex";
		String expected = newFilepath;
		snippet.setFilepath(newFilepath);
		String actual = snippet.getFilepath();
		assertEquals(expected, actual);
	}

	@Test
	void testGetFilecontent() {
		String currentDir = System.getProperty("user.dir");
		String filepath = currentDir + "/src/test/resources/latex/test/footer.tex";
		LatexSnippet snippet = new LatexSnippet(filepath);
		String expected = "\\end{document}";
		String actual = snippet.getFilecontent();
		assertEquals(expected, actual);
	}

	@Test
	void testSetFilecontent() {
		String currentDir = System.getProperty("user.dir");
		String filepath = currentDir + "/src/test/resources/latex/test/footer.tex";
		LatexSnippet snippet = new LatexSnippet(filepath);
		String newContent = "\\end{file}";
		String expected = newContent;
		snippet.setFilecontent(newContent);
		String actual = snippet.getFilecontent();
		assertEquals(expected, actual);
	}
}
