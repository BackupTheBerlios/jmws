/*
 * +---------------------------------------------------------------------------+
 * | JMWS - Java Managed Web System                                            |
 * +---------------------------------------------------------------------------+
 * | UserLocal - Defines the EntityBean Local interface for                    |
 * |             the default User.                                             |
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
 
package org.jmws.entity.user;

import javax.ejb.EJBLocalObject;

import org.jmws.entity.user.infos.UserInfosLocal;

public interface UserLocal extends EJBLocalObject {
	
	/**
	 * Set the active state of this User.
	 * 
	 * @param active
	 */
	public void setActive(Boolean active);
	
	/**
	 * Set the Activator of this User.
	 * 
	 * @param activator
	 */
	public void setTheActivator(UserLocal theActivator);
	
	/**
	 * Set the UserInfos of this User.
	 * 
	 * @param theUserInfos
	 */
	public void setTheUserInfos(UserInfosLocal theUserInfos);
	
}
