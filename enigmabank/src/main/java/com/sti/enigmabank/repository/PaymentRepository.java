package com.sti.enigmabank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.enigmabank.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findPaymentByPaymentDate(Date paymentDate);
}
