/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UseEJBHomeTag - Defines a tag for handling EJB Home interfaces            |
 * |                 into a JSP tag.                                           |
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

import javax.ejb.EJBHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.rmi.PortableRemoteObject;

/**
 * UseEJBHomeTag
 * 
 * @author Mikael Barbeaux
 */
public class UseEJBHomeTag extends TagSupport implements Tag {
			
	// identifiant for the EJB Home.
	private String id;
	
	// Type of the EJB Home.
	private String type;
	
	// JNDI name where to find the EJB Home.
	private String location;
	
	/**
	 * @return id used for the EJB Home.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return location of the EJB Home.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return type of the EJB Home.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string) {
		location = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}
	
	/**
	 * @return Evaluation of the tag body.
	 */
	public int doStartTag() throws JspException {
		try {	
	
			// Try to retrieve the EJB Home.
			InitialContext context = new InitialContext();
			EJBHome instance = (EJBHome) PortableRemoteObject.narrow( 
				(EJBHome) context.lookup(location),
					Class.forName(type, true, 
						pageContext.getPage().getClass().getClassLoader())
			);
			
			// Save this instance into page context.
			pageContext.setAttribute(id, instance);
			
			return EVAL_BODY_INCLUDE;					
		}
		catch(NamingException ne) {
			throw new JspException("NamingException: " + ne.getMessage());
		}
		catch(ClassNotFoundException cnfe) {
			throw new JspException("ClassNotFoundException: " + 
				cnfe.getMessage());
		}
	}
	

}
