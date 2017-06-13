/**
 * Vosao CMS. Simple CMS for Google App Engine.
 * 
 * Copyright (C) 2009-2010 Vosao development team.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * email: vosao.dev@gmail.com
 */

package org.vosao.entity;

import java.util.TimeZone;

import org.vosao.enums.UserRole;




/**
 * @author Alexander Oleynik
 */
public class UserEntity extends BaseEntityImpl {

	public static final long serialVersionUID = 4L;

	public String name;
	public String password;
	public String email;
	public UserRole role;
	public String forgotPasswordKey;
	public boolean disabled;
	public String timezone;
	
	public UserEntity() {
		role = UserRole.USER;
		disabled = false;
		timezone = TimeZone.getDefault().getID();
	}
	
	public UserEntity(String aName, String aPassword,
			String anEmail, UserRole aRole) {
		this();
		name = aName;
		password = aPassword;
		email = anEmail;
		role = aRole;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String aPassword) {
		password = aPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getRoleString() {
		if (role == null) {
			return "";
		}
		return role.name();
	}
	
	public boolean isAdmin() {
		if (role == null) {
			return false;
		}
		return role.equals(UserRole.ADMIN);
	}

	public boolean isSiteUser() {
		if (role == null) {
			return false;
		}
		return role.equals(UserRole.SITE_USER);
	}

	public boolean isUser() {
		if (role == null) {
			return false;
		}
		return role.equals(UserRole.USER);
	}
	
	public boolean isEditor() {
		return isAdmin() || isUser();
	}

	private String getForgotPasswordKey() {
		return forgotPasswordKey;
	}

	public void setForgotPasswordKey(String forgotPasswordKey) {
		this.forgotPasswordKey = forgotPasswordKey;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
}
