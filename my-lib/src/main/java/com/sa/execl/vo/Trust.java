package com.sa.execl.vo;

import java.io.Serializable;

public class Trust implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033969920092548876L;
	
	private String name;
	private String address;
	private String phone;
	private String pan;
	private String regnNo;
	private String regnDate;
	private String treasurerName;
	private String tax = "true";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String pnone) {
		this.phone = pnone;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRegnNo() {
		return regnNo;
	}
	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}
	public String getRegnDate() {
		return regnDate;
	}
	public void setRegnDate(String regnDate) {
		this.regnDate = regnDate;
	}
	public String getTreasurerName() {
		return treasurerName;
	}
	public void setTreasurerName(String treasurerName) {
		this.treasurerName = treasurerName;
	}
	
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	@Override
	public String toString() {
		
		return name+ "-"+ regnNo;
	}

}
