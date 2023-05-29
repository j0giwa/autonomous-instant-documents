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

package de.thowl.automomousInstantdocumentSystem.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import de.thowl.automomousInstantdocumentSystem.exceptions.LatexNotInstalledException;
import de.thowl.automomousInstantdocumentSystem.model.LatexSnippet;
import de.thowl.automomousInstantdocumentSystem.model.Os;

/**
 * This class is a concatenator for LaTeX-snippets. It can concatenate and
 * compile LaTeX documents.
 * 
 * @author Jonas Schwind
 * @version 0.1.2
 * 
 * @see de.thowl.automomousInstantdocumentSystem.model.LatexSnippet
 */
public class Latex {

	private ArrayList<LatexSnippet> snippets;
	private StringBuilder sb;
	private Os os;
	private String osName;
	private String homeDir;

	/**
	 * Constructor for objects of this class
	 */
	public Latex() {
		snippets = new ArrayList<LatexSnippet>();
		sb = new StringBuilder();
		os = new Os();
		osName = os.getOS();
		homeDir = os.getHomeDir();
		os = null;
	}

	/**
	 * This Method gathers snipptes for a LaTex document
	 * 
	 * @param type     The "type" of snippets that should be collected, the type is
	 *                 defined by a directoryname in the config-directory.
	 * @param chapters amount of snippets
	 * @param shuffle  should the order be randomised (always true except for
	 *                 tests)
	 */
	public void gatherSnippets(String type, int chapters, boolean randomise) {
		String snippetsDir = homeDir + "/latex/";
		snippets.add(new LatexSnippet(snippetsDir + type + "/header.tex"));
		for (int i = 0; i < chapters; i++) {
			File directory = new File(snippetsDir + type + "/chapters/");
			File[] files = directory.listFiles();
			Arrays.sort(files);
			int index = i;
			if (randomise) {
				Random rng = new Random();
				index = rng.nextInt(files.length);
			}
			File file = files[index];
			snippets.add(new LatexSnippet(file.getPath()));
		}
		snippets.add(new LatexSnippet(snippetsDir + type + "/footer.tex"));
	}

	/**
	 * This method concatenates a sourcefile from snippets gathered by the
	 * <em>gatherSnippets</em> method.
	 * 
	 * <p>
	 * The method <em>gatherSnippets</em> nedds to called first,
	 * as it gathers all snippets that are required for the sourcefile.
	 * </p>
	 * 
	 * @param type type of the document, the sourcefile gets saved under this name
	 *             at a temporary location
	 * @see de.thowl.automomousInstantdocumentSystem.control.Latex.gatherSnippets
	 */
	public void concat(String type) {
		// Format snippet data to TeX and append it to a to a StringBuilder
		Iterator<LatexSnippet> it = snippets.iterator();
		while (it.hasNext()) {
			LatexSnippet snippet = it.next();
			String filepath = snippet.getFilepath();
			// Header and footer contents are the only important aspects
			if (filepath.contains("header") || filepath.contains("footer")) {
				sb.append(snippet.getFilecontent() + "\n");
				continue;
			}
			// For all other files their path is sufficient.
			sb.append("\\input{" + snippet.getFilepath() + "}\n");
		}
		try {
			// Print StringBuilder String to .tex file
			File outputfile = new File("./temp/" + type + ".tex");
			FileUtils.writeStringToFile(outputfile, sb.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the location of the pdflatex binary current operating system
	 * 
	 * <p>
	 * Only the default instalation locations are checked.
	 * If the binary is stored anywhre else, it can't be found.
	 * </p>
	 * 
	 * @return location of the pdflatex binary
	 * @throws LatexNotInstalledException if the compiler cannot be found anywhere
	 *                                    on the system.
	 */
	private String latexCompilerLocation() throws LatexNotInstalledException {
		String compilerPath = null;
		if (osName.equals("UNIX")) {
			compilerPath = "/usr/bin/pdflatex";
		} else if (osName.equals("Windows")) {
			compilerPath = "C:\\texlive\\2023\\bin\\windows\\pdflatex.exe";
		}
		if (!new File(compilerPath).exists())
			throw new LatexNotInstalledException("pdflatex not found");
		return compilerPath;
	}

	/**
	 * Compiles a LaTeX document from a sourcefile
	 * 
	 * <p>
	 * The method <em>concat</em> needs to be called first,
	 * as it generates the sourcefile.
	 * </p>
	 * 
	 * @param type        type of the the sourcefile ()
	 * @param destination outputlocation for the compilde document.
	 * @see de.thowl.automomousInstantdocumentSystem.control.Latex.concat
	 */
	public void compile(String type, String destination) {
		String compiler = null;
		try {
			compiler = latexCompilerLocation();
		} catch (LatexNotInstalledException e) {
			e.printStackTrace();
		}
		String userdir = System.getProperty("user.dir");
		String outputDir = "-output-directory=" + destination;
		String texFile = userdir + "/temp/" + type + ".tex";
		String compilercommand = compiler + " " + outputDir + " " + texFile;
		try {
			Process proc = Runtime.getRuntime().exec(compilercommand);
			// Print stdOut of pdflatex NOTE: NECCESARY, DON'T DELETE!!!
			InputStreamReader inputStream = new InputStreamReader(proc.getInputStream());
			BufferedReader stdout = new BufferedReader(inputStream);
			String compliermsg;
			while ((compliermsg = stdout.readLine()) != null) {
				System.out.println(compliermsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method is wrapper function, genererate documents here
	 * 
	 * @param type        type of the document that should be genearated
	 * @param destination directory in which the documunt should be saved
	 * @param amount      amount of instances that should be saved
	 * @param shuffle     generate each document with a new set of snippets
	 */
	public void generate(String type, String destination, int amount, int chapters, boolean shuffle) {
		gatherSnippets(type, chapters, true);
		for (int i = 0; i <= amount; i++) {
			String subdirname = "foldername" + i;
			String outputDir = destination + "/" + subdirname;
			new File(outputDir).mkdir();
			concat(type);
			compile(type, outputDir);
		}
	}
}
