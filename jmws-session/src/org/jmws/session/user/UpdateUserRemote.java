/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UpdateUserRemote - Defines the Remote interface for the                   |
 * |                    SessionBean UpdateUser.                                |
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
 
package org.jmws.session.user;

import java.rmi.RemoteException;

import javax.ejb.FinderException;
import javax.ejb.EJBObject;

/**
 * UpdateUserRemote
 * 
 * @author MikaelB
 */
public interface UpdateUserRemote extends EJBObject {

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
	 * @throws CreateException
	 */
	public void updateUser(
		String username,
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
	) throws FinderException, RemoteException;
	
}
