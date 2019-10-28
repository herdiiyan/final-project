package com.sti.enigmabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.LoanType;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String>{

}
