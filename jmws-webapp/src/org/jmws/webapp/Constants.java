/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | Constants - Defines a list of constants for JMWS.                         |
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
 
package org.jmws.webapp;

/**
 * Constants
 * 
 * Defines some constants for the application.
 * 
 * @author Mikael Barbeaux
 */
public final class Constants {

	// The User Session Key
	public static final String USER_KEY = "USER_KEY"; 
	
	// The User Privilege Session Key
	public static final String USERPRIVILEGE_KEY = "USERPRIVILEGE_KEY";
	
	// The privilege list
	public static final String[] PRIVILEGE_LIST = {
		"UNKNOWN", // Unknown User (no privilege)
		"REGISTERED", // Registered User (simple User)
		"USER_MODERATOR", // User Moderator
		"USER_ADMINISTRATOR", // User Administrator
		"GROUP_MANAGER", // Group Manager (User that owns a Group)
		"GROUP_MODERATOR", // Group Moderator
		"GROUP_ADMINISTRATOR", // Group Administrator
		"NEWS_MODERATOR", // News Moderator
		"NEWS_ADMINISTRATOR" // News Administrator
	};

}
