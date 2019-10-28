package com.sti.enigmabank.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.enigmabank.dto.AccountDto;
import com.sti.enigmabank.entity.Account;

public interface AccountDao {

	List<Account> getAllAccount() throws Exception;
	List<Account> getAccountByAccountNumber(Integer accNumber) throws Exception;
	List<Account> getAccountByCustomerCif(String cif) throws Exception;
	Account addAccount(AccountDto accountDto) throws Exception;
	Account updateAccount(Integer accountNumber, AccountDto updateAccount) throws Exception;
	String deleteAccount(Integer accountNumber) throws Exception;
	Page<Account> findPaging(int page) throws Exception;
	Account topUpBalance(Integer accountNumber, AccountDto balance) throws Exception;
}
