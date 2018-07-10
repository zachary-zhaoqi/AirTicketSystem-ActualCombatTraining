package com.softfz.model;

import java.io.Serializable;

public class FlightStop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3245915130035792084L;
	private int flightid;
	private String stopcity;
	private String stopairport;
	private String arrviedtime;
	private String againtime;
	private int stopprice;
	private int flightprice;
	private int airrange;
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public String getStopcity() {
		return stopcity;
	}
	public void setStopcity(String stopcity) {
		this.stopcity = stopcity;
	}
	public String getStopairport() {
		return stopairport;
	}
	public void setStopairport(String stopairport) {
		this.stopairport = stopairport;
	}
	public String getArrviedtime() {
		return arrviedtime;
	}
	public void setArrviedtime(String arrviedtime) {
		this.arrviedtime = arrviedtime;
	}
	public String getAgaintime() {
		return againtime;
	}
	public void setAgaintime(String againtime) {
		this.againtime = againtime;
	}
	public int getStopprice() {
		return stopprice;
	}
	public void setStopprice(int stopprice) {
		this.stopprice = stopprice;
	}
	public int getFlightprice() {
		return flightprice;
	}
	public void setFlightprice(int flightprice) {
		this.flightprice = flightprice;
	}
	public int getAirrange() {
		return airrange;
	}
	public void setAirrange(int airrange) {
		this.airrange = airrange;
	}
	
	
}
