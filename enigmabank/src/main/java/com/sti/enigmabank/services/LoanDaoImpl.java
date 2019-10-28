package com.sti.enigmabank.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.enigmabank.dto.LoanDto;
import com.sti.enigmabank.entity.Loan;
import com.sti.enigmabank.entity.LoanType;
import com.sti.enigmabank.repository.LoanRepository;

public class LoanDaoImpl implements LoanDao {
	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private LoanRepository loanRepo;
	@Autowired LoanTypeDao loanTypeDao;
	

	@Override
	public List<Loan> getAllLoan() throws Exception {
		List<Loan> loanList = new ArrayList<>();
		try {
			loanList = loanRepo.findAll();
			if (loanList.isEmpty()) {
				throw new Exception("Loan List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return loanList;
	}

	@Override
	public List<Loan> getLoanByLoanId(Integer loanId) throws Exception {
		Optional<Loan> loanOpt;
		List<Loan> listLoan = new ArrayList<>();
		try {
			loanOpt = loanRepo.findById(loanId);
			if (!loanOpt.isPresent()) {
				throw new Exception("No Loan Found With " + loanId + " as Loan Id");
			}
			listLoan.add(loanOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public Loan addLoan(LoanDto loanDto) throws Exception {
		List<LoanType> loanType = new ArrayList<>();
		Loan loan = new Loan();
		try {
			loan = modelMapper.map(loanDto, Loan.class);
			loanType = loanTypeDao.getLoanTypeByLoanTypeCode(loan.getLoanTypeCode());
			if (loan.getLoanId() == null) {
				throw new Exception("Loan Id Can't be Empty");
			}
			if(loanType.isEmpty()) {
				throw new Exception("Cant Found Loan Type "+loan.getLoanTypeCode());
			}
			Optional<Loan> exist = loanRepo.findById(loan.getLoanId());
			if (exist.isPresent()) {
				throw new Exception("Loan With Loan Id " + loan.getLoanId() + " Already Exist");
			}
		} catch (Exception e) {
			throw e;
		}
		return loanRepo.save(loan);
	}

	@Override
	public Loan updateLoan(Integer loanId, LoanDto updateLoanDto) throws Exception {
		List<LoanType> loanType = new ArrayList<>();		Optional<Loan> loanOpt;
		Loan loan = new Loan();
		try {
			Loan updateLoan = modelMapper.map(updateLoanDto, Loan.class);
			loanOpt = loanRepo.findById(loanId);
			if (!loanOpt.isPresent()) {
				throw new Exception("No Loan Found With " + loanId + "as Loan Id");			
			}
			loan = loanOpt.get();
			loanType = loanTypeDao.getLoanTypeByLoanTypeCode(updateLoan.getLoanTypeCode());
			if(loanType.isEmpty()) {
				throw new Exception("Cant Found Loan Type "+updateLoan.getLoanTypeCode());
			}
			loan.setLoanAmount(updateLoan.getLoanAmount());
			loan.setLoanBalance(updateLoan.getLoanBalance());
			loan.setLoanStatus(updateLoan.getLoanStatus());
			loan.setDueDate(updateLoan.getDueDate());
			loan.setLoanTenor(updateLoan.getLoanTenor());
			loan.setLoanTypeCode(updateLoan.getLoanTypeCode());
			loan.setAccountNumber(updateLoan.getAccountNumber());
			loan.setCustomerCif(updateLoan.getCustomerCif());
		} catch (Exception e) {
			throw e;
		}
		return loanRepo.save(loan);
	}

	@Override
	public List<Loan> getLoanByAccountNumber(Integer accountNumber) throws Exception {
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByAccountNumber(accountNumber);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found by Account Number : "+accountNumber);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByLoanStatus(int status) throws Exception {
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByLoanStatus(status);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found with Status : "+status);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByOpenDate(String date) throws Exception {	
		DateTime dt = fmt.parseDateTime(date);
		Date currentDate = new DateTime(dt).plusDays(1).toDate();
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByOpenDate(currentDate);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found Opened at : "+date);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByDueDate(String date) throws Exception {
		DateTime dt = fmt.parseDateTime(date);
		Date currentDate = new DateTime(dt).plusDays(1).toDate();
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByDueDate(currentDate);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found Due at : "+date);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByLoanTenor(Integer tenor) throws Exception {
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByLoanTenor(tenor);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found with Tenor : "+tenor);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByLoanTypeCode(String loanTypeCode) throws Exception {
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByLoanTypeCode(loanTypeCode);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found with Type : "+loanTypeCode);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public List<Loan> getLoanByCustomerCif(String cif) throws Exception {
		List<Loan> listLoan;
		try {
			listLoan = loanRepo.findLoanByCustomerCif(cif);
			if(listLoan.isEmpty()) {
				throw new Exception("No Loan Found with Customer Cif : "+cif);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLoan;
	}

	@Override
	public Page<Loan> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<Loan> paging = loanRepo.findAll(pageable);
		return paging;
	}

}
