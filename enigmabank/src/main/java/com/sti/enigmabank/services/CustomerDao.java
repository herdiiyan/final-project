package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.CustomerDto;
import com.sti.enigmabank.entity.Customer;

public interface CustomerDao {

	List<Customer> getAllCustomer() throws Exception;
	Customer addCustomer(CustomerDto customerDto) throws Exception;
	List<Customer> getCustomerByCif(String cif) throws Exception;
	List<Customer> getCustomerByName(String name) throws Exception;
	String deleteCustomerByCif(String cif) throws Exception;
	Customer updateCustomer(String cif, CustomerDto updateCustomer) throws Exception;
	Page<Customer> findPaging(int page) throws Exception;
	
}
