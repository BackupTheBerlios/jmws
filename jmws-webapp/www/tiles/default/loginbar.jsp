<%@ page language="java" %>
<%--
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | template1.jsp - Default web template for JMWS.                            |
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
--%>

<%@ page import="org.jmws.webapp.Constants" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="org.apache.struts.util.MessageResources" %>
<%@ page import="java.util.Locale" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
	// Get User's locale
	Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
	
	// Get Messages resources handler
	MessageResources messages = (MessageResources) 
			request.getAttribute(Globals.MESSAGES_KEY);
%>

<table background="<%= request.getContextPath() %>/images/login_bg.png" 
	width="100%" height="30" bgcolor="#FF9900" cellspacing="0" 
	cellpadding="0" border="0">
<tr>

<%
	if(session.getAttribute(Constants.USER_KEY) == null) {
%>
	<%-- User isn't connected,
		then we must display a connect zone --%>

	<td align="right"><strong>Quick Log in :</strong>&nbsp; &nbsp;</td>

	<td align="left"><html:form action="/login">
	<table height="30">
	<tr>
		<td align="left"><html:text property="login" 
			value="<%= messages.getMessage(locale, \"www.login.login\") %>" /></td>
		<td align="left"><html:password property="password" 
			value="<%= messages.getMessage(locale, \"www.login.password\") %>"/></td>
		<td align="left"><html:submit><bean:message
			key="www.login.submit" /></html:submit></td>
	</tr>
	</table>
	</html:form></td>
	
	<td align="right"><a href="#" class="black">Register an account</a>
	&nbsp; &nbsp;</td>
<%
	}
	else {
%>	
	<td>Welcome, <b>Administrator</b></td>
<%
	}

%>
</tr>
</table>
