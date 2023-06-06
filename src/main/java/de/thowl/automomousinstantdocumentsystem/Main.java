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

package de.thowl.automomousinstantdocumentsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.thowl.automomousinstantdocumentsystem.control.Latex;
import de.thowl.automomousinstantdocumentsystem.view.Gui;
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

	private static final String VERSION = "version: 0.8.5";

	private static final Logger logger = LogManager.getLogger(Main.class);

	private static String type = null;
	private static String destination = null;
	private static int amount = 0;
	private static int chapters = 0;
	private static boolean shuffle = true;

	/**
	 * This method starts the programm (as we all know)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			Application.launch(Gui.class, args);
			System.exit(EXIT_SUCCESS);
		}
		handleArgs(args);
		if (type == null || destination == null || amount <= 0) {
			logger.error("Not enough arguments");
			System.exit(EXIT_ERROR);
		}
		Latex latex = new Latex();
		latex.generate(type, destination, amount, chapters, shuffle);
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
		String prevArg = null;
		for (int i = 0; i < argc; i++) {
			arg = args[i];
			if (arg.startsWith("--"))
				arg = arg.substring(2);
			if (arg.startsWith("-"))
				arg = arg.substring(1);
			if (prevArg != null) {
				handleParamArgs(arg, prevArg);
				prevArg = null;
			}
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
			case "noshuffle":
			case "ns":
				shuffle = false;
				break;
			default:
				prevArg = arg;
				break;
			}
		}
	}

	/**
	 * This method handles the command line arguments with additional
	 * parameters
	 * 
	 * @param args
	 */
	private static void handleParamArgs(String arg, String prevArg) {
		switch (prevArg) {
		case "type":
		case "t":
			type = arg;
			break;
		case "destination":
		case "d":
			destination = arg;
			break;
		case "amount":
		case "a":
			amount = checkInt(arg);
			break;
		case "chapters":
		case "c":
			chapters = checkInt(arg);
			break;
		default:
			logger.error("Unknown arg: {}", arg);
			break;
		}
	}

	/**
	 * Valtidates if an supposed integer is an actual integer
	 * 
	 * @param inputInt Integer to validate
	 * @return Integervalue (if int)
	 */
	private static int checkInt(String inputInt) {
		int integer = 0;
		try {
			integer = Integer.parseInt(inputInt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		return integer;
	}

	/**
	 * This method print the version information to console
	 */
	private static void printVersion() {
		System.out.println(VERSION); // NOSONAR
	}

	/**
	 * This Method prints a si mple help document to console
	 */
	private static void printHelp() {
		// TODO Add help once all features are implemented
		System.out.println("NO HELP (yet...)"); // NOSONAR
	}
}
