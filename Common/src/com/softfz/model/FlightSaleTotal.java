package com.softfz.model;

import java.io.Serializable;

public class FlightSaleTotal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5853472709582814871L;
	private String flightno;//航班编号
	private String airline;//所属航空公司
	private String month;//月份
	private long ticketnum;//票数
	private long ticketmoney;//票价汇总
	private long turnnum;//退票数
	private long turnmoney;//退票额
	private long totalmoney;//销售汇总
	public String getFlightno() {
		return flightno;
	}
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(long ticketnum) {
		this.ticketnum = ticketnum;
	}
	public long getTicketmoney() {
		return ticketmoney;
	}
	public void setTicketmoney(long ticketmoney) {
		this.ticketmoney = ticketmoney;
	}
	
	public long getTurnnum() {
		return turnnum;
	}
	public void setTurnnum(long turnnum) {
		this.turnnum = turnnum;
	}
	public long getTurnmoney() {
		return turnmoney;
	}
	public void setTurnmoney(long turnmoney) {
		this.turnmoney = turnmoney;
	}
	public long getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(long totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	
}
