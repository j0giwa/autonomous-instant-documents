package controll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import model.LatexSnippet;

public class LatexConcatinator {

	private ArrayList<LatexSnippet> snippets;
	private StringBuilder sb;

	public LatexConcatinator() {
		snippets = new ArrayList<LatexSnippet>();
		sb = new StringBuilder();	
	}
	
	public void concat(String type) {
		// TODO: Replace with data from json
		System.out.println(System.getProperty("user.dir"));
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
		}
		File file = new File("./temp/" + type + ".tex");
		try {
			FileUtils.writeStringToFile(file, sb.toString(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done!");
	} 
}
