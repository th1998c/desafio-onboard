package com.flexpag.paymentscheduler.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Page<Payment> findByStatus(PaymentStatus status, Pageable paginacao);

}
