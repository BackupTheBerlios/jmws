/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLocalHome - Defines the Local Home interface of the                   |
 * |                 EntityBean User.                                          |
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

import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * UserLocalHome
 * 
 * @author MikaelB
 */
public interface UserLocalHome extends EJBLocalHome {
	
	/**
	 * @param username - User's username
	 * @param id - User's id
	 * @param password - User's password
	 * @return Instance of UserLocal
	 * @throws CreateException
	 */
	public UserLocal create(
		String username,
		Long id,
		String password	
	) throws CreateException;
	
	/**
	 * @param username - Primary key of User to find
	 * @return Corresponding user
	 * @throws FinderException
	 */
	public UserLocal findByPrimaryKey(String username)
			throws FinderException;
	
	/**
	 * @return Corresponding users
	 * @throws FinderException
	 */	
	public Collection findAll() throws FinderException;
	
	/**
	 * @param active - activity state
	 * @return Corresponding users
	 * @throws FinderException
	 */
	public Collection findByActive(Boolean active)
			throws FinderException;
	
	
	/**
	 * @param registerDate - registration date of Users to find
	 * @return Corresponding users
	 * @throws FinderException
	 */	
	public Collection findByRegistrationDate(Date registerDate)
			throws FinderException;
			
}
