package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.LoanDto;
import com.sti.enigmabank.entity.Loan;

public interface LoanDao {

	List<Loan> getAllLoan() throws Exception;
	List<Loan> getLoanByLoanId(Integer loanId) throws Exception;
	Loan addLoan(LoanDto loanDto) throws Exception;
	Loan updateLoan(Integer loanId, LoanDto updateLoanDto) throws Exception;
	List<Loan> getLoanByAccountNumber(Integer accountNumber) throws Exception;
	List<Loan> getLoanByLoanStatus(int status) throws Exception;
	List<Loan> getLoanByOpenDate(String date) throws Exception;
	List<Loan> getLoanByDueDate(String date) throws Exception;
	List<Loan> getLoanByLoanTenor(Integer tenor) throws Exception;
	List<Loan> getLoanByLoanTypeCode(String loanTypeCode) throws Exception;
	List<Loan> getLoanByCustomerCif(String cif) throws Exception;
	Page<Loan> findPaging(int page) throws Exception;
}
