/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | PKGenerator - Defines a Java class for handling PKGenerator               |
 * |               EntityBean.                                                 |
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
import org.jmws.entity.pkgenerator.PKGeneratorBean;
import org.jmws.entity.pkgenerator.PKGeneratorLocal;
import org.jmws.entity.pkgenerator.PKGeneratorLocalHome;

/**
 * PKGenerator
 * 
 * @author MikaelB
 */
public class PKGenerator {
	
	public static Long getNextId(String table)
			throws NamingException, CreateException {
		//	Get PKGenerator Home interface
		InitialContext context = new InitialContext();
		PKGeneratorLocalHome pkgen_home;
		Object tmp = context.lookup(PKGeneratorBean.JNDI_NAME);
		pkgen_home = (PKGeneratorLocalHome) tmp;

		// Get next value of PKGenerator
		PKGeneratorLocal pkgen_local = null;
		try {
			pkgen_local = 
				pkgen_home.findByPrimaryKey(table);
		}
		catch(FinderException fe) {
			pkgen_local = pkgen_home.create(table);
		}
		Long key = pkgen_local.getKey();
		pkgen_local.increment();
		 
		return key;
	}

}
