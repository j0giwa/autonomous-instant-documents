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

package de.thowl.automomousInstantdocumentSystem.model;

/**
 * This class contains Methods to read and write a JSon file
 * 
 * @author Jonas Schwind
 * @version 1.3,0
 */
public class Os {

	private String os;
	private String homeDir;

	/**
	 * Constructor for objects of this class
	 */
	public Os() {
		os = defineOs();
		homeDir = defineHomeDir();
	}

	/**
	 * Return the confighome for the programm. all configurationfiles an
	 * LatexSnippets reside here. (helpermethod to clean up the construcktor)
	 * 
	 * <p>
	 * The envirometvariable {@code AIDS_HOME} gets chaked first, is servves a
	 * manual override. If {@code AIDS_HOME} is not defined, a location will be
	 * chosen based on the OS.
	 * </p>
	 * 
	 * @return confighome location
	 */
	private String defineHomeDir() {
		String directory = System.getenv("AIDS_HOME");
		if (directory == null) {
			if (os.equals("Windows")) {
				directory = System.getenv("appdata");
			} else if (os.equals("UNIX")) {
				directory = System.getenv("XDG_CONFIG_HOME");
			} else if (os.equals("mac")) {
				directory = System.getenv("Library");
			}
			directory += "/aids";
		}
		return directory;
	}

	/**
	 * Return the used operationg system (helpermethod to clean up the construcktor)
	 * 
	 * <p>
	 * For sake of simplicity, the os name will be abstracted:
	 * {@code UNIX} for UNIX based operationg,
	 * {@code Windows} for Mircrosoft operationg systems,
	 * and {@code Mac} for Apple macs.
	 * </p>
	 * 
	 * @return used OS
	 */
	private String defineOs() {
		String osType = System.getProperty("os.name");
		switch (osType) {
			case "BSD":
			case "Linux":
				osType = "UNIX";
				break;
			case "Mac OS X":
				osType = "Mac";
				break;
			case "Windows 7":
			case "Windows 10":
			case "Windows 11":
				osType = "Windows";
				break;
		}
		return osType;
	}

	/**
	 * This Method gets the name of the current OS.
	 * 
	 * @return OS name
	 */
	public String getOS() {
		return os;
	}

	/**
	 * This Method gets the path to the config home.
	 * 
	 * @return OS name
	 */
	public String getHomeDir() {
		return homeDir;
	}
}
