package com.flexpag.paymentscheduler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<PaymentDTO> findAll(){
		return PaymentDTO.converter(paymentRepository.findAll());
	}
	
	public Optional<Payment> findById(Long id) {
		return paymentRepository.findById(id);
	}
	
	public Payment createPayment(PaymentForm form) {
		Payment payment = form.converter();
		paymentRepository.save(payment);
		return payment;
	}

}
