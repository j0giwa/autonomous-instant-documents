/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind, Marvin Boschmann
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

package de.thowl.aids.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class contains Methods to read and write a JSon file
 *
 * @author Jonas Schwind
 * @version 1.0,0
 */
public class Json {

  private String filePath;
  private JSONObject jsonObject;

  /**
   * Constructor for objects of this class
   *
   * @param filepath The location of the JSonfile
   */
  public Json(String filepath) {
    this.filePath = filepath;
    try {
      Object parser = new JSONParser().parse(new FileReader(filepath));
      jsonObject = (JSONObject)parser;
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns a value from a JSon file.
   *
   * @param object JSonObject that contains the value that should be
   *               returned
   * @param key    key in JSonObject, that contains the value that should
   *               be returned
   * @return value of the Json Key
   */
  public String getValue(String object, String key) {
    JSONObject currentObject = (JSONObject)jsonObject.get(object);
    return (String)currentObject.get(key);
  }

  /**
   * Cahnges a value in a JSon file.
   *
   * @param object JSonObject that contains the value that should be
   *               changed
   * @param key    key in JSonObject, that contains the value that should
   *               be changed
   * @param value  value the should be changed
   */
  public void setValue(String object, String key, String value) {
    JSONObject currentObject = (JSONObject)jsonObject.get(object);
    // NOTE: JSONObject is not generic, warning cannot be fixed
    currentObject.put(key, value);
    try (FileWriter file = new FileWriter(filePath)) {
      file.write(jsonObject.toJSONString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getFilePath() { return filePath; }

  public void setFilePath(String filePath) { this.filePath = filePath; }

  public JSONObject getJsonObject() { return jsonObject; }

  public void setJsonObject(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }
}
