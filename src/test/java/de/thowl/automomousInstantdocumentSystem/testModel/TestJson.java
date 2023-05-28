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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import de.thowl.automomousInstantdocumentSystem.model.Json;

public class TestJson {

	@Test
	void testGetValue() {
		Json json = new Json("./src/test/resources/test.json");
		assertEquals("poggers", json.getValue("test", "testGetValue"));
	}

	@Test
	void testsetValue() {
		Json json = new Json("./src/test/resources/test.json");
		String oldValue = json.getValue("test", "testSetValue");
		Random random = new Random(System.currentTimeMillis() / 1000L);
		json.setValue("test", "testSetValue", String.valueOf(random.nextInt()));
		String newValue = json.getValue("test", "testSetValue");
		assertNotEquals(oldValue, newValue);
	}
}
