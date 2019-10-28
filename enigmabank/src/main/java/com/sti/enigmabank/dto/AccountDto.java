package com.sti.enigmabank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountDto {

	private Integer accountNumber;
	private String customerCif;
	private Double accountBalance;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date createdAt;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerCif() {
		return customerCif;
	}
	public void setCustomerCif(String customerCif) {
		this.customerCif = customerCif;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
