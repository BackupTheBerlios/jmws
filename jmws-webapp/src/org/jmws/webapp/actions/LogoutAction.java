/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | LogoutAction - Defines a Struts Action for logging out.                   |
 * |                                                                           |
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
 
package org.jmws.webapp.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jmws.webapp.Constants;

/**
 * LogoutAction
 * 
 * @author MikaelB
 */
public class LogoutAction extends Action {

	/**
	 * @param mapping - Action mapping
	 * @param form - Associated form
	 * @param request - Request
	 * @param response - Response
	 * @return Forwarding
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		// Test if User is connected
		HttpSession session = request.getSession();
		if(session.getAttribute(Constants.LOGIN_KEY) != null) {
			// Delete sessions keys
			session.removeAttribute(Constants.USER_KEY);
			session.removeAttribute(Constants.LOGIN_KEY);
			
			// Save success messages and forward to success page
			ActionMessages messages = new ActionMessages();
			messages.add("logged out", 
					new ActionMessage("www.index.logout.success"));
			saveMessages(request, messages);			
			return mapping.findForward("success");
		}
		else {
			// Save error and forward to failed page
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.index.errors.logout")
			);
			saveErrors(request, errors);	
			return mapping.findForward("failed");
		}
		


	}


}
