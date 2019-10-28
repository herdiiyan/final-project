package com.sti.enigmabank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sti.enigmabank.services.AccountDao;
import com.sti.enigmabank.services.AccountDaoImpl;
import com.sti.enigmabank.services.BillingDao;
import com.sti.enigmabank.services.BillingDaoImpl;
import com.sti.enigmabank.services.CustomerDao;
import com.sti.enigmabank.services.CustomerDaoImpl;
import com.sti.enigmabank.services.LoanDao;
import com.sti.enigmabank.services.LoanDaoImpl;
import com.sti.enigmabank.services.LoanTypeDao;
import com.sti.enigmabank.services.LoanTypeDaoImpl;
import com.sti.enigmabank.services.PaymentDao;
import com.sti.enigmabank.services.PaymentDaoImpl;
import com.sti.enigmabank.util.CommonResponseGenerator;
import com.sti.enigmabank.util.CommonStatus;

@Configuration
public class SpringConfig {

	@Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}
	
	@Bean
	public LoanDao loanDao() {
		return new LoanDaoImpl();
	}
	
	@Bean
	public LoanTypeDao loanTypeDao() {
		return new LoanTypeDaoImpl();
	}
	
	@Bean
	public BillingDao billingDao() {
		return new BillingDaoImpl();
	}
	
	@Bean
	public PaymentDao paymentDao() {
		return new PaymentDaoImpl();
	}
	
	@Bean
	public CommonResponseGenerator commonResponseGenerator() {
		return new CommonResponseGenerator();
	}
	
	@Bean
	public CommonStatus commonStatus() {
		return new CommonStatus();
	}
}
