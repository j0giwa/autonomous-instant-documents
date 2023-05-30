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

package de.thowl.automomousInstantdocumentSystem.exceptions;

/**
 * This exception gets thrown wen a LaTeX binary cannot be found
 */
public class LatexNotInstalledException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * @param message errormessage if the exception gets thrown
	 */
	public LatexNotInstalledException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @return Erromassage as a String
	 */
	@Override
	public String toString() {
		return message;
	}
}
