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

package de.thowl.aids.testCore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.aids.core.Json;
import de.thowl.aids.core.OperatingSystem;

public class TestJson {

	private String file;
	private Json json;

	@BeforeEach
	public void setUp() {
		OperatingSystem os = new OperatingSystem();
		file = os.getTempDir() + File.separator + "aidsTest" +
				File.separator + "test.json";
		createTestJsonFile();
		json = new Json(file);
	}

	/**
	 * Tests the {@link Json#getValue(String, String)} method to ensure it
	 * returns the correct value from the JSON object when the object and
	 * key exist.
	 */
	@Test
	public void test_getValue_ShouldReturnCorrectValue_WhenObjectAndKeyExist() {
		String object = "person";
		String key = "name";
		String expectedValue = "John Doe";
		String value = json.getValue(object, key);
		assertEquals(expectedValue, value);
	}

	/**
	 * Tests the {@link Json#setValue(String, String, String)} method to
	 * ensure it changes the value in the JSON object when the object and
	 * key exist.
	 */
	@Test
	public void test_setValue_ShouldChangeValue_WhenObjectAndKeyExist() {
		String object = "person";
		String key = "age";
		String expectedValue = "30";
		json.setValue(object, key, expectedValue);
		String updatedValue = json.getValue(object, key);
		assertEquals(expectedValue, updatedValue);
	}

	private void createTestJsonFile() {
		String content = "{ \"person\": { \"name\": \"John Doe\" } }";
		try (FileWriter fileWriter = new FileWriter(file)) {
			fileWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
