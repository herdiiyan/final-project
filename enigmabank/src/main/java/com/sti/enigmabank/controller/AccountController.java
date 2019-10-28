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

import com.sti.enigmabank.dto.AccountDto;
import com.sti.enigmabank.entity.Account;
import com.sti.enigmabank.services.AccountDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="account",produces = "application/json; charset=UTF-8")
public class AccountController {

	@Autowired
	AccountDao accDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	
	private static final String ACCOUNT_PATH_VARIABLE_ACCOUNT_NUMBER ="accountNumber";
	private static final String ACCOUNT_REQ_PARAM_CIF ="cif";
	private static final String ACCOUNT_ADDR = "";
	private static final String ACCOUNT_BY_ACCOUNT_NUMBER_ADDR = ACCOUNT_ADDR + "/{"+ACCOUNT_PATH_VARIABLE_ACCOUNT_NUMBER+"}";
	private static final String ACCOUNT_BY_CIF_ADDR = ACCOUNT_ADDR + ACCOUNT_REQ_PARAM_CIF;
	private static final String ACCOUNT_TOP_UP_ADDR = ACCOUNT_ADDR +"/topup/{"+ACCOUNT_PATH_VARIABLE_ACCOUNT_NUMBER+"}";
	private static final String ACCOUNT_PAGE_ADDR = ACCOUNT_ADDR + "/page";

	
	@CrossOrigin(origins = "*")
	@GetMapping(ACCOUNT_ADDR)
	public String getAllAccount() throws Exception{
		List<AccountDto> listAccountDto = accDao.getAllAccount().stream().map(a -> modelMapper.map(a, AccountDto.class)).collect(Collectors.toList());
		CommonResponse<List<AccountDto>> response = comGen.generateCommonResponse(listAccountDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(ACCOUNT_ADDR)
	public String addAccount(@RequestBody AccountDto account) throws Exception{
		AccountDto accountDto = modelMapper.map(accDao.addAccount(account), AccountDto.class);
		CommonResponse<AccountDto> response = comGen.generateCommonResponse(accountDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(ACCOUNT_BY_ACCOUNT_NUMBER_ADDR)
	public String getAccountByAccountNumber(@PathVariable(value = "accountNumber") Integer accountNumber) throws Exception{
		List<AccountDto> listAccountDto = accDao.getAccountByAccountNumber(accountNumber).stream().map(a -> modelMapper.map(a, AccountDto.class)).collect(Collectors.toList());
		CommonResponse<List<AccountDto>> response = comGen.generateCommonResponse(listAccountDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(ACCOUNT_BY_CIF_ADDR)
	public String getAccountByCif(@RequestParam(value = "cif") String cif) throws Exception{
		List<AccountDto> listAccountDto = accDao.getAccountByCustomerCif(cif).stream().map(a -> modelMapper.map(a, AccountDto.class)).collect(Collectors.toList());
		CommonResponse<List<AccountDto>> response = comGen.generateCommonResponse(listAccountDto);
		return JsonUtil.generateJson(response);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(ACCOUNT_BY_ACCOUNT_NUMBER_ADDR)
	public String deleteAccountByAccountNumber(@PathVariable(value = "accountNumber") Integer accountNumber) throws Exception{
		String res = accDao.deleteAccount(accountNumber);
		CommonResponse<String> response = comGen.generateCommonResponse(res);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(ACCOUNT_BY_ACCOUNT_NUMBER_ADDR)
	public String updateAccount(@PathVariable(value = "accountNumber") Integer accountNumber, @RequestBody AccountDto upAccount) throws Exception{
		AccountDto accountDto = modelMapper.map(accDao.updateAccount(accountNumber, upAccount), AccountDto.class);
		CommonResponse<AccountDto> response = comGen.generateCommonResponse(accountDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(ACCOUNT_TOP_UP_ADDR)
	public String topUpBalance(@PathVariable(value = "accountNumber") Integer accountNumber, @RequestBody AccountDto balance) throws Exception{
		AccountDto accountDto = modelMapper.map(accDao.topUpBalance(accountNumber, balance), AccountDto.class);
		CommonResponse<AccountDto> response = comGen.generateCommonResponse(accountDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(ACCOUNT_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Account> pageAccount = accDao.findPaging(page);
		CommonResponse<Page<Account>> response = comGen.generateCommonResponse(pageAccount);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
}
