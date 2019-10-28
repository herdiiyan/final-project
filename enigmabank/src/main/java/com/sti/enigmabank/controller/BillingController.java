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

import com.sti.enigmabank.dto.BillingDto;
import com.sti.enigmabank.entity.Billing;
import com.sti.enigmabank.services.BillingDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="billing",produces = "application/json; charset=UTF-8")
public class BillingController {

	@Autowired
	BillingDao billingDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	

	private static final String BILLING_PATH_VARIABLE_BILLING_ID ="billingId";
	private static final String BILLING_REQ_PARAM_BILL_DATE ="billDate";
	private static final String BILLING_REQ_PARAM_BILLING_DUE_DATE ="billingDueDate";
	private static final String BILLING_REQ_PARAM_BILLING_ID ="loanId";
	private static final String BILLING_REQ_PARAM_BILLING_STATUS ="billingStatus";
	private static final String BILLING_ADDR = "";
	private static final String BILLING_BY_BILLING_ID_ADDR = BILLING_ADDR + "/{"+BILLING_PATH_VARIABLE_BILLING_ID+"}";
	private static final String BILLING_BY_LOAN_ID_ADDR = BILLING_ADDR + BILLING_REQ_PARAM_BILLING_ID;
	private static final String BILLING_BY_BILL_DATE_ADDR = BILLING_ADDR + BILLING_REQ_PARAM_BILL_DATE;
	private static final String BILLING_BY_BILLING_DUE_DATE_ADDR = BILLING_ADDR + BILLING_REQ_PARAM_BILLING_DUE_DATE;
	private static final String BILLING_BY_BILLING_STATUS_ADDR = BILLING_ADDR + BILLING_REQ_PARAM_BILLING_STATUS;
	private static final String BILLING_PAGE_ADDR = BILLING_ADDR + "/page";

	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_ADDR)
	public String getAllBilling() throws Exception{
		List<BillingDto> listBillingDto = billingDao.getAllBilling().stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(BILLING_ADDR)
	public String addBilling(@RequestBody BillingDto billing) throws Exception{
		BillingDto billingDto = modelMapper.map(billingDao.addBilling(billing), BillingDto.class);
		CommonResponse<BillingDto> response = comGen.generateCommonResponse(billingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_BY_BILLING_ID_ADDR)
	public String getBillingByBillingId(@PathVariable(value = "billingId") Integer billingId) throws Exception{
		List<BillingDto> listBillingDto = billingDao.getBillingByBillingId(billingId).stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(BILLING_BY_BILLING_ID_ADDR)
	public String updateBilling(@PathVariable(value = "billingId") Integer billingId, @RequestBody BillingDto upBilling) throws Exception{
		BillingDto billingDto = modelMapper.map(billingDao.updateBilling(billingId, upBilling), BillingDto.class);
		CommonResponse<BillingDto> response = comGen.generateCommonResponse(billingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_BY_LOAN_ID_ADDR)
	public String getBillingByLoanId(@RequestParam(value = "loanId") Integer loanId) throws Exception{
		List<BillingDto> listBillingDto = billingDao.getBillingByLoanId(loanId).stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_BY_BILL_DATE_ADDR)
	public String getBillingByBillDate(@RequestParam(value = "billDate") String billDate) throws Exception{
		List<BillingDto> listBillingDto = billingDao.getBillingByBillDate(billDate).stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_BY_BILLING_DUE_DATE_ADDR)
	public String getBillingByBillingDueDate(@RequestParam(value = "billingDueDate") String billingDueDate) throws Exception{
		List<BillingDto> listBillingDto = billingDao.getBillingByBillingDueDate(billingDueDate).stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(BILLING_BY_BILLING_STATUS_ADDR)
	public String getBillingByBillingStatus(@RequestParam(value = "billingStatus") int billingStatus) throws Exception{
		List<BillingDto> listBillingDto = billingDao.getBillingByBillingStatus(billingStatus).stream().map(b -> modelMapper.map(b, BillingDto.class)).collect(Collectors.toList());
		CommonResponse<List<BillingDto>> response = comGen.generateCommonResponse(listBillingDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(BILLING_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Billing> pageBilling = billingDao.findPaging(page);
		CommonResponse<Page<Billing>> response = comGen.generateCommonResponse(pageBilling);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
}
