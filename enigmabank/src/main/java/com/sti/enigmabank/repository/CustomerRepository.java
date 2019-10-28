package com.sti.enigmabank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	List<Customer> findByFirstNameLike(String fname);
	List<Customer> findByLastNameLike(String lname);
//	Page<Customer> findPage(Pageable pageable);
//	List<Customer> findByName(String name);
}
