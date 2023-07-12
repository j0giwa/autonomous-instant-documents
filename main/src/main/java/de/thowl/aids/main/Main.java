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

package de.thowl.aids.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.thowl.aids.core.Latex;
import javafx.application.Application;

/**
 * This is the Main class of the Program
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
	 * @param args The command line arguments,
	 *             without args the programm launches in a GUI.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			Application.launch(de.thowl.aids.gui.Gui.class, args);
			System.exit(EXIT_SUCCESS);
		}
		handleArgs(args);
		if (type == null || destination == null || amount <= 0
				|| chapters <= 0) {
			logger.error("Not enough arguments");
			System.exit(EXIT_ERROR);
		}
		Latex latex = new Latex();
		logger.info("Generating {} '{}'-Documents with {} chapters.",
				amount, type, chapters);
		latex.generate(type, destination, amount, chapters, shuffle);
		logger.info("done");
		System.exit(EXIT_SUCCESS);
	}

	/**
	 * Strips the preceeding dashes from an arg
	 *
	 * @param arg without dashes
	 */
	private static String formatArg(String arg) {
		if (arg.isEmpty() || arg.equals(""))
			return null;
		if (arg.startsWith("--"))
			arg = arg.substring(2);
		if (arg.startsWith("-"))
			arg = arg.substring(1);
		return arg;
	}

	/**
	 * Handles the command line arguments.
	 *
	 * <p>
	 * Only the following arguments are handled:
	 * </p>
	 * <ul>
	 * <li><em>v</em> - Print version and exit</li>
	 * <li><em>version</em> - Same as <em>v</em></li>
	 * <li><em>h</em> - Print help and exit</li>
	 * <li><em>help</em> - Same as <em>h</em></li>
	 * <li><em>ns</em> - Turn off shuffle mode</li>
	 * <li><em>noshuffle</em> - Same as <em>ns</em></li>
	 * </ul>
	 *
	 * <p>
	 * If the argument is not in the list, it is assumed that the argument
	 * is a parameterized argument. These arguments are redirected to
	 * {@link Main#handleParamArgs(String arg, String prevArg)}.
	 * </p>
	 *
	 * @param args The command line arguments
	 */
	public static void handleArgs(String[] args) {
		int argc = args.length;
		String arg = null;
		String prevArg = null;
		for (int i = 0; i < argc; i++) {
			arg = formatArg(args[i]);
			if (prevArg != null) {
				handleParamArgs(arg, prevArg);
				prevArg = null;
			}
			if (arg == null)
				return;
			switch (arg) {
				case "version":
				case "v":
					printVersion();
					break;
				case "help":
				case "h":
					printHelp();
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
	 * parameters, i.e "--something {param}".
	 *
	 * @param args
	 */
	@SuppressWarnings("squid:S131") // No deafult, because of the arg params
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
		}
	}

	/**
	 * Valtidates if an supposed integer is an actual integer
	 *
	 * @param inputInt Integer to validate
	 * @return Integervalue (if int)
	 */
	public static int checkInt(String inputInt) {
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
	@SuppressWarnings("squid:S106") // justified sysout, used as usermessage
	private static void printVersion() {
		System.out.println(VERSION);
		System.exit(EXIT_SUCCESS);
	}

	/**
	 * Prints a simple help to console
	 */
	@SuppressWarnings("squid:S106") // justified sysout, used as usermessage
	private static void printHelp() {
		System.out.println("Flags:\t\t\t\tFunction:");
		System.out.println("-t --type <type>\t\t"
				+ "Specifies the Document to generate.");
		System.out.println("-c --chapters <chapters>\t"
				+ "Specifies the amount of chapters per document.");
		System.out.println("-a --amount <amount>\t\t"
				+ "Specifies the amount of Documents.");
		System.out.println("-ns --noshuffle\t\t\t"
				+ "Turns off shuffle mode.");
		System.out.println(
				"-h --help\t\t\t" + "Show summary of options.");
		System.out.println("-v --version\t\t\t"
				+ "Print version number and exit.");
		System.exit(EXIT_SUCCESS);
	}

	// Helpers for tests

	public static String getType() {
		return type;
	}

	public static String getDestination() {
		return destination;
	}

	public static int getAmount() {
		return amount;
	}

	public static int getChapters() {
		return chapters;
	}

	public static boolean getShuffle() {
		return shuffle;
	}

}
