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

package de.thowl.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is a Representation of a LaTeX-document.
 * <p>
 * It contains methods for the creation of LaTeX-Documents
 * </p>
 * 
 * @author Jonas Schwind
 * @version 0.1.2
 * @see LatexSnippet
 */
public class Latex {

	private static final Logger logger = LogManager.getLogger(Latex.class);
	private LatexSnippet header;
	private ArrayList<LatexSnippet> snippets;
	private LatexSnippet footer;
	private OperatingSystem operatingSystem;
	private String homeDir;

	private String pdflatex;

	/**
	 * Constructor for objects of this class
	 */
	public Latex() {
		snippets = new ArrayList<LatexSnippet>();
		operatingSystem = new OperatingSystem();
		homeDir = operatingSystem.getHomeDir();
		try {
			pdflatex = operatingSystem.getPdflatexLocation();
		} catch (LatexNotInstalledException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method gathers snipptes for a LaTeX document
	 * 
	 * @param type      The "type" of snippets that should be collected, the
	 *                  type is defined by a directoryname in the confighome.
	 * @param chapters  amount of snippets
	 * @param randomise should the order be randomised
	 *                  (always {@code true} except for tests)
	 */
	public void gatherSnippets(String type, int chapters,
			boolean randomise) {
		Random rng = new Random(System.currentTimeMillis() / 1000L);
		String snippetsDir = homeDir + "/latex/";
		header = new LatexSnippet(snippetsDir + type + "/header.tex");
		for (int i = 0; i < chapters; i++) {
			File directory = new File(
					snippetsDir + type + "/chapters/");
			File[] files = directory.listFiles();
			Arrays.sort(files);
			int index = i;
			if (randomise) {
				index = rng.nextInt(files.length);
			}
			File file = files[index];
			snippets.add(new LatexSnippet(file.getPath()));
		}
		footer = new LatexSnippet(snippetsDir + type + "/footer.tex");
	}

	/**
	 * This method concatenates a sourcefile from snippets gathered by the
	 * <em>gatherSnippets</em> method.
	 *
	 * <p>
	 * The method {@link #gatherSnippets(String, int, boolean)} needs to be
	 * called first, as it gathers all snippets that are required for the
	 * sourcefile.
	 * </p>
	 * 
	 * @param type        type of the document, the sourcefile gets saved under
	 *                    this name at a temporary location.
	 * @param destination location the the sourcefile should be placed
	 */
	public void concat(String type, String destination) {
		StringBuilder fileContent = new StringBuilder();
		fileContent.append(header.getFileContent() + "\n");
		Iterator<LatexSnippet> it = snippets.iterator();
		while (it.hasNext()) {
			LatexSnippet snippet = it.next();
			fileContent.append("\\input{" + snippet.getFilePath() + "}\n");
		}
		fileContent.append(footer.getFileContent() + "\n");
		try {
			new File(destination).mkdir();
			String outputfilePath = destination + File.separator + type + ".tex";
			Files.write(Paths.get(outputfilePath), fileContent.toString()
					.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Compiles a LaTeX document from a sourcefile
	 * <p>
	 * The method {@link #concat(String)} needs to be called first, as it
	 * generates the sourcefile.
	 * </p>
	 *
	 * @param type        type of the the sourcefile
	 * @param destination temporary loaction where document should be proccessed
	 */
	public void compile(String type, String workingDir) {
		String texFile = workingDir + File.separator + type + ".tex";
		String outputDir = "-output-directory=" + workingDir;
		String[] command = { pdflatex, outputDir, texFile };
		try {
			// NOTE: Generaly LaTeX-documents are compiled twice
			for (int i = 1; i <= 2; i++) {
				Process proc = Runtime.getRuntime().exec(command);
				// NOTE: pdflatex won't work if messages are suppressed
				BufferedReader stdout = new BufferedReader(
						new InputStreamReader(proc.getInputStream()));
				String pdflatexMessage;
				while ((pdflatexMessage = stdout.readLine()) != null) {
					logger.info(pdflatexMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copys a pdf file to a location.
	 *
	 * @param sourceDir      Directory in wich the original pdf is located
	 * @param fileName       name of the pdf (without type extention)
	 * @param destinationDir directory whrere the pdf should by copyed to.
	 * @param newFileName    new name of the file
	 */
	private void copyPdf(String sourceDir, String fileName, String destinationDir, String newFileName) {
		try {
			File sourceFile = new File(sourceDir + File.separator + fileName + ".pdf");
			File targetFile = new File(destinationDir + File.separator + newFileName);
			new File(destinationDir).mkdir();
			Files.copy(sourceFile.toPath(), targetFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is a wrapper function to genererate documents
	 *
	 * <p>
	 * It handles the methods {@link #gatherSnippets(String, int, boolean)},
	 * {@link #concat(String)} and {@link #compile(String, String)} for you.
	 * </p>
	 *
	 * @param type        type of the document that should be genearated
	 * @param destination directory in which the documunt should be saved
	 * @param amount      amount of instances that should be saved
	 * @param chapters    amount of snippets per document
	 * @param shuffle     generate each document with a new set of snippets
	 */
	public void generate(String type, String destination, int amount,
			int chapters, boolean shuffle) {
		gatherSnippets(type, chapters, true);
		for (int i = 1; i <= amount; i++) {
			// TODO: Add pretty foldername
			String subDir = "foldername" + i;
			String workingDir = "temp" + File.separator + subDir;
			new File(workingDir).mkdir();
			concat(type, workingDir);
			compile(type, workingDir);
			copyPdf(workingDir, type, destination, type);
			Collections.shuffle(snippets);
		}
	}

	/**
	 * Return the header of the document
	 * 
	 * @return the header of the document
	 */
	public LatexSnippet getHeader() {
		return header;
	}

	/**
	 * Return the snippets in the document
	 * 
	 * @return a list of LaTeX snippets
	 */
	public List<LatexSnippet> getSnippets() {
		return snippets;
	}

	/**
	 * Return the footer of the document
	 * 
	 * @return the footer of the document
	 */
	public LatexSnippet getFooter() {
		return footer;
	}
}
