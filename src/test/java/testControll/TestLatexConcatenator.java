package testControll;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import controll.LatexConcatenator;

public class TestLatexConcatenator {

	public class ExampleTest {
		@Test
		void testConcat() {
			// Concatenate test Document
			LatexConcatenator concatenator = new LatexConcatenator();
			concatenator.concat("test");

			File testFile = new File("./temp/test.tex");
			File referenceFile = new File("./assets/latex/test/header.tex");

			try {
				String referenceFileContent = FileUtils.readFileToString(referenceFile, "utf-8");
				String testFileContent = FileUtils.readFileToString(testFile, "utf-8");
				assertEquals(referenceFileContent, testFileContent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
