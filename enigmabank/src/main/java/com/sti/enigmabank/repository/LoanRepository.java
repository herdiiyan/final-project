package com.sti.enigmabank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{

	List<Loan> findLoanByAccountNumber(Integer accountNumber);
	List<Loan> findLoanByLoanStatus(int status);
	List<Loan> findLoanByOpenDate(Date date);
	List<Loan> findLoanByDueDate(Date date);
	List<Loan> findLoanByLoanTenor(Integer tenor);
	List<Loan> findLoanByLoanTypeCode(String loanTypeCode);
	List<Loan> findLoanByCustomerCif(String customerCif);
}
