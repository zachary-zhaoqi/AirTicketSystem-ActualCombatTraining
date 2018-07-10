package com.softfz.model;

import java.io.Serializable;
import java.util.Date;

public class SaleLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8351439306674828119L;
	private int logid;
	private long saleid;
	private int netid;
	private Date operatordate;
	private String operatortype;
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public long getSaleid() {
		return saleid;
	}
	public void setSaleid(long saleid) {
		this.saleid = saleid;
	}
	public int getNetid() {
		return netid;
	}
	public void setNetid(int netid) {
		this.netid = netid;
	}
	public Date getOperatordate() {
		return operatordate;
	}
	public void setOperatordate(Date operatordate) {
		this.operatordate = operatordate;
	}
	public String getOperatortype() {
		return operatortype;
	}
	public void setOperatortype(String operatortype) {
		this.operatortype = operatortype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
