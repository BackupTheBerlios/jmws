/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | LoginAction - Defines a Struts Action for logging in.                     |
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

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jmws.session.connect.UserLoginBean;
import org.jmws.session.connect.UserLoginHome;
import org.jmws.session.connect.UserLoginRemote;
import org.jmws.webapp.Constants;

/**
 * LoginAction
 * 
 * @author MikaelB
 */
public class LoginAction extends Action {

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
		// Retrieve form parameters
		String username = null;
		String password = null;
		try {
			username = (String) 
				PropertyUtils.getSimpleProperty(form, "username");
			password = (String)
				PropertyUtils.getSimpleProperty(form, "password");
		}
		catch (Exception e) {			
		} 
		
		ActionErrors errors = new ActionErrors();
		
		// Verification
		if((username == null) || (username.equals(""))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.index.errors.username.empty")
			);
		}
		if((password == null) || 
			(password.equals("d41d8cd98f00b204e9800998ecf8427e"))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.index.errors.password.empty")
			);
		}

		// Does an error occurs ?
		if(! errors.isEmpty()) {
			// Save errors into request and forward to failed page
			saveErrors(request, errors);	
			return mapping.findForward("failed");
		}
		else {
			/** -= Log in =- */
			try {
				// Retrive UserLogin Home interface
				InitialContext context = new InitialContext();
				UserLoginHome home;
				Object obj = context.lookup(UserLoginBean.JNDI_NAME);
				home = (UserLoginHome) obj;
	
				// Get the Remote interface of UserLogin bean
				UserLoginRemote remote = home.create();
	
				// Try to log in..
				boolean log = remote.login(username, password);
				if(! log)
					throw new Exception();
				
				// Save login state and username into session
				HttpSession session = request.getSession();
				session.setAttribute(Constants.LOGIN_KEY, Boolean.TRUE); 
				session.setAttribute(Constants.USER_KEY, username);
											
				// Save success messages and forward to success page
				ActionMessages messages = new ActionMessages();
				messages.add("logged", 
						new ActionMessage("www.index.login.success"));
				saveMessages(request, messages);
				return mapping.findForward("success");				
			}
			catch (Exception e) {
				// Save error and forward to failed page
				errors = new ActionErrors();
				errors.add(
					ActionErrors.GLOBAL_ERROR, 
					new ActionError("www.index.errors.login")
				);
				saveErrors(request, errors);	
				return mapping.findForward("failed");
			} 		
		}
	}

}
