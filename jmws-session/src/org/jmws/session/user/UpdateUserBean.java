/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UpdateUserBean - Defines the SessionBean for updating                     |
 * |                  User objects.                                            |
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
 * UpdateUserBean
 * 
 * @author MikaelB
 */
public class UpdateUserBean implements SessionBean {
	
	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UpdateUser";
	
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
	) throws FinderException {
		try {	
			// Get User Home interface
			InitialContext context = new InitialContext();
			UserLocalHome user_home;
			Object tmp = context.lookup(UserBean.JNDI_NAME);
			user_home = (UserLocalHome) tmp;
	
			// Get User Local
			UserLocal user_local = user_home.findByPrimaryKey(username);
			
			// Update User
			user_local.updateUser(
				fullname,
				newpassword,
				email,
				homepage,
				signature,
				avatar,
				biography,
				location,
				occupation,
				icq,
				aim,
				msn,
				yahoo,
				lang	
			);			
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
