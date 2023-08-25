
package de.thowl.aids.testMain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.jupiter.api.Test;

import de.thowl.aids.main.Main;

public class TestMain {

  @Test
  void test_handleArgs_parsesArgs() {
    Random rand = new Random();
    byte[] array = new byte[7]; // length is bounded by 7
    rand.nextBytes(array);
    String rdmType = new String(array, Charset.forName("UTF-8"));
    int rdmChapters = rand.nextInt(100 + 1);
    int rdmAmount = rand.nextInt(100 + 1);

    String[] args = {
        "-t",
        rdmType,
        "-a",
        String.valueOf(rdmAmount),
        "-c",
        String.valueOf(rdmChapters)
    };
    Main.handleArgs(args);
    assertEquals(rdmType, Main.getType());
    assertEquals(rdmAmount, Main.getAmount());
    assertEquals(rdmChapters, Main.getChapters());
  }
}
