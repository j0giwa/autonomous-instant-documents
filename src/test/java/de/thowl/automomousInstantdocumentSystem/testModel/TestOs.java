package de.thowl.automomousInstantdocumentSystem.testModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.thowl.automomousinstantdocumentsystem.model.Os;

public class TestOs {

	private Os operatingSystem;

	@BeforeEach
	public void setUp() {
		operatingSystem = new Os();
	}

	/**
	 * Tests the {@link Os#getHomeDir()} method to ensure it returns a
	 * nonempty file path.
	 */
	@Test
	public void test_getHomeDir_shouldReturnNonEmptyString() {
		String homeDir = operatingSystem.getHomeDir();
		Assertions.assertNotNull(homeDir);
		Assertions.assertFalse(homeDir.isEmpty());
	}

	/**
	 * Tests the {@link Os#getOS()} method to ensure it returns one of the
	 * following values:
	 * <ul>
	 * <li>{@code UNIX} for UNIX-based operating systems</li>
	 * <li>{@code Windows} for Microsoft operating systems</li>
	 * <li>{@code Mac} for Apple Macs</li>
	 * </ul>
	 */
	@Test
	public void test_getOS_shouldReturnNonValidOs() {
		String os = operatingSystem.getOperatingSystem();
		Assertions.assertNotNull(os);
		Assertions.assertTrue(os.equals("UNIX") || os.equals("Windows")
				|| os.equals("Mac"));
	}
}
