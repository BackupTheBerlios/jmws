<%--
/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | newuser.php - JSP page for creating of a new User into JMWS               |
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
    <html:javascript formName="NewUserFormBean" />
    <script language="JavaScript" src="md5.js"></script> 
    
    <title>New User</title>
    
  </head>
  
  <%
  	if(request.getParameter("success") != null) {
  %>
  
  	<%-- When User has been successfully created --%>
  
  	<body>
	<logic:messagesPresent message="true">
		<html:messages id="created" message="true">
			<bean:write name="created" />
		</html:messages>
	</logic:messagesPresent>
	
	<br><br>
	<a href="/jmws/index.jsp">Back to index</a>
	
  <%
  	}
  	else if(request.getParameter("failed") != null) {
  %>
  
  	<%-- When User creation throws an error --%>
  	
  	<body>
	<html:errors />	
	<br><br>
	<a href="/jmws/newuser.jsp">Retry</a><br>
	<a href="/jmws/index.jsp">Back to index</a>
	
  <%
  	}
  	else {
  %>
  
  	<%-- Display creation page --%>
  	
  	<body onload="validateNewUserFormBean(document.forms['NewUserFormBean']);">
  	<b>Create a new User :</b><br><br>
    <html:form action="/newUser" method="post" focus="username">
      <table border="0">
        <tr>
          <td><bean:message key="www.newuser.username" />:</td>
          <td><html:text property="username"
          		altKey="www.newuser.username.alt"
          		onkeypress="validateNewUserFormBean(this.form);"
        	  	onkeyup="validateNewUserFormBean(this.form);"
          		onkeydown="validateNewUserFormBean(this.form);" /></td>
        </tr>
        <tr>
          <td><bean:message key="www.newuser.password" />:</td>
          <td><html:password property="password" 
          		altKey="www.newuser.password.alt"
          		onkeypress="validateNewUserFormBean(this.form);"
        	  	onkeyup="validateNewUserFormBean(this.form);"
          		onkeydown="validateNewUserFormBean(this.form);" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html:submit 
          		onclick="crypt(this.form); return true;"
          		ondblclick="crypt(this.form); return true;" >
          	<bean:message key="www.newuser.submit" />
          </html:submit></td>
        </tr>
      </table>
    </html:form>
    
    <a href="/jmws/index.jsp">Back to index</a>
    
  <%
  	}
  %>
  
  </body>
</html:html>
