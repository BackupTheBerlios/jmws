/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserManagement - Defines the SessionBean object for                       |
 * |                  the User management operations.                          |
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jmws.entity.user.User;
import org.jmws.entity.user.UserLocal;
import org.jmws.entity.user.UserLocalHome;

/**
 * 
 * 
 * @author Mikael Barbeaux
 */
public class UserManagement implements SessionBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UserManagement";


	/**
	 * Returns all inactive Users logins.
	 * 
	 * @return
	 * @throws FinderException
	 */
	public Collection getUsersToActive()
			throws FinderException {
		
		try {
			// JNDI naming context
			InitialContext context = new InitialContext();
			
			// Get the UserLocalHome interface
			UserLocalHome home;
			Object obj = context.lookup(User.JNDI_NAME);
			home = (UserLocalHome) obj;
			
			// Get all inactive Users
			Collection users = home.findByActive(Boolean.FALSE);	
			
			// Build output collection with logins
			Collection logins = new ArrayList();
			Iterator it = users.iterator();
			while(it.hasNext()) {
				UserLocal user = (UserLocal) it.next();
				logins.add(user.getLogin());
			}
			
			// Returns the output collection
			return logins;
		}
		catch(NamingException ne) {
			throw new FinderException("NamingException: " + 
				ne.getMessage());
		}
	}
	
	
	/**
	 * Active the User with the given login and activator.
	 * 
	 * @param login
	 * @param activator
	 * @throws FinderException
	 */		
	public void activeUser(
		String login, 
		String activator
	) throws FinderException {
		
		try {
			// JNDI naming context
			InitialContext context = new InitialContext();
			
			// Get UserLocalHome interface
			UserLocalHome home;
			Object obj = context.lookup(User.JNDI_NAME);
			home = (UserLocalHome) obj;
			
			// Get the User to activate
			UserLocal user = home.findByPrimaryKey(login);
			
			// Get the Activator
			UserLocal theActivator = home.findByPrimaryKey(activator);
			
			// Set User as active and set his activator
			user.setActive(Boolean.TRUE);
			user.setTheActivator(theActivator);
			
		}
		catch(NamingException ne) {
			throw new FinderException("NamingException: " + 
				ne.getMessage());
		}
	}


	public void ejbCreate() throws CreateException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0)
		throws EJBException, RemoteException {
	}

}
