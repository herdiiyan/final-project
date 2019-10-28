package com.sti.enigmabank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.enigmabank.dto.LoanTypeDto;
import com.sti.enigmabank.entity.LoanType;
import com.sti.enigmabank.services.LoanTypeDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="loan-type",produces = "application/json; charset=UTF-8")
public class LoanTypeController {

	@Autowired
	LoanTypeDao loanTypeDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;

	private static final String LOAN_TYPE_PATH_VARIABLE_TYPE_CODE ="loanTypeCode";
	private static final String LOAN_TYPE_ADDR = "lt";
	private static final String LOAN_TYPE_BY_LOAN_TYPE_CODE_ADDR = LOAN_TYPE_ADDR + "/{"+LOAN_TYPE_PATH_VARIABLE_TYPE_CODE+"}";
	private static final String LOAN_TYPE_PAGE_ADDR = LOAN_TYPE_ADDR + "/page";



	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_TYPE_ADDR)
	public String getAllLoanType() throws Exception{
		List<LoanTypeDto> listLoanTypeDto = loanTypeDao.getAllLoanType().stream().map(lt -> modelMapper.map(lt, LoanTypeDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanTypeDto>> response = comGen.generateCommonResponse(listLoanTypeDto);
		return JsonUtil.generateJson(response);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(LOAN_TYPE_BY_LOAN_TYPE_CODE_ADDR)
	public String getLoanTypeByLoanTypeCode(@PathVariable(value = "loanTypeCode") String loanTypeCode) throws Exception{
		List<LoanTypeDto> listLoanTypeDto = loanTypeDao.getLoanTypeByLoanTypeCode(loanTypeCode).stream().map(lt -> modelMapper.map(lt, LoanTypeDto.class)).collect(Collectors.toList());
		CommonResponse<List<LoanTypeDto>> response = comGen.generateCommonResponse(listLoanTypeDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(LOAN_TYPE_ADDR)
	public String addLoanType(@RequestBody LoanTypeDto loanType) throws Exception{
		LoanTypeDto loanTypeDto = modelMapper.map(loanTypeDao.addLoanType(loanType), LoanTypeDto.class);
		CommonResponse<LoanTypeDto> response = comGen.generateCommonResponse(loanTypeDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(LOAN_TYPE_BY_LOAN_TYPE_CODE_ADDR)
	public String deleteLoanTypeByLoanTypeCode(@PathVariable(value = "loanTypeCode") String loanTypeCode) throws Exception{
		String res = loanTypeDao.deleteLoanType(loanTypeCode);
		CommonResponse<String> response = comGen.generateCommonResponse(res);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(LOAN_TYPE_BY_LOAN_TYPE_CODE_ADDR)
	public String updateLoanTypeByLoanTypeCode(@PathVariable(value = "loanTypeCode") String loanTypeCode, @RequestBody LoanTypeDto upLoanType) throws Exception{
		LoanTypeDto loanTypeDto = modelMapper.map(loanTypeDao.updateLoanType(loanTypeCode, upLoanType), LoanTypeDto.class);
		CommonResponse<LoanTypeDto> response = comGen.generateCommonResponse(loanTypeDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(LOAN_TYPE_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<LoanType> pageAccount = loanTypeDao.findPaging(page);
		CommonResponse<Page<LoanType>> response = comGen.generateCommonResponse(pageAccount);
		return JsonUtil.generateJson(response);
	}

	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
}
