/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UseEJBHomeTEI - Defines the TagExtraInfo for JSP tag UseEJBHomeTag        |
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

package org.jmws.webapp.taglib.ejb;

import javax.ejb.*;
import javax.naming.*;
import javax.servlet.jsp.tagext.*;

/**
 * UseEJBHomeTEI
 * 
 * @author Mikael Barbeaux
 */
public class UseEJBHomeTEI extends TagExtraInfo
{
	/**
	 * @return Infos
	 */
	public VariableInfo[] getVariableInfo(TagData data)
	{
		// Resolving EJB type
		String type = (String) data.getAttribute("type");
		if(type == null) {
			EJBMetaData metaData = 
				getEJBMetaData((String) data.getAttribute("location"));
			if(metaData != null)
				type = metaData.getHomeInterfaceClass().getName();
			else
				throw new RuntimeException("Unable to determine " +					"type of EJBHome at location '" + 
					(String)data.getAttribute("location"));
		}

		// Building output
		VariableInfo info = new VariableInfo(data.getId(), type, true, VariableInfo.AT_END);
		VariableInfo[] back = { info };
		return back;
	}

	/**
	 * @param location
	 * @return EJBMetaData
	 */
	public static EJBMetaData getEJBMetaData(String location) {
		try {
			// Gety EJBHome interface
			EJBHome home = (EJBHome) 
				javax.rmi.PortableRemoteObject.narrow(
					new InitialContext().lookup(location), 
					EJBHome.class
				);
			
			return home.getEJBMetaData();
		}
		catch(ClassCastException e) {
		}
		catch(NamingException e) {
		}
		catch(java.rmi.RemoteException e) {
		}
		
		return null;
	}
}