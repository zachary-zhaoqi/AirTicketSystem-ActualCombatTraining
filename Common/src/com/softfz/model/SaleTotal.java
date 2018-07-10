package com.softfz.model;

import java.io.Serializable;

public class SaleTotal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -220591707172968957L;
	private String month;//销售月份：如201301
	private String flightno;//航班编号
	private String airline;//航空公司
	private long ticketmoney;//销售票价
	private int ticketnum;//销售的数量
	private long turnmoney;//退票数量
	private int turnnum;//退票金额
	private long totalmoney;//销售汇总=卖了多少钱-退了多少钱
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
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
	public long getTicketmoney() {
		return ticketmoney;
	}
	public void setTicketmoney(long ticketmoney) {
		this.ticketmoney = ticketmoney;
	}
	public int getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(int ticketnum) {
		this.ticketnum = ticketnum;
	}
	public long getTurnmoney() {
		return turnmoney;
	}
	public void setTurnmoney(long turnmoney) {
		this.turnmoney = turnmoney;
	}
	public int getTurnnum() {
		return turnnum;
	}
	public void setTurnnum(int turnnum) {
		this.turnnum = turnnum;
	}
	public long getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(long totalmoney) {
		this.totalmoney = totalmoney;
	}
	@Override
	public String toString() {
		return "SaleTotal [month=" + month + ", flightno=" + flightno
				+ ", airline=" + airline + ", ticketmoney=" + ticketmoney
				+ ", ticketnum=" + ticketnum + ", turnmoney=" + turnmoney
				+ ", turnnum=" + turnnum + ", totalmoney=" + totalmoney + "]";
	}
	
	
}
