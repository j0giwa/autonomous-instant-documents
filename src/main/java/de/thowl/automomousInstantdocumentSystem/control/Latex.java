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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import de.thowl.automomousInstantdocumentSystem.Main;
import de.thowl.automomousInstantdocumentSystem.exceptions.LatexNotInstalledException;
import de.thowl.automomousInstantdocumentSystem.model.LatexSnippet;

/**
 * This class is a concatenator for LaTeX snippets files. It can concatenate and
 * compile LaTeX documents.
 * 
 * @version 0.1.2
 * @author Jonas Schwind
 */
public class Latex {

	private ArrayList<LatexSnippet> snippets;
	private StringBuilder sb;

	/**
	 * Constructor for objects of this class
	 */
	public Latex() {
		snippets = new ArrayList<LatexSnippet>();
		sb = new StringBuilder();
	}

	/**
	 * This Method gthers snipptes for a document
	 * 
	 * @param type
	 * @param chapters
	 * @param shuffle
	 */
	public void gatherSnippets(String type, int chapters, boolean shuffle) {
		String OS = Main.getOS();
		String snippetsDir = null;
		// determine OS specific file path
		if (OS.equals("Windows")) {
			snippetsDir = System.getenv("appdata") + "/aids/latex/";
		} else if (OS.equals("UNIX")) {
			snippetsDir = System.getenv("XDG_CONFIG_HOME") + "/aids/latex/";
		}
		snippets.add(new LatexSnippet(snippetsDir + type + "/header.tex"));
		for (int i = 0; i < chapters; i++) {
			File directory = new File(snippetsDir + type + "/chapters/");
			File[] files = directory.listFiles();
			int index = i;
			if (shuffle) {
				Random rng = new Random();
				index = rng.nextInt(files.length);
			}
			File file = files[index];
			snippets.add(new LatexSnippet(file.getPath()));
		}
		snippets.add(new LatexSnippet(snippetsDir + type + "/footer.tex"));
	}

	/**
	 * This method concatenates all required snippets for a specified document
	 * 
	 * @param type
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
	 * This Methodd returns the default location of the pdflatex binary for the
	 * current opperating system.
	 * 
	 * @return location of pdflatex
	 * @throws LatexNotInstalledException
	 */
	private String latexCompilerLocation() throws LatexNotInstalledException {
		String compilerPath = null;
		System.out.println(Main.getOS());
		if (Main.getOS().equals("UNIX")) {
			compilerPath = "/usr/bin/pdflatex";
		} else if (Main.getOS().equals("Windows")) {
			compilerPath = "C:\\texlive\\2023\\bin\\windows\\pdflatex.exe";
		}
		if (!new File(compilerPath).exists())
			throw new LatexNotInstalledException("pdflatex not found");
		return compilerPath;
	}

	/**
	 * This method compiles a LaTeX document
	 * 
	 * @param type
	 * @param destination
	 */
	public void compile(String type, String destination) {
		String compiler = null;
		try {
			compiler = latexCompilerLocation();
		} catch (LatexNotInstalledException e) {
			e.printStackTrace();
		}
		// Variables for LaTeX complier
		String userdir = System.getProperty("user.dir");
		String outputDir = "-output-directory=" + destination;
		String texFile = userdir + "/temp/" + type + ".tex";
		// Compiler command: [Compliler] [path to destination] [path to source.tex file]
		String compilercommand = compiler + " " + outputDir + " " + texFile;
		try {
			// Compile LaTeX document
			Process proc = Runtime.getRuntime().exec(compilercommand);
			BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			// Print stdOut of pdflatex (NOTE: NECCESARY, DON'T DELETE!!!)
			String compliermsg;
			while ((compliermsg = stdout.readLine()) != null) {
				System.out.println(compliermsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method concatinates and Compiles Documents within the passed parameters
	 * Use this to generate Documents
	 * 
	 * @param type
	 * @param destination
	 * @param amount
	 */
	public void generate(String type, String destination, int amount, int chapters, boolean shuffle) {
		gatherSnippets(type, chapters, shuffle);
		for (int i = 0; i <= amount; i++) {
			String subdirname = "foldername" + i;
			String outputDir = destination + "/" + subdirname;
			new File(outputDir).mkdir();
			concat(type);
			compile(type, outputDir);
		}
	}
}
