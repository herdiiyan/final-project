package com.sti.enigmabank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.enigmabank.dto.LoanTypeDto;
import com.sti.enigmabank.entity.LoanType;
import com.sti.enigmabank.repository.LoanTypeRepository;

public class LoanTypeDaoImpl implements LoanTypeDao {

	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private LoanTypeRepository loanTypeRepo;
	
	@Override
	public List<LoanType> getAllLoanType() throws Exception {
		List<LoanType> loanTypeList = new ArrayList<>();
		try {
			loanTypeList = loanTypeRepo.findAll();
			if (loanTypeList.isEmpty()) {
				throw new Exception("Loan Type List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return loanTypeList;
	}

	@Override
	public List<LoanType> getLoanTypeByLoanTypeCode(String loanTypeCode) throws Exception {
		Optional<LoanType> loanTypeOpt;
		List<LoanType> listLoanType = new ArrayList<>();
		try {
			loanTypeOpt = loanTypeRepo.findById(loanTypeCode);
			if (!loanTypeOpt.isPresent()) {
				throw new Exception("No Loan Type Found With " + loanTypeCode + " Code");
			}
			listLoanType.add(loanTypeOpt.get());
		} catch (Exception e) {
			throw e;
		}
		return listLoanType;
	}

	@Override
	public LoanType addLoanType(LoanTypeDto loanTypeDto) throws Exception {
		LoanType loanType = new LoanType();
		try {
			loanType = modelMapper.map(loanTypeDto, LoanType.class);
			if (loanType.getLoanTypeCode() == null) {
				throw new Exception("Loan Id Can't be Empty");
			}
			Optional<LoanType> exist = loanTypeRepo.findById(loanType.getLoanTypeCode());
			if (exist.isPresent()) {
				throw new Exception("Loan Type " + loanType.getLoanTypeCode() + " Already Exist");
			}
		} catch (Exception e) {
			throw e;
		}
		return loanTypeRepo.save(loanType);
	}

	@Override
	public String deleteLoanType(String loanTypeCode) throws Exception {
		Optional<LoanType> loanTypeOpt;
		String response = "Loan Type Deleted";
		try {
			loanTypeOpt = loanTypeRepo.findById(loanTypeCode);
			if (loanTypeOpt.isPresent()) {
				LoanType loanType = loanTypeOpt.get();
				loanTypeRepo.delete(loanType);
			} else {
				throw new Exception("No Loan Type Found With " + loanTypeCode + " Code");
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public LoanType updateLoanType(String loanTypeCode, LoanTypeDto updateLoanTypeDto) throws Exception {
		Optional<LoanType> loanTypeOpt;
		LoanType loanType = new LoanType();
		try {
			LoanType updateLoanType = modelMapper.map(updateLoanTypeDto, LoanType.class);
			loanTypeOpt = loanTypeRepo.findById(loanTypeCode);
			if (!loanTypeOpt.isPresent()) {
				throw new Exception("No Loan Type Found With " + loanTypeCode + " Code");			
			}
			loanType = loanTypeOpt.get();
			loanType.setLoanTypeDescription(updateLoanType.getLoanTypeDescription());
		} catch (Exception e) {
			throw e;
		}
		return loanTypeRepo.save(loanType);
	}

	@Override
	public Page<LoanType> findPaging(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, 10);
		Page<LoanType> paging = loanTypeRepo.findAll(pageable);
		return paging;
	}

}
