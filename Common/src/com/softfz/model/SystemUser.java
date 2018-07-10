package com.softfz.model;

import java.io.Serializable;

public class SystemUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8863716205543323989L;
	private int userid;
	private String username;
	private String password;
	private String email;
	private String state;
	private String realname;
	private String telephone;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "SystemUser [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", state="
				+ state + ", realname=" + realname + ", telephone=" + telephone
				+ "]";
	}
	
	
}
