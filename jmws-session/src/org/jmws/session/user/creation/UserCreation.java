/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserCreation - Defines the SessionBean object for                         |
 * |                the User creation operation.                               |
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

package org.jmws.session.user.creation;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jmws.entity.user.User;
import org.jmws.entity.user.UserLocal;
import org.jmws.entity.user.UserLocalHome;
import org.jmws.entity.user.infos.UserInfos;
import org.jmws.entity.user.infos.UserInfosLocal;
import org.jmws.entity.user.infos.UserInfosLocalHome;
import org.jmws.session.pkgenerator.PKGeneratorUtils;

/**
 * UserCreation
 * 
 * @author Mikael Barbeaux
 */
public class UserCreation implements SessionBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UserCreation";
	
	
	/**
	 * Create a new User in the database.
	 * 
	 * @param login
	 * @param password
	 * @param email
	 * @param active
	 * @return
	 * @throws CreateException - In case of creation problems
	 */
	public String addUser(
		String login,
		String password,
		String email,
		Boolean active
	) throws CreateException {
		
		try {
			// JNDI context naming
			InitialContext context = new InitialContext();
			
			// Get UserLocalHome interface
			UserLocalHome home;
			Object obj = context.lookup(User.JNDI_NAME);
			home = (UserLocalHome) obj;
			
			// Create the new User
			UserLocal local = home.create(login, password, email);
		
			// Set active state
			local.setActive(active);
			
			
			// Get UserInfosLocalHome
			UserInfosLocalHome infos_home;
			obj = context.lookup(UserInfos.JNDI_NAME);
			infos_home = (UserInfosLocalHome) obj;
			
			// Get next primary key for UserInfos
			Long pk = PKGeneratorUtils.getNextPK(UserInfos.TABLE_NAME);
			
			// Create the new UserInfos
			UserInfosLocal infos_local = infos_home.create(pk);
			
			
			// Set User's UserInfos
			local.setTheUserInfos(infos_local);
		
		
			// Return User's primary key
			return login;
		}
		catch(NamingException ne) {
			throw new CreateException("NamingException: " + 
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
