/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserManagementRemote - Defines the SessionBean Remote                     |
 * |                        interface for the UserManagement bean.             |
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

package org.jmws.session.user.management;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

/**
 * UserManagementRemote
 * 
 * @author Mikael Barbeaux
 */
public interface UserManagementRemote extends EJBObject {

	/**
	 * Returns all inactive Users logins.
	 * 
	 * @return
	 * @throws FinderException
	 * @throws RemoteException
	 */
	public Collection getUsersToActive()
			throws FinderException, RemoteException;
	
	/**
	 * Active the User with the given login and activator.
	 * 
	 * @param login
	 * @param activator
	 * @throws FinderException
	 * @throws RemoteException
	 */		
	public void activeUser(
		String login, 
		String activator
	) throws FinderException, RemoteException;
}
