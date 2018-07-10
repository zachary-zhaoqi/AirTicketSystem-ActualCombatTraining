package com.softfz.model;

import java.io.Serializable;

public class NetDealer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7957357203506689673L;
	private int netid;
	private int userid;
	private String netcode;
	private String netname;
	private String password;
	private String director;
	private String telphone;
	private String state;
	private String address;
	private String rowid;
	
	
	
	@Override
	public String toString() {
		return "NetDealer [netid=" + netid + ", userid=" + userid
				+ ", netcode=" + netcode + ", netname=" + netname
				+ ", password=" + password + ", director=" + director
				+ ", telphone=" + telphone + ", state=" + state + ", address="
				+ address + ", rowid=" + rowid + "]";
	}
	public int getNetid() {
		return netid;
	}
	public void setNetid(int netid) {
		this.netid = netid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getNetcode() {
		return netcode;
	}
	public void setNetcode(String netcode) {
		this.netcode = netcode;
	}
	public String getNetname() {
		return netname;
	}
	public void setNetname(String netname) {
		this.netname = netname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	
}
