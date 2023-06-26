module de.thowl.main {
	requires org.apache.logging.log4j;
	requires json.simple;
    requires javafx.controls;
    requires javafx.fxml;
    requires de.thowl.gui;
    requires de.thowl.core;
    exports  de.thowl.main;
}