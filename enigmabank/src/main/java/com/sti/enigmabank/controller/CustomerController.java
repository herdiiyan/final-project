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

import com.sti.enigmabank.dto.CustomerDto;
import com.sti.enigmabank.entity.Customer;
import com.sti.enigmabank.services.CustomerDao;
import com.sti.enigmabank.util.CommonResponse;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.JsonUtil;

@RestController
@RequestMapping(path="customer",produces = "application/json; charset=UTF-8")
public class CustomerController {

	@Autowired
	CustomerDao cusDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	
	private static final String CUSTOMER_PATH_VARIABLE_CIF ="cif";
	private static final String CUSTOMER_REQ_PARAM_NAME ="name";
	private static final String CUSTOMER_ADDR = "";
	private static final String CUSTOMER_BY_CIF_ADDR = CUSTOMER_ADDR + "/{"+CUSTOMER_PATH_VARIABLE_CIF+"}";
	private static final String CUSTOMER_BY_NAME_ADDR = CUSTOMER_ADDR + CUSTOMER_REQ_PARAM_NAME;
	private static final String CUSTOMER_PAGE_ADDR = CUSTOMER_ADDR + "/page";
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(CUSTOMER_ADDR)
	public String getAllCustomer() throws Exception{
		List<CustomerDto> listCustomerDto = cusDao.getAllCustomer().stream().map(c -> modelMapper.map(c, CustomerDto.class)).collect(Collectors.toList());
		CommonResponse<List<CustomerDto>> response = comGen.generateCommonResponse(listCustomerDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(CUSTOMER_ADDR)
	public String addCustomer(@RequestBody CustomerDto customer) throws Exception{
		CustomerDto customerDto = modelMapper.map(cusDao.addCustomer(customer), CustomerDto.class);
		CommonResponse<CustomerDto> response = comGen.generateCommonResponse(customerDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(CUSTOMER_BY_CIF_ADDR)
	public String getCustomerByCif(@PathVariable(value = "cif") String cif) throws Exception{
		List<CustomerDto> listCustomerDto = cusDao.getCustomerByCif(cif).stream().map(c -> modelMapper.map(c, CustomerDto.class)).collect(Collectors.toList());
		CommonResponse<List<CustomerDto>> response = comGen.generateCommonResponse(listCustomerDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(CUSTOMER_BY_NAME_ADDR)
	public String getCustomerByName(@RequestParam(value = "name") String name) throws Exception{
		List<CustomerDto> listCustomerDto = cusDao.getCustomerByName(name).stream().map(c -> modelMapper.map(c, CustomerDto.class)).collect(Collectors.toList());
		CommonResponse<List<CustomerDto>> response = comGen.generateCommonResponse(listCustomerDto);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(CUSTOMER_BY_CIF_ADDR)
	public String deleteCustomerByCif(@PathVariable(value = "cif") String cif) throws Exception{
		String res = cusDao.deleteCustomerByCif(cif);
		CommonResponse<String> response = comGen.generateCommonResponse(res);
		return JsonUtil.generateJson(response);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(CUSTOMER_BY_CIF_ADDR)
	public String updateCustomer(@PathVariable(value = "cif") String cif, @RequestBody CustomerDto upCustomer) throws Exception{
		CustomerDto customerDto = modelMapper.map(cusDao.updateCustomer(cif, upCustomer), CustomerDto.class);
		CommonResponse<CustomerDto> response = comGen.generateCommonResponse(customerDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(CUSTOMER_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Customer> pageCustomer = cusDao.findPaging(page);
		CommonResponse<Page<Customer>> response = comGen.generateCommonResponse(pageCustomer);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
	
}
