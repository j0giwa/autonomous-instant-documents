package testControll;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import controll.LatexConcatenator;

public class TestLatexConcatenator {

	@Test
	void testConcat() {
		// Concatenate test Document
		LatexConcatenator concatenator = new LatexConcatenator();
		concatenator.concat("test");

		File testFile = new File("./temp/test.tex");
		File referenceFile = new File("./assets/latex/test/test.tex");

		try {
			String referenceFileContent = FileUtils.readFileToString(referenceFile, "utf-8");
			String testFileContent = FileUtils.readFileToString(testFile, "utf-8");
			assertEquals(referenceFileContent, testFileContent);
		} catch (IOException e) {
			fail("One or more files does not exist");
		}
	}
}
