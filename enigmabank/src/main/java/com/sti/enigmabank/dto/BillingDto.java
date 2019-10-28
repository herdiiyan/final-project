package com.sti.enigmabank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BillingDto {

	private Integer billingId;
	private Integer loanId;
	private Double billingAmount;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date billDate;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date billingDueDate;
	private int billingStatus;
	
	public Integer getBillingId() {
		return billingId;
	}
	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(Double billingAmount) {
		this.billingAmount = billingAmount;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Date getBillingDueDate() {
		return billingDueDate;
	}
	public void setBillingDueDate(Date billingDueDate) {
		this.billingDueDate = billingDueDate;
	}
	public int getBillingStatus() {
		return billingStatus;
	}
	public void setBillingStatus(int billingStatus) {
		this.billingStatus = billingStatus;
	}
	
	
	
}
