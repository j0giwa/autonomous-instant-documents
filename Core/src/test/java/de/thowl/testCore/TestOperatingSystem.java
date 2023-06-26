package de.thowl.automomousInstantdocumentSystem.testModel;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.core.LatexNotInstalledException;
import de.thowl.core.OperatingSystem;

public class TestOperatingSystem {

	private OperatingSystem operatingSystem;

	@BeforeEach
	public void setUp() {
		operatingSystem = new OperatingSystem();
	}

	/**
	 * Tests the {@link OperatingSystem#getHomeDir()} method to ensure it
	 * returns a nonempty file path.
	 */
	@Test
	public void test_getHomeDir_shouldReturnNonEmptyString() {
		String homeDir = operatingSystem.getHomeDir();
		assertFalse(homeDir.isEmpty());
	}

	/**
	 * Tests the {@link OperatingSystem#getOS()} method to ensure it returns
	 * one of the following values:
	 * <ul>
	 * <li>{@code UNIX} for UNIX-based operating systems</li>
	 * <li>{@code Windows} for Microsoft operating systems</li>
	 * <li>{@code Mac} for Apple Macs</li>
	 * </ul>
	 */
	@Test
	public void test_getOS_shouldReturnNonValidOs() {
		String os = operatingSystem.getOperatingSystem();
		assertTrue(os.equals("UNIX") || os.equals("Windows")
				|| os.equals("Mac"));
	}

	/**
	 * Tests the {@link OperatingSystem#getPdflatexLocation()} method to
	 * ensure it correctly returns the location of the pdflatex binary based
	 * on the current operating system.
	 */
	@Test
	public void test_getPdflatexLocation_ShouldReturnPdflatexLocation() {
		String pdflatex;
		try {
			pdflatex = operatingSystem.getPdflatexLocation();
			assertNotNull(pdflatex);
		} catch (LatexNotInstalledException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}
