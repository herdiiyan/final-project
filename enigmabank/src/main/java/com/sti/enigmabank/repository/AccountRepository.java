package com.sti.enigmabank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByCustomerCif(String cif);
}
