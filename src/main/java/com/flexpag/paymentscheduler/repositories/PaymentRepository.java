package com.flexpag.paymentscheduler.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Page<Payment> findByStatus(PaymentStatus status, Pageable paginacao);

	List<Payment> findByStatus(PaymentStatus pending);


}
