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

package de.thowl.automomousInstantdocumentSystem.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * This class is a representation of a .tex file. It contains the Path and the
 * content of the file
 * 
 * @version 0.0.0.1
 * @author Jonas Schwwind
 */
public class LatexSnippet {

    private File texfile;
    private String filepath;
    private String filecontent;

    /**
     * Constructor for objects of this class
     */
    public LatexSnippet(String filepath) {
	this.filepath = filepath;
	texfile = new File(this.filepath);
	try {
	    filecontent = FileUtils.readFileToString(texfile, "utf-8");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Get the location of the file
     * 
     * @return String
     */
    public String getFilepath() {
	return filepath;
    }

    /**
     * Get the content of the file
     * 
     * @return String
     */
    public String getFilecontent() {
	return filecontent;
    }

    /**
     * Overwrite the location of the file
     * 
     * @param String
     */
    public void setFilepath(String filepath) {
	this.filepath = filepath;
    }

    /**
     * Overwrite the content of the file
     * 
     * @param String
     */
    public void getFilecontent(String filecontent) {
	this.filecontent = filecontent;
    }
}
