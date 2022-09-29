package com.flexpag.paymentscheduler.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.controller.form.UpdatePaymentForm;
import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;
import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Page<PaymentDetalhesDTO> findAll(PaymentStatus status, Pageable paginacao){
		if(status == null) {
			return PaymentDetalhesDTO.converter(paymentRepository.findAll(paginacao));
		}
		return PaymentDetalhesDTO.converter(paymentRepository.findByStatus(status, paginacao));
	}
	
	public ResponseEntity<PaymentDetalhesDTO> findPaymentById(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		return ResponseEntity.ok(new PaymentDetalhesDTO(payment.get()));
	}
	
	public ResponseEntity<PaymentDTO> createPayment(PaymentForm form, UriComponentsBuilder uriBuilder) {
		Payment payment = paymentRepository.save(form.converter());
		 URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
		 return ResponseEntity.created(uri).body(new PaymentDTO(payment));
	}
	
	public ResponseEntity<PaymentDetalhesDTO> updatePayment(Long id, UpdatePaymentForm form) {	
		return ResponseEntity.ok(new PaymentDetalhesDTO(form.atualizar(id, paymentRepository)));
	}

	public ResponseEntity<PaymentDTO> pay(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		payment.get().setStatus(PaymentStatus.PAID);
		return ResponseEntity.ok(new PaymentDTO(payment.get()));	
	}
	
	public ResponseEntity<Payment> delete(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		paymentRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public List<Payment> findStatus(PaymentStatus pending) {
		return paymentRepository.findByStatus(pending);
	}

}
