/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLogIn - Defines the SessionBean object for                            |
 * |             the User logging in operation.                                |
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

import java.rmi.RemoteException;
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
 * UserLogIn
 * 
 * @author Mikael Barbeaux
 */
public class UserLogIn implements SessionBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UserLogIn";

	/**
	 * Checks for log in for that User.
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws FinderException
	 * @throws UserWrongPasswordException
	 * @throws UserInactiveException
	 */
	public Boolean checkLogIn(
		String login,
		String password
	) throws FinderException, UserWrongPasswordException,
			UserInactiveException {
	
		try {
			// JNDI context naming
			InitialContext context = new InitialContext();
			
			// Get UserLocalHome interface
			UserLocalHome home;
			Object obj = context.lookup(User.JNDI_NAME);
			home = (UserLocalHome) obj;
			
			// Find the specified User
			UserLocal user = home.findByPrimaryKey(login);
			
			// Check that User isn't inactive
			if(! user.getActive().booleanValue()) {
				throw new UserInactiveException("User " + login +
					" is inactive");
			}
			
			// Check that the password is correct
			if(! user.getPassword().equals(password)) {
				throw new UserWrongPasswordException("The specified" +					" password isn't correct.");
			}
			
			// Everything is ok !
			return Boolean.TRUE;
		}
		catch(NamingException ne) {
			return Boolean.FALSE;
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
