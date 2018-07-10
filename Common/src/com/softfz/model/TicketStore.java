package com.softfz.model;

import java.io.Serializable;
import java.util.Date;

public class TicketStore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1961151416001287459L;
	private int storeid;
	private int flightid;
	private Date ticketdate;
	private String fromcity;
	private String tocity;
	private int storenum;
	private int cityseq;
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public Date getTicketdate() {
		return ticketdate;
	}
	public void setTicketdate(Date ticketdate) {
		this.ticketdate = ticketdate;
	}
	public String getFromcity() {
		return fromcity;
	}
	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}
	public String getTocity() {
		return tocity;
	}
	public void setTocity(String tocity) {
		this.tocity = tocity;
	}
	public int getStorenum() {
		return storenum;
	}
	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}
	public int getCityseq() {
		return cityseq;
	}
	public void setCityseq(int cityseq) {
		this.cityseq = cityseq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
