/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserInfos - Defines the EntityBean object for the default UserInfos.      |
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

package org.jmws.entity.user.infos;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import org.jmws.entity.user.UserLocal;

/**
 * UserInfos
 * 
 * @author Mikael Barbeaux
 */
public abstract class UserInfos implements EntityBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/entity/UserInfos";
	
	// Database table name
	public static final String TABLE_NAME = "jmws_userinfos";
	
	
	/**
	 * CMP fields
	 */
	
	public abstract Long getId();
	public abstract void setId(Long id);
	
	
	
	/**
	 * CMR fields
	 */

	public abstract UserLocal getTheUser();
	public abstract void setTheUser(UserLocal theUser);

	
	
	/**
	 * Create a User informations bean.
	 * 
	 * @param id
	 * @return
	 * @throws CreateException
	 */
	public Long ejbCreate(Long id) throws CreateException {
		// Set fields
		this.setId(id);
		
		// Return UserInfos primary key
		return id;
	}
	
	/**
	 * Code to process after UserInfos' creation.
	 * 
	 * @param id
	 * @throws CreateException
	 */
	public void ejbPostCreate(Long id) throws CreateException {
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
