	package com.softfz.model;

import java.io.Serializable;

public class Oiltax implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3132217226069664227L;
	private int breakpoint;
	private int lowfee;
	private int highfee;
	public int getBreakpoint() {
		return breakpoint;
	}
	public void setBreakpoint(int breakpoint) {
		this.breakpoint = breakpoint;
	}
	public int getLowfee() {
		return lowfee;
	}
	public void setLowfee(int lowfee) {
		this.lowfee = lowfee;
	}
	public int getHighfee() {
		return highfee;
	}
	public void setHighfee(int highfee) {
		this.highfee = highfee;
	}
	
	
}
