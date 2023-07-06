package de.thowl.aids.testGui;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public abstract class TestFxBase extends ApplicationTest {

	@Before
	public void setup() throws Exception {
		ApplicationTest.launch(de.thowl.aids.gui.Gui.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
	}

	@After
	public void afterEachTest() throws TimeoutException {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

}
