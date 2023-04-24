package model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LatexSnippet {

	private File texfile;
	private String filepath;
	private String filecontent;
	
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
	
	public String getFilepath() {
		return filepath;
	}
	
	public String getFilecontent() {
		return filecontent;
	}
}
