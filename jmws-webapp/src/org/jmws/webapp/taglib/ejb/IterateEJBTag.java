/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | IterateEJBTag - Defines a tag for iterating a Collection of               |
 * |                 EJB Objects into a JSP tag.                               |
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

import java.util.Collection;
import java.util.Iterator;
import javax.ejb.EJBObject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * @author Mikael Barbeaux
 */
public class IterateEJBTag extends BodyTagSupport implements BodyTag {

	// iterator upon the Collection of EJB instances
	private Iterator iterator;
	
	// EJBs type
	private String type;
	
	// Position
	private int pos;
	
	// Max objects to iterate
	private int max;

	/**
	 * @return max iterations
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @return type of instances
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param collection
	 */
	public void setCollection(Collection collection) {
		this.iterator = collection.iterator();
	}

	/**
	 * @param i
	 */
	public void setMax(int i) {
		max = i;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}
	
	/**
	 * @return evaluation ok
	 * @throws JspException
	 */
	public int doStartTag() throws JspException {
		return doAfterBody();
	}
	
	/**
	 * @return end of evaluation
	 */
	public int doEndTag() {
		this.max = -1;
		this.pos = 0;
		return EVAL_PAGE;
	}
	
	/**
	 * @return Evaluation
	 * @throws JspException
	 */
	public int doAfterBody() throws JspException
	{
		// As long as there are things to iterate...
		if(iterator.hasNext() && (max < 0 || pos++ < max)) {
			try{
				// Get the instance
				EJBObject instance = (EJBObject) 
					javax.rmi.PortableRemoteObject.narrow(
						iterator.next(), 
						Class.forName(type, true, 
							pageContext.getPage().getClass().getClassLoader()));
			
				// save it
				pageContext.setAttribute(this.getId(), instance);
				// Evaluate tag's body
				return BodyTag.EVAL_BODY_INCLUDE;
			}
			catch(ClassNotFoundException e) {
				throw new JspException("ClassNotFoundException:" + e.getMessage());
			}
		}
		else {
			// finish body
			try {
				if(bodyContent != null)
					bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}
			catch(java.io.IOException e) {
				throw new JspException("IO Error: " + e.getMessage());
			}
			return BodyTag.SKIP_BODY;
		}
	}

}
