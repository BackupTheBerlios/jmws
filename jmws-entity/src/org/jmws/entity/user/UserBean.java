/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserBean - Defines the EntityBean object for User.                        |
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
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * UserBean
 * 
 * @author MikaelB
 */
public abstract class UserBean implements EntityBean {
	
	// JNDI name
	public static final String JNDI_NAME = "jmws/entity/User";
	// Table name
	public static final String TABLE_NAME = "jmws_users";
	
	
	public abstract String getUsername();
	public abstract void setUsername(String username);
	
	public abstract Long getId();
	public abstract void setId(Long id);
	
	public abstract Boolean getUserActive(); 
	public abstract void setUserActive(Boolean userActive);
	
	public abstract String getPassword();
	public abstract void setPassword(String password);
	
	public abstract Date getRegisterDate();
	public abstract void setRegisterDate(Date registerDate);
	
	public abstract String getFullName();
	public abstract void setFullName(String fullName);
	
	public abstract String getEmail();
	public abstract void setEmail(String email);
	
	public abstract String getHomepage();
	public abstract void setHomepage(String homepage); 
	
	public abstract String getBiography();
	public abstract void setBiography(String biography);
	
	public abstract Date getLastVisit();
	public abstract void setLastVisit(Date lastVisit);
	
	public abstract String getLocation();
	public abstract void setLocation(String location);
	
	public abstract String getOccupation();
	public abstract void setOccupation(String occupation);
	
	public abstract String getIcqNumber();
	public abstract void setIcqNumber(String icqNumber);
	
	public abstract String getAimAddress();
	public abstract void setAimAddress(String aimAddress);
	
	public abstract String getMsnAddress();
	public abstract void setMsnAddress(String msnAddress);
	
	public abstract String getYahooAddress();
	public abstract void setYahooAddress(String yahooAddress);
	
	public abstract String getSignature();
	public abstract void setSignature(String signature);
	
	public abstract String getAvatar();
	public abstract void setAvatar(String avatar);
	
	public abstract Date getCookieTimeOut();
	public abstract void setCookieTimeOut(Date cookieTimeOut);
	
	public abstract String getLanguage();
	public abstract void setLanguage(String language);
	
	
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
	 */
	public void updateUser(
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
	) {
		this.setFullName(fullname);
		if((newpassword != null) && (! newpassword.equals("")))
			this.setPassword(newpassword);
		this.setEmail(email);
		this.setHomepage(homepage);
		this.setSignature(signature);
		this.setAvatar(avatar);
		this.setBiography(biography);
		this.setLocation(location);
		this.setOccupation(occupation);
		this.setIcqNumber(icq);
		this.setAimAddress(aim);
		this.setMsnAddress(msn);
		this.setYahooAddress(yahoo);
		this.setLanguage(lang);		
	}	
	
	/**
	 * @param username - User's username
	 * @param id - User's id
	 * @param password - User's password
	 * @return Instance of UserLocal
	 * @throws CreateException
	 */
	public String ejbCreate(
		String username,
		Long id,
		String password	
	) throws CreateException {
		this.setUsername(username);
		this.setId(id);
		this.setUserActive(Boolean.TRUE);
		this.setPassword(password);
		this.setRegisterDate(new Date());
				
		return username;	
	}
	
	/**
	 * @param username - User's username
	 * @param id - User's id
	 * @param password - User's password
	 * @throws CreateException
	 */
	public void ejbPostCreate(
		String username,
		Long id,
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
