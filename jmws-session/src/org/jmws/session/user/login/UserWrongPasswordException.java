/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserWrongPasswordException - Defines an exception for UserLogIn           |
 * |                              wrong password error.                        |
 * +---------------------------------------------------------------------------+
 * | Copyright (C) 2000,2001 by the following authors:                         |
 * |                                                                           |
 * | Authors: Mikael Barbeaux  - mikaelbarbeaux@users.sourceforge.net          |
 * +---------------------------------------------------------------------------+
 * |                                                                           |
 * | This program is free software; you can redistribute it and/or             |
 * | modify it under the terms of the GNU General Public License               |
 * | as published by the Free Software Foundation; either version 2            |
 * | of the License, or (at your option) any later version.                    |
 * |                                                                           |
 * | This program is distributed in the hope that it will be useful,           |
 * | but WITHOUT ANY WARRANTY; without even the implied warranty of            |
 * | MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             |
 * | GNU General Public License for more details.                              |
 * |                                                                           |
 * | You should have received a copy of the GNU General Public License         |
 * | along with this program; if not, write to the Free Software Foundation,   |
 * | Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.           |
 * |                                                                           |
 * +---------------------------------------------------------------------------+
 */

package org.jmws.session.user.login;

/**
 * UserWrongPasswordException
 * 
 * @author Mikael Barbeaux
 */
public class UserWrongPasswordException extends Exception {

	// Message of this exception
	private String message;

	/**
	 * Default constructor
	 * @param message
	 */
	public UserWrongPasswordException(String message) {
		super();
		this.message = message;
	}
	
	
	/**
	 * Returns the exception's message
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}


}
