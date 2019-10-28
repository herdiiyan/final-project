package com.sti.enigmabank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.Billing;
@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
	
	List<Billing> findBillingByLoanId(Integer loanId);
	List<Billing> findBillingByBillDate(Date billDate);
	List<Billing> findBillingByBillingDueDate(Date billingDueDate);
	List<Billing> findBillingByBillingStatus(int billingStatus);

}
