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

<table background="<%= request.getContextPath() %>/images/login_bg.png" 
	width="100%" height="30" bgcolor="#FF9900" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td><a href="#" class="black">Log in</a></td>
	<td><a href="#" class="black">Register an account</a></td>
	<td align="center">
		<input type="text" value="User Name">
		<input type="password" value="Password">
		<input type="button" value="Log in">
	</td>
	
	<td align=right>Welcome, <b>Administrator</b></td>
</tr>
</table>
