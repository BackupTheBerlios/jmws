/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | User - Defines the EntityBean object for the default User.                |
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

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * User entity bean CMP 2.0
 * 
 * @author Mikael Barbeaux
 */
public abstract class User implements EntityBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/entity/User";
	
	// Database table name
	public static final String TABLE_NAME = "jmws_users";
	
	/**
	 * CMP fields
	 */
	
	public abstract String getLogin();
	public abstract void setLogin(String login);
	
	public abstract String getPassword();
	public abstract void setPassword(String password);
	
	public abstract Boolean getActive();
	public abstract void setActive(Boolean active);


	/**
	 * Create a new User.
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws CreateException
	 */
	public String ejbCreate(
		String login,
		String password
	) throws CreateException {
		
		
		return login;
	}
	
	
	/**
	 * Code to process after User's creation.
	 * 
	 * @param login
	 * @param password
	 * @throws CreateException
	 */
	public void ejbPostCreate(
		String login,
		String password
	) throws CreateException {
		
	}


	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbLoad() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove()
		throws RemoveException, EJBException, RemoteException {
	}

	public void ejbStore() throws EJBException, RemoteException {
	}

	public void setEntityContext(EntityContext arg0)
		throws EJBException, RemoteException {
	}

	public void unsetEntityContext() throws EJBException, RemoteException {
	}


}
