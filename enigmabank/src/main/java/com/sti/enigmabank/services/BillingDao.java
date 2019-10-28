package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.BillingDto;
import com.sti.enigmabank.entity.Billing;

public interface BillingDao {

	List<Billing> getAllBilling() throws Exception;
	List<Billing> getBillingByBillingId(Integer billingId) throws Exception;
	Billing addBilling(BillingDto billingDto) throws Exception;
	Billing updateBilling(Integer billingId, BillingDto billingDto) throws Exception;
	List<Billing> getBillingByLoanId(Integer loanId) throws Exception;
	List<Billing> getBillingByBillDate(String billDate) throws Exception;
	List<Billing> getBillingByBillingDueDate(String billingDueDate) throws Exception;
	List<Billing> getBillingByBillingStatus(int billingStatus) throws Exception;
	Page<Billing> findPaging(int page) throws Exception;
}
