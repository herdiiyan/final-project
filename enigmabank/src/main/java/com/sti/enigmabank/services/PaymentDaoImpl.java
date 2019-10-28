package com.sti.enigmabank.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.enigmabank.dto.PaymentDto;
import com.sti.enigmabank.entity.Payment;
import com.sti.enigmabank.repository.PaymentRepository;

public class PaymentDaoImpl implements PaymentDao{

	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public List<Payment> getAllPayment() throws Exception {
		List<Payment> paymentList = new ArrayList<>();
		try {
			paymentList = paymentRepo.findAll();
			if (paymentList.isEmpty()) {
				throw new Exception("Payment List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return paymentList;
	}

	@Override
	public List<Payment> getPaymentByBillingId(Integer billingId) throws Exception {
		Optional<Payment> paymentOpt;
		List<Payment> listPayment = new ArrayList<>();
		try {
			paymentOpt = paymentRepo.findById(billingId);
			if (!paymentOpt.isPresent()) {
				throw new Exception("No Payment Found With Billing ID " + billingId);
			}
			listPayment.add(paymentOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listPayment;
	}

	@Override
	public Payment addPayment(PaymentDto paymentDto) throws Exception {
		Payment payment = new Payment();
		try {
			payment = modelMapper.map(paymentDto, Payment.class);
			if (payment.getBillingId() == null) {
				throw new Exception("Billing Id Can't be Empty");
			}
			Optional<Payment> exist = paymentRepo.findById(payment.getBillingId());
			if (exist.isPresent()) {
				throw new Exception("Payment With Billing Id " + payment.getBillingId() + " Already Exist");
			}
		} catch (Exception e) {
			throw e;
		}
		return paymentRepo.save(payment);
	}

	@Override
	public Payment updatePayment(Integer billingId, PaymentDto paymentDto) throws Exception {
		Payment payment = new Payment();
		Optional<Payment> paymentOpt;
		try {
			Payment updatePayment = modelMapper.map(paymentDto, Payment.class);
			paymentOpt = paymentRepo.findById(billingId);
			if (!paymentOpt.isPresent()) {
				throw new Exception("No Payment Found With Billing ID" + billingId);			
			}
			payment = paymentOpt.get();
			payment.setPaymentAmount(updatePayment.getPaymentAmount());
		} catch (Exception e) {
			throw e;
		}
		return paymentRepo.save(payment);
	}
	
	@Override
	public List<Payment> getPaymentByPaymentDate(String paymentDate) throws Exception {
		DateTime dt = fmt.parseDateTime(paymentDate);
		Date currentDate = new DateTime(dt).plusDays(1).toDate();
		List<Payment> listPayment;
		try {
			listPayment = paymentRepo.findPaymentByPaymentDate(currentDate);
			if(listPayment.isEmpty()) {
				throw new Exception("No Payment Found By Date : "+paymentDate);
			}
		} catch (Exception e) {
			throw e;
		}
		return listPayment;
	}

	@Override
	public Page<Payment> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<Payment> paging = paymentRepo.findAll(pageable);
		return paging;
	}

	
	
}
