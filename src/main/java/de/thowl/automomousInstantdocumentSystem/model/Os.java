package de.thowl.automomousInstantdocumentSystem.model;

public class Os {

	private String os;
	private String homeDir;

	public Os() {
		os = defineOs();
		homeDir = defineHomeDir();
	}

	private String defineHomeDir() {
		String directory = System.getenv("AIDS_HOME");
		if (directory == null) {
			if (os.equals("Windows")) {
				directory = System.getenv("appdata");
			} else if (os.equals("UNIX")) {
				directory = System.getenv("XDG_CONFIG_HOME");
			} else if (os.equals("mac")) {
				directory = System.getenv("Library");
			}
			directory += "/aids";
		}
		return directory;
	}

	private String defineOs() {
		String osType = System.getProperty("os.name");
		switch (osType) {
			case "Linux":
				osType = "UNIX";
				break;
			case "Mac OS X":
				osType = "mac";
				break;
			case "Windows 10":
			case "Windows 11":
				osType = "Windows";
				break;
			default:
				break;
		}
		return osType;
	}

	/**
	 * This Method gets the name of the current OS (Just returns Windows or Unix to
	 * simplify things)
	 * 
	 * @return OS name
	 */
	public String getOS() {
		return os;
	}

	public String getHomeDir() {
		return homeDir;
	}
}
