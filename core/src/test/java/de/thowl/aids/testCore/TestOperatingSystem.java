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
 :w
 */

package de.thowl.aids.testCore;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.aids.core.LatexNotInstalledException;
import de.thowl.aids.core.OperatingSystem;

public class TestOperatingSystem {

	private OperatingSystem operatingSystem;

	@BeforeEach
	public void setUp() {
		operatingSystem = new OperatingSystem();
	}

	/**
	 * Tests the {@link OperatingSystem#getHomeDir()} method to ensure it
	 * returns a nonempty file path.
	 */
	@Test
	public void test_getHomeDir_shouldReturnNonEmptyString() {
		String homeDir = operatingSystem.getHomeDir();
		assertFalse(homeDir.isEmpty());
	}

	/**
	 * Tests the {@link OperatingSystem#getOS()} method to ensure it returns
	 * one of the following values:
	 * <ul>
	 * <li>{@code UNIX} for UNIX-based operating systems</li>
	 * <li>{@code Windows} for Microsoft operating systems</li>
	 * <li>{@code Mac} for Apple Macs</li>
	 * </ul>
	 */
	@Test
	public void test_getOS_shouldReturnNonValidOs() {
		String os = operatingSystem.getOperatingSystem();
		assertTrue(os.equals("UNIX") || os.equals("Windows")
				|| os.equals("Mac"));
	}

	/**
	 * Tests the {@link OperatingSystem#getPdflatexLocation()} method to
	 * ensure it correctly returns the location of the pdflatex binary based
	 * on the current operating system.
	 */
	@Test
	public void test_getPdflatexLocation_ShouldReturnPdflatexLocation() {
		String pdflatex;
		try {
			pdflatex = operatingSystem.getPdflatexPath();
			assertNotNull(pdflatex);
		} catch (LatexNotInstalledException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}
