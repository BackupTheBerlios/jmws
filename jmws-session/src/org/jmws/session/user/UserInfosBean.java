/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserInfosBean - Defines the SessionBean for get informations about        |
 * |                 User objects.                                             |
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
import java.util.HashMap;

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
 * UserInfosBean
 * 
 * @author MikaelB
 */
public class UserInfosBean implements SessionBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/session/UserInfos";

	/**
	 * @param username - User's username
	 * @return A Hashmap with all his infos
	 * @throws RemoteException
	 */
	public HashMap getAllInfosAbout(String username)
			throws FinderException {
		// Output hashmap
		HashMap output = new HashMap();
		
		try {	
			// Get User Home interface
			InitialContext context = new InitialContext();
			UserLocalHome user_home;
			Object tmp = context.lookup(UserBean.JNDI_NAME);
			user_home = (UserLocalHome) tmp;
			
			// Get the desired User
			UserLocal user = user_home.findByPrimaryKey(username);
			
			// Collects infos and put them into the output
			output.put("username",username);
			output.put("fullname",user.getFullName());
			output.put("email",user.getEmail());
			output.put("homepage",user.getHomepage());
			output.put("signature",user.getSignature());
			output.put("avatar",user.getAvatar());
			output.put("biography",user.getBiography());
			output.put("location",user.getLocation());
			output.put("occupation",user.getOccupation());
			output.put("icq",user.getIcqNumber());
			output.put("aim",user.getAimAddress());
			output.put("msn",user.getMsnAddress());
			output.put("yahoo",user.getYahooAddress());
			output.put("lang",user.getLanguage());

			return output;	
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
