/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLogInAction - Defines the Struts Action for User logging in.          |
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

package org.jmws.webapp.action;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import org.jmws.session.user.login.UserInactiveException;
import org.jmws.session.user.login.UserLogIn;
import org.jmws.session.user.login.UserLogInHome;
import org.jmws.session.user.login.UserLogInRemote;
import org.jmws.session.user.login.UserWrongPasswordException;
import org.jmws.webapp.Constants;

/**
 * UserLogInAction
 * 
 * @author Mikael Barbeaux
 */
public class UserLogInAction extends Action {

	/**
	 * Executes the UserLogIn Struts Action.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		
		// retrieve form's parameters
		String login = null;
		String password = null;
		
		try {
			login = (String) PropertyUtils.getSimpleProperty(form, "login");
			password = (String) PropertyUtils.getSimpleProperty(form, "password");
		}
		catch(Exception e) {
		}
		
		// Form validation
		ActionErrors errors = new ActionErrors();
		if((login == null) || (login.equals("")))
			errors.add(ActionErrors.GLOBAL_ERROR, 
					new ActionError("errors.login.login.notfound"));
		else if((password == null) || (password.equals("")))
			errors.add(ActionErrors.GLOBAL_ERROR, 
					new ActionError("errors.login.password.notfound"));
					
		// If any errors, forward errors to input page
		if(! errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		else {
			
			try {
				// JNDI naming context
				InitialContext context = new InitialContext();
			
				// Get the UserLogInHome interface
				UserLogInHome home;
				Object obj = context.lookup(UserLogIn.JNDI_NAME);
				home = (UserLogInHome) obj;
				
				// Get a new UserLogIn session bean.
				UserLogInRemote remote = home.create();
				
				// Checks log in for the specified User.
				Boolean logged = remote.checkLogIn(login, password);
				
				// Check failed ?
				if(logged.equals(Boolean.FALSE)) {
					// Forward the error to input page
					errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR,
						new ActionError("errors.login.failed"));
					saveErrors(request, errors);
					return mapping.getInputForward();
				}
				// Check succeeded ?
				else {
					// Save User logged in state into Session
					HttpSession session = request.getSession();
					session.setAttribute(Constants.USER_KEY, login);
					
					// Forward to success page
					return mapping.findForward("success");
				} 
			}
			catch(NamingException ne) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.global.namingexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			catch(RemoteException re) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.global.remoteexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();				
			}
			catch(CreateException ce) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.global.createexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();					
			}
			catch(UserWrongPasswordException uspe) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.login.userwrongpasswordexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();					
			}
			catch(UserInactiveException uia) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.login.userinactiveexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();					
			}
			catch(FinderException fe) {
				// Forward the error to input page
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("errors.global.finderexception"));
				saveErrors(request, errors);
				return mapping.getInputForward();					
			}
		}
	}


}
