/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLocalHome - Defines the EntityBean LocalHome interface for            |
 * |                 the default User.                                         |
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
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;


public interface UserLocalHome extends EJBLocalHome {

	/**
	 * Create a new User.
	 * 
	 * @param login
	 * @param password
	 * @param email
	 * @return
	 * @throws CreateException
	 */
	public UserLocal create(
		String login,
		String password,
		String email
	) throws CreateException;
	
	
	/**
	 * Find a User by its login.
	 * 
	 * @param login
	 * @return
	 * @throws FinderException - If not found
	 */
	public UserLocal findByPrimaryKey(String login)
		throws FinderException;
		
	
	/**
	 * Returns all Users.
	 * 
	 * @return
	 * @throws FinderException
	 */
	public Collection findAll()
		throws FinderException;
		
	
	/**
	 * Find Users by their active state.
	 * 
	 * @param active
	 * @return
	 * @throws FinderException
	 */
	public Collection findByActive(Boolean active)
		throws FinderException;

}
