package com.sti.enigmabank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@Column(name="account_number")
	private Integer accountNumber;

	@Column(name="customer_cif")
	private String customerCif;

	@Column(name="account_balance")
	private Double accountBalance;

	@Column(name="created_at")
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
