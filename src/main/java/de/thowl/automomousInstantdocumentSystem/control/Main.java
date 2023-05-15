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

package de.thowl.automomousInstantdocumentSystem.control;

/**
 * This is the Main Class of the Program
 * 
 * @version 0.1.2
 * @author Jonas Schwind
 */
public class Main {

    private static String OS;
    private static final String version = "Autonomous-Instantdocument-System V0.1.2-SNAPSHOT";

    // Variables related to document generation
    private static String type = null;
    private static String destination = null;
    private static int amount = 0;

    public static void main(String[] args) {
	OS = System.getProperty("os.name");
	// When no arguments are passed the program runs in a graphical mode
	if (args.length > 0) {
	    // TODO: run GUI
	}
	// handle cli arguments
	handleArgs(args);
	// early return on incomplete values
	if (type == null || destination == null || amount <= 0) {
	    System.out.println("Not enough arguments");
	    System.exit(2);
	}
	System.out.println(type);
	System.out.println(destination);
	System.out.println(amount);
	generate(type, destination, amount);
	System.exit(0);
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
	    // This is a long-form argument
	    if (arg.startsWith("--")) {
		arg = arg.substring(2);
		switch (arg) {
		case "version":
		    printVersion();
		    System.exit(0);
		    break;
		case "help":
		    printHelp();
		    System.exit(0);
		    break;
		default:
		    previousArg = arg;
		    break;
		}
	    // This is a short-form argument
	    } else if (arg.startsWith("-")) {
		arg = arg.substring(1);
		switch (arg) {
		case "version":
		    printVersion();
		    System.exit(0);
		    break;
		case "help":
		    printHelp();
		    System.exit(0);
		    break;
		default:
		    previousArg = arg;
		    break;
		}
	    } else if (previousArg != null) {
		switch (previousArg) {
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
		    amount = Integer.parseInt(arg);
		    break;
		}
		previousArg = null;
	    }
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
	latex.concat(type);
	latex.compile(type, destination);
    }

    /**
     * This Method gets the name of the current OS (a Linux distribution is just
     * called "Linux")
     * 
     * @return OS name
     */
    public static String getOS() {
	return OS;
    }
}
