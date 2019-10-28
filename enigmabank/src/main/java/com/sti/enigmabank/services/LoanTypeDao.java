package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.LoanTypeDto;
import com.sti.enigmabank.entity.LoanType;

public interface LoanTypeDao {

	List<LoanType> getAllLoanType() throws Exception;
	List<LoanType> getLoanTypeByLoanTypeCode(String loanTypeCode) throws Exception;
	LoanType addLoanType(LoanTypeDto loanTypeDto) throws Exception;
	String deleteLoanType(String loanTypeCode) throws Exception;
	LoanType updateLoanType(String loanTypeCode, LoanTypeDto updateLoanTypeDto) throws Exception;
	Page<LoanType> findPaging(int page) throws Exception;
}
