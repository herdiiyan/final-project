package com.sti.enigmabank.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.enigmabank.dto.CustomerDto;
import com.sti.enigmabank.entity.Customer;
import com.sti.enigmabank.repository.CustomerRepository;


public class CustomerDaoImpl implements CustomerDao {

	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CustomerRepository cusRepo;

	@Override
	public List<Customer> getAllCustomer() throws Exception {
		List<Customer> customerlist = new ArrayList<>();			
		try {
			customerlist = cusRepo.findAll();
			if (customerlist.isEmpty()) {
				throw new Exception("Customer List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return customerlist;
	}

	@Override
	public Customer addCustomer(CustomerDto customerDto) throws Exception {
		Customer customer = new Customer();
		try {
			customer = modelMapper.map(customerDto, Customer.class);
			if(customer.getCif()==null) {
				throw new Exception("CIF Can't be Empty");
			}
			Optional<Customer> exist = cusRepo.findById(customer.getCif());
			if(exist.isPresent()) {
				throw new Exception("Customer With CIF "+customer.getCif()+" Already Exist");
			}			
		} catch (Exception e) {
			throw e;
		}
		return cusRepo.save(customer);
	}

	@Override
	public List<Customer> getCustomerByName(String name) throws Exception {
		List<Customer> customerlist = new ArrayList<>();
		List<Customer> newlist = new ArrayList<>();
		String[] fullname;
		try {
			boolean isWhitespace = name.contains(" ");
			if(isWhitespace) {
				fullname = name.split(" ",2);
				customerlist = cusRepo.findByFirstNameLike("%" + fullname[0] + "%");
				customerlist.addAll(cusRepo.findByLastNameLike("%" + fullname[1] + "%"));
			}else {
				customerlist = cusRepo.findByFirstNameLike("%" + name + "%");
				customerlist.addAll(cusRepo.findByLastNameLike("%" + name + "%"));
			}
		
			if (customerlist.isEmpty()) {
				throw new Exception("No Customer With Name " + name + " Found");
			}
			 LinkedHashSet<Customer> hashSet = new LinkedHashSet<>(customerlist);
			 newlist = new ArrayList<>(hashSet);
		} catch (Exception e) {
			throw e;
		}
		return newlist;
	}

	@Override
	public List<Customer> getCustomerByCif(String cif) throws Exception {
		Optional<Customer> customerOpt;
		List<Customer> listCustomer = new ArrayList<>();
		try {
			customerOpt = cusRepo.findById(cif);
			if(!customerOpt.isPresent()) {
				throw new Exception("No Customer Found by CIF : "+cif);
			}
			listCustomer.add(customerOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listCustomer;
	}
	

	@Override
	public String deleteCustomerByCif(String cif) throws Exception {
		Optional<Customer> customerOpt;
		String response = "Customer Deleted";
		try {
			customerOpt = cusRepo.findById(cif);
			if (customerOpt.isPresent()) {
				Customer customer = customerOpt.get();
				cusRepo.delete(customer);
			} else {
				throw new Exception("No Customer Found With " + cif + "as CIF");
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Customer updateCustomer(String cif, CustomerDto updateCustomerDto) throws Exception {
		Optional<Customer> customerOpt;
		Customer customer = new Customer();
		try {
			Customer updateCustomer = modelMapper.map(updateCustomerDto, Customer.class);
			customerOpt = cusRepo.findById(cif);
			if (!customerOpt.isPresent()) {
				throw new Exception("No Customer Found With " + cif + "as CIF");			
			}
			customer = customerOpt.get();
			customer.setFirstName(updateCustomer.getFirstName());
			customer.setLastName(updateCustomer.getLastName());
			customer.setAddress(updateCustomer.getAddress());
			customer.setBirthPlace(updateCustomer.getBirthPlace());
			customer.setBirthDate(updateCustomer.getBirthDate());
		} catch (Exception e) {
			throw e;
		}
		return cusRepo.save(customer);
	}

	@Override
	public Page<Customer> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<Customer> paging = cusRepo.findAll(pageable);
		return paging;
	}
}
