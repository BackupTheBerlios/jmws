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
