/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UpdateUserSettingsAction - Defines a Struts Action for updating           |
 * |                 			the User settings.                             |
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

import java.net.URL;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jmws.session.user.UpdateUserBean;
import org.jmws.session.user.UpdateUserHome;
import org.jmws.session.user.UpdateUserRemote;

/**
 * UpdateUserSettingsAction
 * 
 * @author MikaelB
 */
public class UpdateUserSettingsAction extends Action {

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
		String fullname = null;
		String newpassword = null;
		String email = null;
		String homepage = null;
		URL homep = null;
		String signature = null;
		String avatar = null;
		String biography = null;
		String location = null;
		String occupation = null;
		String icq = null;
		String aim = null;
		String msn = null;
		String yahoo = null;
		String lang = null;
		try {
			username = (String) 
				PropertyUtils.getSimpleProperty(form, "username");
			fullname = (String) 
				PropertyUtils.getSimpleProperty(form, "fullname");
			newpassword = (String)
				PropertyUtils.getSimpleProperty(form, "newpassword");
			email = (String)
					PropertyUtils.getSimpleProperty(form, "email");
			homepage = (String)
					PropertyUtils.getSimpleProperty(form, "homepage");
			homep = new URL(homepage);
			signature = (String)
					PropertyUtils.getSimpleProperty(form, "signature");
			avatar = (String)
					PropertyUtils.getSimpleProperty(form, "avatar");
			biography = (String)
					PropertyUtils.getSimpleProperty(form, "biography");
			location = (String)
					PropertyUtils.getSimpleProperty(form, "location");
			occupation = (String)
					PropertyUtils.getSimpleProperty(form, "occupation");
			icq = (String)
					PropertyUtils.getSimpleProperty(form, "icq");
			aim = (String)
					PropertyUtils.getSimpleProperty(form, "aim");
			msn = (String)
					PropertyUtils.getSimpleProperty(form, "msn");
			yahoo = (String)
					PropertyUtils.getSimpleProperty(form, "yahoo");
			lang = (String)
					PropertyUtils.getSimpleProperty(form, "lang");
		}
		catch (Exception e) {			
		}	
		
		ActionErrors errors = new ActionErrors();

		// Verification
		if((username == null) || (username.equals(""))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.usersettings.errors.username.empty")
			);
		}
		if((fullname == null) || (fullname.equals(""))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.usersettings.errors.fullname.empty")
			);
		}
		if((email == null) || (email.equals(""))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.usersettings.errors.email.empty")
			);
		}
		if((email != null) && (! GenericValidator.isEmail(email))) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.usersettings.errors.email.notvalid")
			);
		}
		if((homepage != null) && (!homepage.equals("")) && (homep == null)) {
			errors.add(
				ActionErrors.GLOBAL_ERROR, 
				new ActionError("www.usersettings.errors.homepage.notvalid")
			);
		}
		if(newpassword.equals("d41d8cd98f00b204e9800998ecf8427e"))
			newpassword = "";

		// Does an error occurs ?
		if(! errors.isEmpty()) {
			// Save errors into request and forward to failed page
			saveErrors(request, errors);	
			return mapping.findForward("failed");
		}
		else {	
			/** -= Update User settings =- */
			
			try {
				// Retrive CreateUser Home interface
				InitialContext context = new InitialContext();
				UpdateUserHome home;
				Object obj = context.lookup(UpdateUserBean.JNDI_NAME);
				home = (UpdateUserHome) obj;
				
				// Get the Remote interface of CreateUser bean
				UpdateUserRemote remote = home.create();
				
				// Update user
				remote.updateUser(
					username,
					fullname,
					newpassword,
					email,
					homepage,
					signature,
					avatar,
					biography,
					location,
					occupation,
					icq,
					aim,
					msn,
					yahoo,
					lang	
				);
				
				// Save success messages and forward to success page
				ActionMessages messages = new ActionMessages();
				messages.add("updated", 
					new ActionMessage("www.usersettings.success"));
				saveMessages(request, messages);
				return mapping.findForward("success");		
			}
			catch(Exception e) {
				// Save error and forward to failed page
				errors = new ActionErrors();
				errors.add(
					ActionErrors.GLOBAL_ERROR, 
					new ActionError("www.usersettings.errors.update")
				);
				saveErrors(request, errors);	
				return mapping.findForward("failed");
			}
		}
	}


}
