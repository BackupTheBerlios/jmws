<%--
/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | index.php - Homepage for JMWS                                             |
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
--%>


<%@ page language="java"%>
<%@ page import="org.jmws.session.user.*"%>
<%@ page import="org.jmws.webapp.Constants"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html locale="true">

	<head>
    	<html:base />
    	<html:javascript formName="LoginFormBean" />
    	<script language="JavaScript" src="md5.js"></script> 
        
    	<title>Index</title>
    
  	</head>
  
  <%
  	if(request.getParameter("success") != null) {
  %>
  
  	<%-- When User has been successfully loggued --%>
  
  	<body>
	<logic:messagesPresent message="true">
		<html:messages id="loggued" message="true">
			<bean:write name="loggued" />
		</html:messages>
	</logic:messagesPresent>
	
	<br><br>
	<a href="/jmws/usersettings.jsp">Enter your settings</a><br>
	<a href="/jmws/index.jsp">Back to index</a>

  <%
  	}
  	else if(session.getAttribute(Constants.LOGIN_KEY) != null) {
  %>

	Already logged.<br><br>
	<a href="/jmws/usersettings.jsp">Modify your settings</a><br>
	<a href="/jmws/logout.do"><bean:message key="www.index.logout" /></a>
	
  <%
  	}
  	else if(request.getParameter("failed") != null) {
  %>
  
  	<%-- When User log in throws an error --%>
  	
  	<body>
	<html:errors />	
	
	<br><br>
	<a href="/jmws/index.jsp">Retry</a>

  <%
  	}
  	else if(request.getParameter("logout") != null) {
  %>
  
  	<%-- When User log in throws an error --%>
  	
  	<body>
	Successfully logged out.
	
	<br><br>
	<a href="/jmws/index.jsp">Back to index</a>

  <%
  	}
  	else if(request.getParameter("logoutfailed") != null) {
  %>
  
  	<%-- When User log in throws an error --%>
  	
  	<body>
	<html:errors />	
	
	<br><br>
	<a href="/jmws/index.jsp">Back to index</a>
	
  <%
  	}
  	else {
  %>
  
  	<%-- Display index page --%>
  
  	<body onload="validateLoginFormBean(document.forms['LoginFormBean']);">
  	<b>Log in :</b><br><br>
  	  <html:form action="/login" method="post" focus="username">
  	  	<table border="0">
        <tr>
          <td><bean:message key="www.index.username" />:</td>
          <td><html:text property="username"
          		altKey="www.index.username.alt"
          		onkeypress="validateLoginFormBean(this.form);"
        	  	onkeyup="validateLoginFormBean(this.form);"
          		onkeydown="validateLoginFormBean(this.form);" /></td>
        </tr>
        <tr>
          <td><bean:message key="www.index.password" />:</td>
          <td><html:password property="password"
          		altKey="www.index.password.alt"
          		onkeypress="validateLoginFormBean(this.form);"
        	  	onkeyup="validateLoginFormBean(this.form);"
          		onkeydown="validateLoginFormBean(this.form);" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html:submit 
          		onclick="crypt(this.form); return true;"
          		ondblclick="crypt(this.form); return true;" >
          	<bean:message key="www.index.login.submit" />
          </html:submit><html:reset>
          	<bean:message key="www.index.login.reset" />
          </html:reset></td>
        </tr>
  	  	</table>
  	  </html:form>
  	  <a href="/jmws/newuser.jsp"><bean:message key="www.index.newuser" /></a>
  	  
  <%
  	}
  %>
  		
  	</body>
  	
</html:html>
