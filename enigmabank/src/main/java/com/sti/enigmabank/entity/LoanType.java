package com.sti.enigmabank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loan_type")
public class LoanType {

	@Id
	@Column(name="loan_type_code")
	private String loanTypeCode;
	
	@Column(name="loan_type_desc")
	private String loanTypeDescription;
	
	public String getLoanTypeCode() {
		return loanTypeCode;
	}
	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}
	public String getLoanTypeDescription() {
		return loanTypeDescription;
	}
	public void setLoanTypeDescription(String loanTypeDescription) {
		this.loanTypeDescription = loanTypeDescription;
	}
	
	
}
