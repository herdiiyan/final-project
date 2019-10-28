package com.sti.enigmabank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.enigmabank.dto.AccountDto;
import com.sti.enigmabank.entity.Account;
import com.sti.enigmabank.repository.AccountRepository;

public class AccountDaoImpl implements AccountDao {

	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private AccountRepository accRepo;
	
	@Override
	public List<Account> getAllAccount() throws Exception {
		List<Account> accountList = new ArrayList<>();			
		try {
			accountList = accRepo.findAll();
			if (accountList.isEmpty()) {
				throw new Exception("Account List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return accountList;
	}

	@Override
	public List<Account> getAccountByAccountNumber(Integer accNumber) throws Exception {
		Optional<Account> accountOpt;
		List<Account> listAccount = new ArrayList<>();
		try {
			accountOpt = accRepo.findById(accNumber);
			if(!accountOpt.isPresent()) {
				throw new Exception("No Account Found With "+accNumber+" as Account Number");
			}
			listAccount.add(accountOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listAccount;
	}

	@Override
	public List<Account> getAccountByCustomerCif(String cif) throws Exception {
		List<Account> listAccount;
		try {
			listAccount = accRepo.findByCustomerCif(cif);
			if(listAccount.isEmpty()) {
				throw new Exception("No Account Found by Cif : "+cif);
			}
		} catch (Exception e) {
			throw e;
		}
		return listAccount;
	}

	@Override
	public Account addAccount(AccountDto accountDto) throws Exception {
		Account account = new Account();
		try {
			account = modelMapper.map(accountDto, Account.class);
			if(account.getAccountNumber()==null) {
				throw new Exception("Account Number Can't be Empty");
			}
			Optional<Account> exist = accRepo.findById(account.getAccountNumber());
			if(exist.isPresent()) {
				throw new Exception("Account With Account Number "+account.getAccountNumber()+" Already Exist");
			}			
		} catch (Exception e) {
			throw e;
		}
		return accRepo.save(account);
	}

	@Override
	public Account updateAccount(Integer accountNumber, AccountDto updateAccountDto) throws Exception {
		Optional<Account> accountOpt;
		Account account = new Account();
		try {
			Account updateAccount = modelMapper.map(updateAccountDto, Account.class);
			accountOpt = accRepo.findById(accountNumber);
			if (!accountOpt.isPresent()) {
				throw new Exception("No Account Found With " + accountNumber + "as Account Number");			
			}
			account = accountOpt.get();
			account.setAccountBalance(updateAccount.getAccountBalance());
			account.setCustomerCif(updateAccount.getCustomerCif());
		} catch (Exception e) {
			throw e;
		}
		return accRepo.save(account);
	}

	@Override
	public String deleteAccount(Integer accountNumber) throws Exception {
		Optional<Account> accountOpt;
		String response = "Account Deleted";
		try {
			accountOpt = accRepo.findById(accountNumber);
			if (accountOpt.isPresent()) {
				Account account = accountOpt.get();
				accRepo.delete(account);
			} else {
				throw new Exception("No Account Found With Account Number" + accountNumber);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Page<Account> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<Account> paging = accRepo.findAll(pageable);
		return paging;
	}

	@Override
	public Account topUpBalance(Integer accountNumber, AccountDto balance) throws Exception {
		Optional<Account> accountOpt;
		Account account = new Account();
		try {
			Account updateBalance = modelMapper.map(balance, Account.class);
			accountOpt = accRepo.findById(accountNumber);
			if (!accountOpt.isPresent()) {
				throw new Exception("No Account Found With " + accountNumber + "as Account Number");			
			}
			account = accountOpt.get();
			account.setAccountBalance(updateBalance.getAccountBalance()+accountOpt.get().getAccountBalance());
			account.setCreatedAt(accountOpt.get().getCreatedAt());
			account.setCustomerCif(accountOpt.get().getCustomerCif());
		} catch (Exception e) {
			throw e;
		}
		return accRepo.save(account);
	}

}
