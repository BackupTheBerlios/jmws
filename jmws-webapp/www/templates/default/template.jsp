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

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">

	<head>
		<title><tiles:getAsString name="title" /></title> 
		<link rel="STYLESHEET" type="text/css" 
			href="<%= request.getContextPath() %>/css/template1.css" />
	</head>
	
	<body>
		<div align="center">
		<table width="800" align="center" cellpadding="0" 
			cellspacing="0"	class="maintable">
		<tr>
		
			<%-- Banner --%>
			<td valign="top" align="left" width="800" class="tdmaintable">
				<tiles:insert attribute="banner" />
			</td>
			
		</tr>
		<tr>
			
			<%-- Menu bar --%>
			<td valign="top" align="left" width="800" class="tdmaintable">
				<tiles:insert attribute="menubar" />
			</td>
			
		</tr>
		<tr>
			
			<%-- Log In bar --%>
			<td valign="top" align="left" width="800" class="tdmaintable">
				<tiles:insert attribute="loginbar" />
			</td>
			
		</tr>
		<tr bgcolor="#FFFFFF">
			<td class="tdmaintable">
			<table width="100%" border="0" cellspacing="10" cellpadding="0">
			<tr>  
			
				<%-- Menu navigation --%>
				<td valign="top" align="left" width="200">
					<tiles:insert attribute="menu" />
				</td>	
			
			
				<%-- Body --%>
				<td valign="top" align="left" width="600">
					<tiles:insert attribute="body" />
				</td>	
			
			</tr>
			</table>
			</td>			
		</tr>
		<tr>
		
			<%-- Footer --%>
			<td class="tdmaintable" valign="top" align="left" width="800">
				<tiles:insert attribute="footer" />
			</td>
		</tr>
		</table>
		</div>
	</body>
</html:html> 
