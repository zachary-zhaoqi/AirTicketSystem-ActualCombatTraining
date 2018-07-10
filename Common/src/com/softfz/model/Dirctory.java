package com.softfz.model;

import java.io.Serializable;

public class Dirctory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 36744862655741603L;
	
	private int dicid;
	private String dicname;
	private int fatherid;
	public int getDicid() {
		return dicid;
	}
	public void setDicid(int dicid) {
		this.dicid = dicid;
	}
	public String getDicname() {
		return dicname;
	}
	public void setDicname(String dicname) {
		this.dicname = dicname;
	}
	public int getFatherid() {
		return fatherid;
	}
	public void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}

	
}
