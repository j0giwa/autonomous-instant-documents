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

package de.thowl.aids.core;

import java.io.File;

/**
 * This class contains Methods to read and write a JSon file
 * 
 * @author Jonas Schwind
 * @version 1.3,0
 */
public class OperatingSystem {

	private String operatingSystem;
	private String homeDir;
	private String tempDir;
	private String pdflatexPath;

	/**
	 * Constructs a new instance of the OperatingSystem class.
	 */
	public OperatingSystem() {
		String osName = System.getProperty("os.name");
		homeDir = System.getenv("AIDS_HOME");
		switch (osName) {
			case "BSD":
			case "Linux":
				operatingSystem = "UNIX";
				homeDir = System.getenv("XDG_CONFIG_HOME");
				// Fallback when XDG is unconfigured/unsupported
				if (homeDir == null)
					homeDir = System.getProperty("user.home")
							+ "/.config";
				tempDir = "/tmp";
				pdflatexPath = "/usr/bin/pdflatex";
				break;
			case "Mac OS X":
				operatingSystem = "Mac";
				homeDir = System.getProperty("user.home") +
						"/Library/Application Support/";
				tempDir = "/tmp";
				pdflatexPath = "/usr/bin/pdflatex";
				break;
			case "Windows 7":
			case "Windows 10":
			case "Windows 11":
				operatingSystem = "Windows";
				homeDir = System.getenv("APPDATA");
				tempDir = "%temp%";
				pdflatexPath = "C:\\texlive\\2023\\bin\\windows\\pdflatex.exe";
				break;
			default:
				break;
		}
		homeDir += "/aids";
	}

	/**
	 * Returns the operating system used by the program. This is a helper
	 * method used to simplify the constructor.
	 * 
	 * <p>
	 * To provide simplicity, the OS name will be abstracted as follows:
	 * </p>
	 * 
	 * <ul>
	 * <li>{@code UNIX} for UNIX-based operating systems</li>
	 * <li>{@code Windows} for Microsoft operating systems</li>
	 * <li>{@code Mac} for Apple Macs</li>
	 * </ul>
	 * 
	 * @return The used OS
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Returns the confighome where all configfiles and snippets reside.
	 * 
	 * <p>
	 * The environment variable {@code AIDS_HOME} is checked first, serving
	 * as a manual override. If {@code AIDS_HOME} is not defined, a location
	 * will be chosen based on the operating system.
	 * </p>
	 * 
	 * @return The confighome location
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * Returns the location of the pdflatex binary current operating system
	 * <p>
	 * Only the default instalation locations are checked. If the binary is
	 * stored anywhre else, it can't be found.
	 * </p>
	 * 
	 * @return location of the pdflatex binary
	 * @throws LatexNotInstalledException if the compiler cannot be found
	 *                                    anywhere on the system.
	 */
	public String getPdflatexPath() throws LatexNotInstalledException {
		if (!new File(pdflatexPath).exists())
			throw new LatexNotInstalledException(
					"pdflatex not found");
		return pdflatexPath;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public void setPdflatexPath(String pdflatexPath) {
		this.pdflatexPath = pdflatexPath;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

}
