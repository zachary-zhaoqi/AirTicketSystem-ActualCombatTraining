package com.softfz.model;

import java.io.Serializable;
import java.util.Date;

public class Discount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7445419511543844568L;
	private long discountid;
	private int flightid;
	private double discount;
	private Date discountdate;
	
	public long getDiscountid() {
		return discountid;
	}
	public void setDiscountid(long discountid) {
		this.discountid = discountid;
	}
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Date getDiscountdate() {
		return discountdate;
	}
	public void setDiscountdate(Date discountdate) {
		this.discountdate = discountdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
