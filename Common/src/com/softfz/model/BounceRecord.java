package com.softfz.model;

import java.io.Serializable;
import java.util.Date;

public class BounceRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446016138278586304L;
	private int bounceid;
	private int saleid;
	private int netid;
	private Date bouncedate;
	private String idcard;
	private String custtel;
	private String reason;
	private int money;
	public int getBounceid() {
		return bounceid;
	}
	public void setBounceid(int bounceid) {
		this.bounceid = bounceid;
	}
	public int getSaleid() {
		return saleid;
	}
	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}
	public int getNetid() {
		return netid;
	}
	public void setNetid(int netid) {
		this.netid = netid;
	}
	public Date getBouncedate() {
		return bouncedate;
	}
	public void setBouncedate(Date bouncedate) {
		this.bouncedate = bouncedate;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCusttel() {
		return custtel;
	}
	public void setCusttel(String custtel) {
		this.custtel = custtel;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	

}
