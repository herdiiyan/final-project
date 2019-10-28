package com.sti.enigmabank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="billing")
public class Billing {
	@Id
	@Column(name="billing_id")
	private Integer billingId;

	@Column(name="loan_id")
	private Integer loanId;

	@Column(name="billing_amount")
	private Double billingAmount;

	@Column(name="bill_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date billDate;

	@Column(name="billing_due_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date billingDueDate;

	@Column(name="billing_status")
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
