package com.softfz.model;

import java.io.Serializable;

public class NetSaleTotal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5294191000257736860L;
	private String netcode;//网点编号
	private String netname;//网点名称
	private String month;//月份
	private long ticketnum;//售票数
	private long ticketmoney;//售票额
	private long turnnum;//退票数
	private long turnmoney;//退票额
	private long totalmoney;//销售总额
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
	@Override
	public String toString() {
		return "NetSaleTotal [netcode=" + netcode + ", netname=" + netname
				+ ", month=" + month + ", ticketnum=" + ticketnum
				+ ", ticketmoney=" + ticketmoney + ", turnnum=" + turnnum
				+ ", turnmoney=" + turnmoney + ", totalmoney=" + totalmoney
				+ "]";
	}
	
	
}
