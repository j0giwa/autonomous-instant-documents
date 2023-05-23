/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind
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

package de.thowl.automomousInstantdocumentSystem;

import de.thowl.automomousInstantdocumentSystem.control.Latex;
import de.thowl.automomousInstantdocumentSystem.exceptions.LatexNotInstalledException;
import de.thowl.automomousInstantdocumentSystem.view.MainView;
import javafx.application.Application;

/**
 * This is the Main Class of the Program
 * 
 * @version 0.2.5
 * @author Jonas Schwind
 */
public class Main {

	private static final int EXIT_SUCCESS = 0;
	private static final int EXIT_ERROR = 1;

	private static String OS = System.getProperty("os.name");
	private static final String version = "Autonomous-Instantdocument-System V0.1.2-SNAPSHOT";

	private static String documentType = null;
	private static String documentDestination = null;
	private static int documentAmount = 0;

	public static void main(String[] args) {
		// When no arguments are passed the program runs in a graphical mode
		if (args.length == 0) {
			Application.launch(MainView.class, args);
			System.exit(EXIT_SUCCESS);
		}
		handleArgs(args);
		// early return on incomplete values
		if (documentType == null || documentDestination == null || documentAmount <= 0) {
			System.out.println("Not enough arguments");
			System.exit(EXIT_ERROR);
		}
		generate(documentType, documentDestination, documentAmount);
		System.exit(EXIT_SUCCESS);
	}

	/**
	 * This method handles the command line arguments
	 * 
	 * @param args
	 */
	private static void handleArgs(String[] args) {
		int argc = args.length;
		String arg = null;
		String previousArg = null;
		for (int i = 0; i < argc; i++) {
			arg = args[i];
			if (arg.startsWith("--")) {
				arg = arg.substring(2);
			} else if (arg.startsWith("-")) {
				arg = arg.substring(1);
			}
			// Parameterized arguments
			if (previousArg != null) {
				handleParameterisedArgs(arg, previousArg);
				previousArg = null;
			}
			// Standard arguments
			switch (arg) {
				case "version":
				case "v":
					printVersion();
					System.exit(EXIT_SUCCESS);
					break;
				case "help":
				case "h":
					printHelp();
					System.exit(EXIT_SUCCESS);
					break;
				default:
					previousArg = arg;
					break;
			}
		}
	}

	/**
	 * This method handles the command line arguments with additional parameters
	 * 
	 * @param args
	 */
	private static void handleParameterisedArgs(String arg, String previousArg) {
		switch (previousArg) {
			case "type":
			case "t":
				documentType = arg;
				break;
			case "destination":
			case "d":
				documentDestination = arg;
				break;
			case "amount":
			case "a":
				documentAmount = Integer.parseInt(arg);
				break;
		}
	}

	/**
	 * This method print the version information to console
	 */
	private static void printVersion() {
		System.out.println(version);
	}

	/**
	 * This Method prints a simple help document to console
	 */
	private static void printHelp() {
		// TODO Add help once all features are implemented
		System.out.println("NO HELP (yet...)");
	}

	/**
	 * This Method generates Documents within the passed parameters TODO: amount
	 * does not do anything yet
	 * 
	 * @param type
	 * @param destination
	 * @param amount
	 */
	private static void generate(String type, String destination, int amount) {
		Latex latex = new Latex();
		latex.concat(type, 2, false);
		try {
			latex.compile(type, destination);
		} catch (LatexNotInstalledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This Method gets the name of the current OS (Just returns Windows or Unix to
	 * simplify things)
	 * 
	 * @return OS name
	 */
	public static String getOS() {
		String OperatingSystemType = null;
		switch (OS) {
			case "Linux":
				// TODO: find out actual name of OS
			case "MacOS":
				OperatingSystemType = "UNIX";
				break;
			case "Windows 10":
			case "Windows 11":
				OperatingSystemType = "Windows";
				break;
			default:
				break;
		}
		return OperatingSystemType;
	}
}
