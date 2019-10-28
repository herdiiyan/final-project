package com.sti.enigmabank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="loan")
public class Loan {


	@Id
	@Column(name="loan_id")
	private Integer loanId;
	
	@Column(name="account_number")
	private Integer accountNumber;
	
	@Column(name="loan_amount")
	private Double loanAmount;

	@Column(name="loan_balance")
	private Double loanBalance;

	@Column(name="loan_status")
	private int loanStatus;

	@Column(name="loan_open_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date openDate;

	@Column(name="loan_due_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dueDate;

	@Column(name="loan_tenor")
	private Integer loanTenor;

	@Column(name="loan_type_code")
	private String loanTypeCode;

	@Column(name="customer_cif")
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
