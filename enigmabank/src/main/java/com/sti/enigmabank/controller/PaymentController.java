package com.sti.enigmabank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.enigmabank.dto.PaymentDto;
import com.sti.enigmabank.entity.Payment;
import com.sti.enigmabank.services.PaymentDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="payment",produces = "application/json; charset=UTF-8")
public class PaymentController {

	@Autowired
	PaymentDao paymentDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	
	private static final String PAYMENT_PATH_VARIABLE_BILLING_ID ="billingId";
	private static final String PAYMENT_REQ_PARAM_PAYMENT_DATE ="paymentDate";
	private static final String PAYMENT_ADDR = "payment";
	private static final String PAYMENT_BY_BILLING_ID_ADDR = PAYMENT_ADDR + "/{"+PAYMENT_PATH_VARIABLE_BILLING_ID+"}";
	private static final String PAYMENT_BY_PAYMENT_DATE_ADDR = PAYMENT_ADDR + PAYMENT_REQ_PARAM_PAYMENT_DATE;
	private static final String PAYMENT_PAGE_ADDR = PAYMENT_ADDR + "/page";

	@CrossOrigin(origins = "*")
	@GetMapping(PAYMENT_ADDR)
	public String getAllPayment() throws Exception{
		List<PaymentDto> listPaymentDto = paymentDao.getAllPayment().stream().map(p -> modelMapper.map(p, PaymentDto.class)).collect(Collectors.toList());
		CommonResponse<List<PaymentDto>> response = comGen.generateCommonResponse(listPaymentDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(PAYMENT_ADDR)
	public String addPayment(@RequestBody PaymentDto payment) throws Exception{
		PaymentDto paymentDto = modelMapper.map(paymentDao.addPayment(payment), PaymentDto.class);
		CommonResponse<PaymentDto> response = comGen.generateCommonResponse(paymentDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(PAYMENT_BY_BILLING_ID_ADDR)
	public String getPaymentByBillingId(@PathVariable(value = "billingId") Integer billingId) throws Exception{
		List<PaymentDto> listPaymentDto = paymentDao.getPaymentByBillingId(billingId).stream().map(p -> modelMapper.map(p, PaymentDto.class)).collect(Collectors.toList());
		CommonResponse<List<PaymentDto>> response = comGen.generateCommonResponse(listPaymentDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(PAYMENT_BY_BILLING_ID_ADDR)
	public String updatePayment(@PathVariable(value = "billingId") Integer billingId, @RequestBody PaymentDto upPayment) throws Exception{
		PaymentDto paymentDto = modelMapper.map(paymentDao.updatePayment(billingId, upPayment), PaymentDto.class);
		CommonResponse<PaymentDto> response = comGen.generateCommonResponse(paymentDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(PAYMENT_BY_PAYMENT_DATE_ADDR)
	public String getPaymentByPaymentDate(@RequestParam(value = "paymentDate") String paymentDate) throws Exception{
		List<PaymentDto> listPaymentDto = paymentDao.getPaymentByPaymentDate(paymentDate).stream().map(p -> modelMapper.map(p, PaymentDto.class)).collect(Collectors.toList());
		CommonResponse<List<PaymentDto>> response = comGen.generateCommonResponse(listPaymentDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(PAYMENT_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Payment> pagePayment = paymentDao.findPaging(page);
		CommonResponse<Page<Payment>> response = comGen.generateCommonResponse(pagePayment);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
	
}
