package com.sti.enigmabank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentDto {
	
	private Integer billingId;
	private Double paymentAmount;
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
