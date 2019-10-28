package com.sti.enigmabank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanDto {

	private Integer loanId;
	
	private Integer accountNumber;
	
	private Double loanAmount;
	
	private Double loanBalance;
	
	private int loanStatus;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date openDate;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dueDate;
	
	private Integer loanTenor;
	
	private String loanTypeCode;
	
	private String customerCif;
	
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Double getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(Double loanBalance) {
		this.loanBalance = loanBalance;
	}

	public int getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(int loanStatus) {
		this.loanStatus = loanStatus;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getLoanTenor() {
		return loanTenor;
	}
	public void setLoanTenor(Integer loanTenor) {
		this.loanTenor = loanTenor;
	}
	public String getLoanTypeCode() {
		return loanTypeCode;
	}
	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}
	public String getCustomerCif() {
		return customerCif;
	}
	public void setCustomerCif(String customerCif) {
		this.customerCif = customerCif;
	}
	
	
	
}
