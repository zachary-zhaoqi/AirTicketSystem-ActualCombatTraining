package com.softfz.model;

import java.io.Serializable;
import java.util.Date;

public class SaleRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3798247207445332906L;
	private int saleid;
	private int netid;
	private int flightid;
	private int ticketmoney;
	private Date saletime;
	private int airporttax;
	private int oiltax;
	private String custname;
	private String custtel;
	private String idcard;
	private String startairport;
	private String endairpotr;
	private Date arrtime;
	private Date starttime;
	private String salestate;
	private String fromcity;
	private String tocity;
	
	private int storeid;//°ó¶¨µÄÆ±id
	
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
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public int getTicketmoney() {
		return ticketmoney;
	}
	public void setTicketmoney(int ticketmoney) {
		this.ticketmoney = ticketmoney;
	}
	public Date getSaletime() {
		return saletime;
	}
	public void setSaletime(Date saletime) {
		this.saletime = saletime;
	}
	public int getAirporttax() {
		return airporttax;
	}
	public void setAirporttax(int airporttax) {
		this.airporttax = airporttax;
	}
	public int getOiltax() {
		return oiltax;
	}
	public void setOiltax(int oiltax) {
		this.oiltax = oiltax;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCusttel() {
		return custtel;
	}
	public void setCusttel(String custtel) {
		this.custtel = custtel;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getStartairport() {
		return startairport;
	}
	public void setStartairport(String startairport) {
		this.startairport = startairport;
	}
	public String getEndairpotr() {
		return endairpotr;
	}
	public void setEndairpotr(String endairpotr) {
		this.endairpotr = endairpotr;
	}
	public Date getArrtime() {
		return arrtime;
	}
	public void setArrtime(Date arrtime) {
		this.arrtime = arrtime;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getSalestate() {
		return salestate;
	}
	public void setSalestate(String salestate) {
		this.salestate = salestate;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
}
