/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | PKGeneratorLocalHome - Defines the EntityBean LocalHome                   |
 * |                        interface for the Primary Key Generator.           |
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

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * PKGeneratorLocalHome
 * 
 * @author Mikael Barbeaux
 */
public interface PKGeneratorLocalHome extends EJBLocalHome {

	/**
	 * Create a new PKGenerator for the given table name.
	 * 
	 * @param table
	 * @return
	 * @throws CreateException
	 */
	public PKGeneratorLocal create(
		String table
	) throws CreateException;
	
	
	/**
	 * Find a PKGenerator using the given table name.
	 * 
	 * @param table
	 * @return
	 * @throws FinderException - If not found
	 */
	public PKGeneratorLocal findByPrimaryKey(String table)
		throws FinderException;

}
