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

import com.sti.enigmabank.dto.LoanDto;
import com.sti.enigmabank.entity.Loan;
import com.sti.enigmabank.services.LoanDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="loan",produces = "application/json; charset=UTF-8")
public class LoanController {

	@Autowired
	LoanDao loanDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	
	private static final String LOAN_PATH_VARIABLE_LOAN_ID ="loanId";
	private static final String LOAN_PATH_VARIABLE_LOAN_STATUS ="loanStatus";
	private static final String LOAN_REQ_PARAM_ACCOUNT_NUMBER ="accountNumber";
	private static final String LOAN_REQ_PARAM_CUSTOMER_CIF ="customerCif";
	private static final String LOAN_REQ_PARAM_LOAN_TENOR ="loanTenor";
	private static final String LOAN_REQ_PARAM_OPEN_DATE ="openDate";
	private static final String LOAN_REQ_PARAM_DUE_DATE ="dueDate";
	private static final String LOAN_REQ_PARAM_LOAN_TYPE_CODE ="loanTypeCode";
	private static final String LOAN_ADDR = "";
	private static final String LOAN_BY_LOAN_ID_ADDR = LOAN_ADDR + "/{"+LOAN_PATH_VARIABLE_LOAN_ID+"}";
	private static final String LOAN_BY_LOAN_STATUS_ADDR = LOAN_ADDR + "/status/{"+LOAN_PATH_VARIABLE_LOAN_STATUS+"}";
	private static final String LOAN_BY_ACCOUNT_NUMBER_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_ACCOUNT_NUMBER;
	private static final String LOAN_BY_OPEN_DATE_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_OPEN_DATE;
	private static final String LOAN_BY_DUE_DATE_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_DUE_DATE;
	private static final String LOAN_BY_LOAN_TENOR_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_LOAN_TENOR;
	private static final String LOAN_BY_LOAN_TYPE_CODE_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_LOAN_TYPE_CODE;
	private static final String LOAN_BY_CUSTOMER_CIF_ADDR = LOAN_ADDR + LOAN_REQ_PARAM_CUSTOMER_CIF;
	private static final String LOAN_PAGE_ADDR = LOAN_ADDR + "/page";

	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_ADDR)
	public String getAllLoan() throws Exception{
		List<LoanDto> listLoanDto = loanDao.getAllLoan().stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(LOAN_ADDR)
	public String addLoan(@RequestBody LoanDto loan) throws Exception{
		LoanDto loanDto = modelMapper.map(loanDao.addLoan(loan), LoanDto.class);
		CommonResponse<LoanDto> response = comGen.generateCommonResponse(loanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_LOAN_ID_ADDR)
	public String getLoanByLoanId(@PathVariable(value = "loanId") Integer loanId) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByLoanId(loanId).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(LOAN_BY_LOAN_ID_ADDR)
	public String updateLoan(@PathVariable(value = "loanId") Integer loanId, @RequestBody LoanDto upLoan) throws Exception{
		LoanDto loanDto = modelMapper.map(loanDao.updateLoan(loanId, upLoan), LoanDto.class);
		CommonResponse<LoanDto> response = comGen.generateCommonResponse(loanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_ACCOUNT_NUMBER_ADDR)
	public String getLoanByAccountNumber(@RequestParam(value = "accountNumber") Integer accountNumber) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByAccountNumber(accountNumber).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_LOAN_STATUS_ADDR)
	public String getLoanByLoanStatus(@PathVariable(value = "loanStatus") int loanStatus) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByLoanStatus(loanStatus).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_OPEN_DATE_ADDR)
	public String getLoanByOpenDate(@RequestParam(value = "openDate") String openDate) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByOpenDate(openDate).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_DUE_DATE_ADDR)
	public String getLoanByDueDate(@RequestParam(value = "dueDate") String dueDate) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByDueDate(dueDate).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_LOAN_TENOR_ADDR)
	public String getLoanByLoanTenor(@RequestParam(value = "loanTenor") Integer loanTenor) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByLoanTenor(loanTenor).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_LOAN_TYPE_CODE_ADDR)
	public String getLoanByLoanTypeCode(@RequestParam(value = "loanTypeCode") String loanTypeCode) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByLoanTypeCode(loanTypeCode).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_BY_CUSTOMER_CIF_ADDR)
	public String getLoanByCustomerCif(@RequestParam(value = "customerCif") String customerCif) throws Exception{
		List<LoanDto> listLoanDto = loanDao.getLoanByCustomerCif(customerCif).stream().map(l -> modelMapper.map(l, LoanDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanDto>> response = comGen.generateCommonResponse(listLoanDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(LOAN_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Loan> pageAccount = loanDao.findPaging(page);
		CommonResponse<Page<Loan>> response = comGen.generateCommonResponse(pageAccount);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
}
