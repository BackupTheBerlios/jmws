/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | JMWSValidator - Defines a Struts Validator for JMWS                       |
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
 
package org.jmws.webapp.validator;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.FieldChecks;

/**
 * JMWSValidator
 * 
 * @author MikaelB
 */
public class JMWSValidator extends FieldChecks {

	/**
	 * @param obj - object
	 * @param action - validator action
	 * @param field - field to validate
	 * @param errors - action errors
	 * @param request - request
	 * @return true if validation is ok
	 */
	public static boolean validateAndActiveSubmit(
		Object obj,
		ValidatorAction action,
		Field field,
		ActionErrors errors,
		HttpServletRequest request	
	) {
		return JMWSValidator.validateRequired(
			obj,
			action,
			field,
			errors,
			request
		);
	}

}
