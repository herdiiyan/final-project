package com.sti.enigmabank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="payment")
public class Payment {

	@Id
	@Column(name="billing_id")
	private Integer billingId;
	
	@Column(name="payment_amount")
	private Double paymentAmount;
	
	@Column(name="payment_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date paymentDate;
	
	public Integer getBillingId() {
		return billingId;
	}
	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
