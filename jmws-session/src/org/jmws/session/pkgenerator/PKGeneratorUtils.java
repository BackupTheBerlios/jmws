/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | PKGeneratorUtils - Defines a Java class for managing PKGenerators.        |
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
 
package org.jmws.session.pkgenerator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jmws.entity.pkgenerator.PKGenerator;
import org.jmws.entity.pkgenerator.PKGeneratorLocal;
import org.jmws.entity.pkgenerator.PKGeneratorLocalHome;

/**
 * PKGeneratorUtils
 * 
 * @author Mikael Barbeaux
 */
public class PKGeneratorUtils {

	/**
	 * Returns the next primary key value of the given
	 * table name.
	 * 
	 * @param table
	 * @return
	 * @throws NamingException - If PKGenerator doesn't exist
	 * @throws CreateException - If cannot create generator for table
	 */
	public static Long getNextPK(String table)
			throws NamingException, CreateException {
		
		// JNDI context naming
		InitialContext context = new InitialContext();
		
		// Get PKGeneratorLocalHome interface
		PKGeneratorLocalHome home;
		Object obj = context.lookup(PKGenerator.JNDI_NAME);
		home = (PKGeneratorLocalHome) obj;
		
		/* Try to retrieve the PKGeneratorLocal object 
		 * associated to the given table name */
		PKGeneratorLocal local = null;
		try {
			local = home.findByPrimaryKey(table);  
		}
		catch(FinderException fe) {
			/* PKGeneratorLocal object hasn't been found, so 
			 * we need to create it. */
			local = home.create(table);
		}
		
		// Return next primary key value of the given table name
		return local.getNextPK();
	}

}
