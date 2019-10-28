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

import com.sti.enigmabank.dto.BillingDto;
import com.sti.enigmabank.dto.PaymentDto;
import com.sti.enigmabank.entity.Billing;
import com.sti.enigmabank.entity.Payment;
import com.sti.enigmabank.repository.BillingRepository;

public class BillingDaoImpl implements BillingDao {

	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private BillingRepository billingRepo;
	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public List<Billing> getAllBilling() throws Exception {
		List<Billing> billingList = new ArrayList<>();
		try {
			billingList = billingRepo.findAll();
			if (billingList.isEmpty()) {
				throw new Exception("Billing List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return billingList;
	}

	@Override
	public List<Billing> getBillingByBillingId(Integer billingId) throws Exception {
		Optional<Billing> billingOpt;
		List<Billing> listBilling = new ArrayList<>();
		try {
			billingOpt = billingRepo.findById(billingId);
			if (!billingOpt.isPresent()) {
				throw new Exception("No Billing Found With ID " + billingId);
			}
			listBilling.add(billingOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listBilling;
	}

	@Override
	public Billing addBilling(BillingDto billingDto) throws Exception {
		Billing billing = new Billing();
		Payment payment = new Payment();
		try {
			billing = modelMapper.map(billingDto, Billing.class);
			if(billing.getBillingStatus()==1) {
				DateTime dt = new DateTime();
				String stringDate = dt.getDayOfMonth()+"-"+dt.getMonthOfYear()+"-"+dt.getYear();
				DateTime ds = fmt.parseDateTime(stringDate);
				Date currentDate = new DateTime(ds).toDate();
				payment.setBillingId(billing.getBillingId());
				payment.setPaymentAmount(billing.getBillingAmount());
				payment.setPaymentDate(currentDate);
				PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
				paymentDao.addPayment(paymentDto);
			}
			if (billing.getBillingId() == null) {
				throw new Exception("Billing Id Can't be Empty");
			}
			Optional<Billing> exist = billingRepo.findById(billing.getBillingId());
			if (exist.isPresent()) {
				throw new Exception("Billing With Billing Id " + billing.getBillingId() + " Already Exist");
			}
		} catch (Exception e) {
			throw e;
		}
		return billingRepo.save(billing);
	}

	@Override
	public Billing updateBilling(Integer billingId, BillingDto billingDto) throws Exception {
		Billing billing = new Billing();
		Optional<Billing> billingOpt;
		Payment payment = new Payment();
		try {
			Billing updateBilling = modelMapper.map(billingDto, Billing.class);
			billingOpt = billingRepo.findById(billingId);
			if (!billingOpt.isPresent()) {
				throw new Exception("No Billing Found With ID" + billingId);			
			}
			billing = billingOpt.get();
			billing.setBillingAmount(updateBilling.getBillingAmount());
			billing.setBillingStatus(updateBilling.getBillingStatus());
			if(billing.getBillingStatus()==1) {
				DateTime dt = new DateTime();
				String stringDate = dt.getDayOfMonth()+"-"+dt.getMonthOfYear()+"-"+dt.getYear();
				DateTime ds = fmt.parseDateTime(stringDate);
				Date currentDate = new DateTime(ds).toDate();
				payment.setBillingId(billing.getBillingId());
				payment.setPaymentAmount(billing.getBillingAmount());
				payment.setPaymentDate(currentDate);
				PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
				paymentDao.addPayment(paymentDto);
			}
		} catch (Exception e) {
			throw e;
		}
		return billingRepo.save(billing);
	}

	@Override
	public List<Billing> getBillingByLoanId(Integer loanId) throws Exception {
		List<Billing> listBilling;
		try {
			listBilling = billingRepo.findBillingByLoanId(loanId);
			if(listBilling.isEmpty()) {
				throw new Exception("No Billing Found by Loan ID : "+loanId);
			}
		} catch (Exception e) {
			throw e;
		}
		return listBilling;
	}

	@Override
	public List<Billing> getBillingByBillDate(String billDate) throws Exception {
		DateTime dt = fmt.parseDateTime(billDate);
		Date currentDate = new DateTime(dt).plusDays(1).toDate();
		List<Billing> listBilling;
		try {
			listBilling = billingRepo.findBillingByBillDate(currentDate);
			if(listBilling.isEmpty()) {
				throw new Exception("No Billing Found By Bill Date : "+billDate);
			}
		} catch (Exception e) {
			throw e;
		}
		return listBilling;
	}

	@Override
	public List<Billing> getBillingByBillingDueDate(String billingDueDate) throws Exception {
		DateTime dt = fmt.parseDateTime(billingDueDate);
		Date currentDate = new DateTime(dt).plusDays(1).toDate();
		List<Billing> listBilling;
		try {
			listBilling = billingRepo.findBillingByBillingDueDate(currentDate);
			if(listBilling.isEmpty()) {
				throw new Exception("No Billing Found By Due Date : "+billingDueDate);
			}
		} catch (Exception e) {
			throw e;
		}
		return listBilling;
	}

	@Override
	public List<Billing> getBillingByBillingStatus(int billingStatus) throws Exception {
		List<Billing> listBilling;
		try {
			listBilling = billingRepo.findBillingByBillingStatus(billingStatus);
			if(listBilling.isEmpty()) {
				throw new Exception("No Billing Found with Status : "+billingStatus);
			}
		} catch (Exception e) {
			throw e;
		}
		return listBilling;
	}

	@Override
	public Page<Billing> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<Billing> paging = billingRepo.findAll(pageable);
		return paging;
	}

}
