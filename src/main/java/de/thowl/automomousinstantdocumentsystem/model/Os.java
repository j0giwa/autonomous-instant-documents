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

package de.thowl.automomousinstantdocumentsystem.model;

/**
 * This class contains Methods to read and write a JSon file
 * 
 * @author Jonas Schwind
 * @version 1.3,0
 */
public class Os {

	private String operatingSystem;
	private String homeDir;

	/**
	 * Constructs a new instance of the Os class.
	 */
	public Os() {
		operatingSystem = defineOperatingSystem();
		homeDir = defineHomeDir();
	}

	/**
	 * Returns the confighome where all configfiles and snippets reside.
	 * This is a helper method used to clean up the constructor.
	 * <p>
	 * The environment variable {@code AIDS_HOME} is checked first, serving
	 * as a manual override. If {@code AIDS_HOME} is not defined, a location
	 * will be chosen based on the operating system.
	 * </p>
	 * 
	 * @return The confighome location
	 */
	private String defineHomeDir() {
		String dir = System.getenv("AIDS_HOME");
		if (dir != null) {
			return dir + "/aids";
		}
		switch (operatingSystem) {
		case "Windows":
			dir = System.getenv("APPDATA");
			break;
		case "UNIX":
			dir = System.getenv("XDG_CONFIG_HOME");
			// Fallback when XDG is unconfigured/unsupported
			if (dir == null)
				dir = System.getProperty("user.home")
						+ "/.config";
			break;
		case "Mac":
			dir = "~/Library/Application Support/";
			break;
		default:
			break;
		}
		return dir + "/aids";
	}

	/**
	 * Returns the operating system used by the program. This is a helper
	 * method used to simplify the constructor.
	 * <p>
	 * To provide simplicity, the OS name will be abstracted as follows:
	 * <ul>
	 * <li>{@code UNIX} for UNIX-based operating systems</li>
	 * <li>{@code Windows} for Microsoft operating systems</li>
	 * <li>{@code Mac} for Apple Macs</li>
	 * </p>
	 *
	 * @return The used OS
	 */
	private String defineOperatingSystem() {
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
		default:
			break;
		}
		return osType;
	}

	/**
	 * Return the name of the current OS.
	 * 
	 * @return OS name
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Retun the path to the config home.
	 * 
	 * @return OS name
	 */
	public String getHomeDir() {
		return homeDir;
	}
}
