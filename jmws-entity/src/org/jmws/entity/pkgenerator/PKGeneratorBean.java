/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | PKGeneratorBean - Defines the EntityBean object for PKGenerator.          |
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
 * PKGeneratorBean
 * 
 * @author MikaelB
 */
public abstract class PKGeneratorBean implements EntityBean {

	// JNDI name
	public static final String JNDI_NAME = "jmws/entity/PKGenerator";
	// Table name
	public static final String TABLE_NAME = "jmws_pkgenerator";

	/**
	 * A PKGenetator is the association of a table name
	 * and a Long identifiant. It is used to generate
	 * uniques primary keys (type: Long) for each
	 * table from the database.
	 */
	
	public abstract String getTable();
	public abstract void setTable(String table);
	
	public abstract Long getKey();
	public abstract void setKey(Long key);


	/**
	 * @param table - Table name
	 * @return Current value of pk for table name
	 * @throws CreateException
	 */
	public String ejbCreate(
		String table
	) throws CreateException {
		Long key = new Long(0);
		this.setTable(table);
		this.setKey(key);
		return table;
	}

	/**
	 * @param table - Table name
	 * @throws CreateException
	 */
	public void ejbPostCreate(
		String table
	) throws CreateException {
	}
	
	/**
	 * Increment value of PKGenerator
	 */
	public void increment() {
		long key = this.getKey().longValue() + 1;
		this.setKey(new Long(key));
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
