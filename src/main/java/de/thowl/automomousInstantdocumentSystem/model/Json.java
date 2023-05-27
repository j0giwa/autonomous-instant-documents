/*
 * Autonomous Instantdocument System -- Automatically generate LaTeX Documents
 * Copyright (C) 2023 Jonas Schwind
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

package de.thowl.automomousInstantdocumentSystem.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

    private String filePath;
    private JSONObject jsonObject;

    public Json(String filepath) {
        this.filePath = filepath;
        try {
            Object parser = new JSONParser().parse(new FileReader(filepath));
            jsonObject = (JSONObject) parser;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String object, String key) {
        JSONObject currentObject = (JSONObject) jsonObject.get(object);
        String value = (String) currentObject.get(key);
        return value;
    }

    public void setValue(String object, String key, String value) {
        JSONObject currentObject = (JSONObject) jsonObject.get(object);
        currentObject.put(key, value);
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
