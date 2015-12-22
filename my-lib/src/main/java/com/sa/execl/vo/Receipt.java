package com.sa.execl.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Receipt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1251242004068195473L;
	private String date;
	private String description;
	private long receiptNo;
	private String name;
	private String transferMode = "";
	private String chequeDetails = "";
	private BigDecimal credit;
	private BigDecimal debit;
	private String refferedBy = "";
	private String emailId = "";
	private String phoneNo = "";
	private String address = "";
	private Trust trust ;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(long receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransferMode() {
		return transferMode;
	}
	public void setTransferMode(String transferMode) {
		this.transferMode = transferMode;
	}
	public String getChequeDetails() {
		return chequeDetails;
	}
	public void setChequeDetails(String chequeDetails) {
		this.chequeDetails = chequeDetails;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public String getRefferedBy() {
		return refferedBy;
	}
	public void setRefferedBy(String refferedBy) {
		this.refferedBy = refferedBy;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		
		return name +" " + date + " " + receiptNo +  " "+ credit;
	}
	public Trust getTrust() {
		return trust;
	}
	public void setTrust(Trust trust) {
		this.trust = trust;
	}
}
	
