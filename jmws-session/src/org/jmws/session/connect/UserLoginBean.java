/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLoginBean - Defines the SessionBean for logging in.                   |
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
 
package org.jmws.session.connect;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jmws.entity.user.UserBean;
import org.jmws.entity.user.UserLocal;
import org.jmws.entity.user.UserLocalHome;

/**
 * UserLoginBean
 * 
 * @author MikaelB
 */
public class UserLoginBean implements SessionBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UserLogin";
	
	public boolean login(String username, String password)
			throws FinderException {
		try {	
			// Get User Home interface
			InitialContext context = new InitialContext();
			UserLocalHome user_home;
			Object tmp = context.lookup(UserBean.JNDI_NAME);
			user_home = (UserLocalHome) tmp;

			// Get User with that username
			UserLocal user_local = user_home.findByPrimaryKey(username);
			
			// Test password and return
			if(user_local.getPassword().equals(password))
				return true;
			else return false;
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
