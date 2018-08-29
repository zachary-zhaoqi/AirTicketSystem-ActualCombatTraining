package com.softfz.model;

import java.io.Serializable;

public class Flight implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7753416497259893750L;
	private int flightid;
	private int userid;
	private int dicid;
	
	private String flightno;
	private String startairport;
	private String endairport;
	private int type;
	private String planstarttime;
	private String planendtime;
	private int airrange;
	private int price;
	private String fromcity;
	private String tocity;
	private String flighttype;
	private int ticketnum;
	private int isstop;
	
	private String airname;//所属航空公司名称
	private double discount;//折扣率
	private int storenum;//剩余票数
	private int storeid;//绑定的票id
	
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getDicid() {
		return dicid;
	}
	public void setDicid(int dicid) {
		this.dicid = dicid;
	}
	public String getFlightno() {
		return flightno;
	}
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}
	public String getStartairport() {
		return startairport;
	}
	public void setStartairport(String startairport) {
		this.startairport = startairport;
	}
	public String getEndairport() {
		return endairport;
	}
	public void setEndairport(String endairport) {
		this.endairport = endairport;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPlanstarttime() {
		return planstarttime;
	}
	public void setPlanstarttime(String planstarttime) {
		this.planstarttime = planstarttime;
	}
	public String getPlanendtime() {
		return planendtime;
	}
	public void setPlanendtime(String planendtime) {
		this.planendtime = planendtime;
	}
	public int getAirrange() {
		return airrange;
	}
	public void setAirrange(int airrange) {
		this.airrange = airrange;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getFlighttype() {
		return flighttype;
	}
	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}
	public int getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(int ticketnum) {
		this.ticketnum = ticketnum;
	}
	public int getIsstop() {
		return isstop;
	}
	public void setIsstop(int isstop) {
		this.isstop = isstop;
	}
	public String getAirname() {
		return airname;
	}
	public void setAirname(String airname) {
		this.airname = airname;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getStorenum() {
		return storenum;
	}
	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	
	
}
