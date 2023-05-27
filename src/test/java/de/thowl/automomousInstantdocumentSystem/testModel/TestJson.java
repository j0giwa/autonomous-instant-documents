package de.thowl.automomousInstantdocumentSystem.testModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import de.thowl.automomousInstantdocumentSystem.model.Json;

public class TestJson {

    @Test
    void testGetValue() {
        Json json = new Json("./src/test/resources/test.json");
        assertEquals("poggers", json.getValue("test", "testGetValue"));
    }

    @Test
    void testsetValue() {
        Json json = new Json("./src/test/resources/test.json");
        String oldValue = json.getValue("test", "testSetValue");
        Random random = new Random(System.currentTimeMillis() / 1000L);
        json.setValue("test", "testSetValue", String.valueOf(random.nextInt()));
        String newValue = json.getValue("test", "testSetValue");
        assertNotEquals(oldValue, newValue);
    }
}
