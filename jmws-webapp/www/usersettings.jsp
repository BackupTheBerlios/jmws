<%--
/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | usersettings.php - JSP page for modifying User settings into JMWS         |
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
<%@ page import="java.util.HashMap" %>
<%@ page import="org.jmws.webapp.Constants" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jmws-ejb.tld" prefix="ejb" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    <html:javascript formName="UpdateUserSettingsFormBean" />
    <script language="JavaScript" src="md5.js"></script> 
    
    <title>Update User settings</title>
    
  </head>

  <%
  	if(session.getAttribute(Constants.LOGIN_KEY) == null) {
  %>
  	Not logged.
  	
  	<br><br>
	<a href="/jmws/index.jsp">Back to index</a>	
	
  <%
  	}
  	else if(request.getParameter("success") != null) {
  %>
  
  	<%-- When User has been successfully created --%>
  
  	<body>
	<logic:messagesPresent message="true">
		<html:messages id="updated" message="true">
			<bean:write name="updated" />
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
	<a href="/jmws/usersettings.jsp">Retry</a><br>
	<a href="/jmws/index.jsp">Back to index</a>
	
  <%
  	}
  	else {
  %>
  
  	<%-- Display creation page --%>
  	
	<body onload="validateUpdateUserSettingsFormBean(document.forms['UpdateUserSettingsFormBean']);">

	<%-- Get the UserInfos bean --%>
	
	<ejb:useHome id="infosHome" location="jmws/session/UserInfos"
			type="org.jmws.session.user.UserInfosHome" />
			
	<ejb:useBean id="infos" scope="page" 
			type="org.jmws.session.user.UserInfosRemote">  
		<ejb:createBean instance="<%= infosHome.create() %>" /> 
	</ejb:useBean>
	
	<%	
		HashMap	all = infos.getAllInfosAbout((String) 
				session.getAttribute(Constants.USER_KEY));
	%>
	
    <html:form action="/updateUserSettings" method="post" focus="fullname">
      <html:hidden property="username" 
      		value="<%= (String) all.get(\"username\") %>" />    
      <table border="0">
        <tr>
          <td align="right"><font color="red">
          	<bean:message key="www.usersettings.fullname" />:
          </font></td>
          <td><html:text property="fullname"
          		altKey="www.usersettings.fullname.alt"
          		onkeypress="validateUpdateUserSettingsFormBean(this.form);"
        	  	onkeyup="validateUpdateUserSettingsFormBean(this.form);"
          		onkeydown="validateUpdateUserSettingsFormBean(this.form);"
          		value="<%= (String) all.get(\"fullname\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.newpassword" />:</td>
          <td><html:password property="newpassword"
          		altKey="www.usersettings.newpassword.alt" /></td>
        </tr>
        <tr>
          <td align="right"><font color="red">
          	<bean:message key="www.usersettings.email" />:
          </font></td>
          <td><html:text property="email"
          		altKey="www.usersettings.email.alt"
          		onkeypress="validateAndActiveSubmit(this.form);"
        	  	onkeyup="validateAndActiveSubmit(this.form);"
          		onkeydown="validateAndActiveSubmit(this.form);"
          		value="<%= (String) all.get(\"email\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.homepage" />:</td>
          <td><html:text property="homepage"
          		altKey="www.usersettings.homepage.alt"
          		value="<%= (String) all.get(\"homepage\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.sig" />:</td>
          <td><html:textarea property="signature"
          		altKey="www.usersettings.sig.alt"
          		value="<%= (String) all.get(\"signature\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.avatar" />:</td>
          <td><html:file property="avatar"
          		altKey="www.usersettings.avatar.alt"
          		value="<%= (String) all.get(\"avatar\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.biography" />:</td>
          <td><html:textarea property="biography"
          		altKey="www.usersettings.biography.alt"
          		value="<%= (String) all.get(\"biography\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.location" />:</td>
          <td><html:text property="location"
          		altKey="www.usersettings.location.alt"
          		value="<%= (String) all.get(\"location\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.occupation" />:</td>
          <td><html:text property="occupation"
          		altKey="www.usersettings.occupation.alt"
          		value="<%= (String) all.get(\"occupation\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.icq" />:</td>
          <td><html:text property="icq"
          		altKey="www.usersettings.icq.alt"
          		value="<%= (String) all.get(\"icq\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.aim" />:</td>
          <td><html:text property="aim"
          		altKey="www.usersettings.aim.alt"
          		value="<%= (String) all.get(\"aim\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.msn" />:</td>
          <td><html:text property="msn"
          		altKey="www.usersettings.msn.alt"
          		value="<%= (String) all.get(\"msn\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.yahoo" />:</td>
          <td><html:text property="yahoo"
          		altKey="www.usersettings.yahoo.alt"
          		value="<%= (String) all.get(\"yahoo\") %>" /></td>
        </tr>
        <tr>
          <td align="right"><bean:message key="www.usersettings.lang" />:</td>
          <td><html:text property="lang"
          		altKey="www.usersettings.lang.alt"
          		value="<%= (String) all.get(\"lang\") %>" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          <html:submit onclick="crypt(this.form); return validateEmail(this.form);"
          		ondblclick="crypt(this.form); return validateEmail(this.form);">
          	<bean:message key="www.usersettings.submit" />
          </html:submit>&nbsp;&nbsp;
          <html:reset>
          	<bean:message key="www.usersettings.reset" />
          </html:reset>
          </td>
        </tr>
      </table>
    </html:form>
    <br>
	<a href="/jmws/index.jsp">Back to index</a>
    
  <%
  	}
  %>
    
  </body>
</html:html>
