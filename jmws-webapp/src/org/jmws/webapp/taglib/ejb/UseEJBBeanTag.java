/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UseEJBBeanTag - Defines a tag for handling EJB Bean objects               |
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
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * UseEJBBeanTag
 * 
 * @author Mikael Barbeaux
 */
public class UseEJBBeanTag extends TagSupport implements Tag {

	// identifiant for the EJB object.
	private String id;
	
	// type of the EJB object.
	private String type;
	
	// value of the EJB object.
	private Object value;
	
	// scope where to save the EJB object.
	private String scope;
	
	/**
	 * @return identifiant for the EJB object.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return scope where to save the EJB object.
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @return type of the EJB object.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return value of the EJB object.
	 */
	public Object getValue() {
		return value;
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
	public void setScope(String string) {
		scope = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @param object
	 */
	public void setValue(Object object) {
		value = object;
	}
	
	/**
	 * @return Evaluation of the tag body if an errors occurs, else skip body.
	 * @throws JspException
	 */
	public int doStartTag() throws JspException	{
		
		// if a value were specified
		if(value != null) {
			
			// Verify that it's a EJB object.
			setValue();

			// Save it with the given scope.
			if(scope != null) {
				
				if(scope.equals("session"))
					pageContext.setAttribute(this.getId(), 
							value, 
							PageContext.SESSION_SCOPE);
					
				else if(scope.equals("request"))
					pageContext.setAttribute(this.getId(), 
							value, 
							PageContext.REQUEST_SCOPE);
							
				else if(scope.equals("application"))
					pageContext.setAttribute(this.getId(), 
							value, 
							PageContext.APPLICATION_SCOPE);
			}
				
			return SKIP_BODY;
		}
		// No value specified, we need to retrieve it manually
		// and save it into the given scope.
		else if(scope != null) {
			
			if(scope.equals("session"))	{
				
				HttpSession session = pageContext.getSession();
				if(session != null)	{					
					Object object = session.getAttribute(this.getId());
					if(object instanceof EJBObject)	{
						pageContext.setAttribute(this.getId(), object);
						return SKIP_BODY;
					}
				}
			}
			
			else if(scope.equals("request")) {
				
				Object object = pageContext.getRequest().getAttribute(this.getId());
				if(object instanceof EJBObject)	{
					pageContext.setAttribute(this.getId(), object);
					return SKIP_BODY;
				}
			}
			
			else if(scope.equals("application")) {
				
				Object object = pageContext.getServletContext().getAttribute(this.getId());
				if(object instanceof EJBObject)	{
					pageContext.setAttribute(this.getId(), object);
					return SKIP_BODY;
				}
			}
		}
		
		// An error occurs => evaluate body content
		return EVAL_BODY_INCLUDE;		
	}
		
	/**
	 * @throws JspException
	 */
	private void setValue() throws JspException
	{
		try {	
			if(!(value instanceof EJBLocalObject))
				value = (EJBObject) javax.rmi.PortableRemoteObject.narrow(
					value, 
					Class.forName(type, true, 
						pageContext.getPage().getClass().getClassLoader()));

			pageContext.setAttribute(this.getId(), value);
		}
		catch(ClassNotFoundException e)
		{
			throw new JspException("ClassNotFoundException: " + e.getMessage());
		}
	}
	
	/**
	 * @return Evaluation of the rest of the page
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	

}
