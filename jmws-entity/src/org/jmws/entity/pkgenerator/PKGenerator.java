/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | PKGenerator - Defines the EntityBean object for the                       |
 * |               Primary Key Generator.                                      |
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
 
package org.jmws.entity.pkgenerator;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * 
 * 
 * @author Mikael Barbeaux
 */
public abstract class PKGenerator implements EntityBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/entity/PKGenerator";
	
	// TABLE name
	public static final String TABLE_NAME = "jmws_pkgenerator";
	
	
	/**
	 * CMP fields
	 */
	
	public abstract String getTable();
	public abstract void setTable(String table);
	
	public abstract Long getPkValue();
	public abstract void setPkValue(Long pkValue);
	
	
	
	/**
	 * Returns the next primary key value of the
	 * given table name.
	 * 
	 * @param table
	 * @return
	 */
	public Long getNextPK(String table) {
		// Get current value and increment
		Long pkVal = this.getPkValue();
		this.setPkValue(new Long(pkVal.longValue()+1));
		
		// Return current value
		return pkVal;
	}
	
	
	/**
	 * Create a new PKGenerator for the given table name.
	 * 
	 * @param table
	 * @return
	 * @throws CreateException
	 */
	public String ejbCreate(
		String table
	) throws CreateException {
		// Set fields
		this.setTable(table);
		this.setPkValue(new Long(1));
		
		// Return primary key value
		return table;
	}
	
	
	/**
	 * Code to process after PKGenerator's creation.
	 * @param table
	 * @throws CreateException
	 */
	public void ejbPostCreate(
		String table
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
