package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.PaymentDto;
import com.sti.enigmabank.entity.Payment;

public interface PaymentDao {

	List<Payment> getAllPayment() throws Exception;
	List<Payment> getPaymentByBillingId(Integer billingId) throws Exception;
	Payment addPayment(PaymentDto paymentDto) throws Exception;
	Payment updatePayment(Integer billingId, PaymentDto paymentDto) throws Exception;
	List<Payment> getPaymentByPaymentDate(String paymentDate) throws Exception;
	Page<Payment> findPaging(int page) throws Exception;
	
	
}
