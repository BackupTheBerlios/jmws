/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLocal - Defines the Local interface of the                            |
 * |             EntityBean User.                                              |
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

package org.jmws.entity.user;

import javax.ejb.EJBLocalObject;


/**
 * UserLocal
 * 
 * @author MikaelB
 */
public interface UserLocal extends EJBLocalObject {

	/**
	 * @return Primary key of this User
	 */
	public String getUsername();
	
	/**
	 * @return password
	 */
	public String getPassword();
	
	/**
	 * @return Full name
	 */
	public String getFullName();
	
	/**
	 * @return Email
	 */
	public String getEmail();
	
	/**
	 * @return Website's homepage
	 */
	public String getHomepage();
	
	/**
	 * @return Signature
	 */
	public String getSignature();
	
	/**
	 * @return Avatar
	 */
	public String getAvatar();
	
	/**
	 * @return Biography
	 */
	public String getBiography();
	
	/**
	 * @return Where user lives
	 */
	public String getLocation();
	
	/**
	 * @return What user does in real life
	 */
	public String getOccupation();
	
	/**
	 * @return ICQ number
	 */
	public String getIcqNumber();
	
	/**
	 * @return AIM address
	 */
	public String getAimAddress();

	/**
	 * @return MSN address
	 */	
	public String getMsnAddress();

	/**
	 * @return Yahoo address
	 */	
	public String getYahooAddress();
	
	/**
	 * @return language
	 */	
	public String getLanguage();
	
	/**
	 * @param username - username
	 * @param fullname - fullname
	 * @param newpassword - new password
	 * @param email - email address
	 * @param homepage - homepage
	 * @param signature - signature 
	 * @param avatar - avatar
	 * @param biography - biography
	 * @param location - location 
	 * @param occupation - occupation
	 * @param icq - icq number
	 * @param aim - aim address
	 * @param msn - msn address
	 * @param yahoo - yahoo address
	 * @param lang - language
	 */
	public void updateUser(
		String fullname,
		String newpassword,
		String email,
		String homepage,
		String signature,
		String avatar,
		String biography,
		String location,
		String occupation,
		String icq,
		String aim,
		String msn,
		String yahoo,
		String lang	
	);
	
	
}
