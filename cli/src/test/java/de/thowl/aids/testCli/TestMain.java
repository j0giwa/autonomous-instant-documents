/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind, Martin Boschmann
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package de.thowl.aids.testCli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.jupiter.api.Test;

import de.thowl.aids.cli.Main;

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
