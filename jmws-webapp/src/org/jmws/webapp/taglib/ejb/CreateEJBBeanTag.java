/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | CreateEJBBeanTag - Defines a tag for creating EJB Bean objects            |
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

import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * CreateEJBBeanTag
 * 
 * @author Mikael Barbeaux
 */
public class CreateEJBBeanTag extends TagSupport implements Tag {

	// instance of the created EJB object.
	private Object instance;

	/**
	 * @return instance of the created EJB object.
	 */
	public Object getInstance() {
		return instance;
	}

	/**
	 * @param object
	 */
	public void setInstance(Object object) {
		instance = object;
	}
	
	/**
	 * @return Skip body's tag evaluation.
	 */
	public int doStartTag() throws JspTagException {
		
		// error if instance is null
		if(instance == null)
			throw new NullPointerException("instance was null");

		// Get parent tag - always a useEJBBean
		UseEJBBeanTag beanTag = (UseEJBBeanTag) 
				TagSupport.findAncestorWithClass(this, UseEJBBeanTag.class);
		if(beanTag == null)
			throw new JspTagException("ejb:createBean tags must always " +				"appear within a ejb:useBean tag");
		
		try {
			// Get instance of EJB object.
			Object instance;
			if(this.instance instanceof EJBLocalObject)
				instance = this.instance;
			else instance = (EJBObject) PortableRemoteObject.narrow(
					this.instance, 
					Class.forName(beanTag.getType(), true, 
					pageContext.getPage().getClass().getClassLoader()));
			
			// Save instance into page context		
			pageContext.setAttribute(beanTag.getId(), instance);
			// Set value of useBean parent tag
			beanTag.setValue(instance);
			
			// save instance into scope
			if(beanTag.getScope() != null) {
				if(beanTag.getScope().equals("session")) {
						HttpSession session = pageContext.getSession();
						if(session != null)
							session.setAttribute(beanTag.getId(), instance);
				}
				
				else if(beanTag.getScope().equals("request")) {
					pageContext.getRequest().setAttribute(beanTag.getId(), instance);
				}
				
				else if(beanTag.getScope().equals("application")) {
					pageContext.getServletContext().setAttribute(beanTag.getId(), instance);
				}
			}
		}
		catch(ClassNotFoundException e) {
			throw new JspTagException("EJB class not found: " + 
				beanTag.getType() + " (" + e.getMessage() + ")");
		}
		
			return SKIP_BODY;
	}

}
