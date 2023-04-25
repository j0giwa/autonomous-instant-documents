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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import model.LatexSnippet;

/**
 * This class is a concatenator for LaTeX-snippets files.
 * It can concatenate and compile LaTeX documents.
 * 
 * @version 0.0.0.1
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
	 * @param String
	 */
	public void concat(String type) {
		
		// TODO: Replace with data from json
		snippets.add(new LatexSnippet("/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/header.tex"));
		snippets.add(new LatexSnippet("/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/01.tex"));
		snippets.add(new LatexSnippet("/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/02.tex"));
		snippets.add(new LatexSnippet("/home/jogiwa/Documents/projekte/eclipse/automomous-instantdocument-system/assets/latex/test/footer.tex"));
		
		// Feed relevant snippet data to StringBuilder
		Iterator<LatexSnippet> it = snippets.iterator();
		while(it.hasNext()) {
			LatexSnippet currentSnipptet = it.next();
			// FileContent is only relevant for header and footer files
			if (currentSnipptet.getFilepath().contains("header.tex") ||
					currentSnipptet.getFilepath().contains("footer.tex")) {
				sb.append(currentSnipptet.getFilecontent());
			} else {
				sb.append("\\input{" + currentSnipptet.getFilepath() + "}");
			}
			sb.append("\n");
		}
		File file = new File("./temp/" + type + ".tex");
		try {
			FileUtils.writeStringToFile(file, sb.toString(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
