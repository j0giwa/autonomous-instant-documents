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

package controll;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import model.LatexSnippet;

/**
 * This class is a concatenator for LaTeXsnippets files. It can concatenate and
 * compile LaTeX documents.
 * 
 * @version 0.1.2
 * @author jogiwa
 */
public class LatexConcatenator {

	private ArrayList<LatexSnippet> snippets;
	private StringBuilder sb;

	/**
	 * Constructor for objects of this class
	 */
	public LatexConcatenator() {
		snippets = new ArrayList<LatexSnippet>();
		sb = new StringBuilder();
	}

	/**
	 * This method concatenates all requred snippets for a specified document
	 * 
	 * @param type
	 */
	public void concat(String type) {
		// TODO: Replace with data from json (LOOP)
		snippets.add(new LatexSnippet(
				"/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/header.tex"));
		snippets.add(new LatexSnippet(
				"/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/01.tex"));
		snippets.add(new LatexSnippet(
				"/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/02.tex"));
		snippets.add(new LatexSnippet(
				"/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/footer.tex"));
		// Format snippet data to TeX and append it to a to a StringBuilder 
		Iterator<LatexSnippet> it = snippets.iterator();
		while (it.hasNext()) {
			LatexSnippet snippet = it.next();
			String filepath = snippet.getFilepath();
			// Header and footer contents are the only important aspects
			if (filepath.contains("header") || filepath.contains("footer")) {
				sb.append(snippet.getFilecontent()+"\n");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method complies a LaTeX document
	 * TODO: Implement Windows compiler location
	 * TODO: change output location
	 * @param type
	 */
	public void compile(String type) {
		String latexcompiler = "/usr/bin/pdflatex";
		if (!new File(latexcompiler).exists())
			// TODO: Make custom exception
			return;
		// Compiler command: [Compliler] [path to .tex file]
		String compilercommand = latexcompiler + " " + System.getProperty("user.dir") + "/temp/" + type +".tex";
		try {
			// Compile LaTeX document
			Process proc = Runtime.getRuntime().exec(compilercommand);
			BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			// Print stdOut of pdflatex (NECCESARY, DON'T DELETE!!!)
			String output = null;
			do {
				output = stdout.readLine();
				System.out.println(output);
			} while (output != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
