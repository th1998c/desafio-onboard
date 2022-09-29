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
import com.flexpag.paymentscheduler.services.exceptions.InvalidDateException;
import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Page<PaymentDetalhesDTO> findAll(PaymentStatus status, Pageable paginacao){
		if(status == null) {
			System.out.println("dentro do if");
			return PaymentDetalhesDTO.converter(paymentRepository.findAll(paginacao));
		}
		System.out.println("fora do if");
		return PaymentDetalhesDTO.converter(paymentRepository.findByStatus(status, paginacao));
	}
	
	public ResponseEntity<PaymentDetalhesDTO> findPaymentById(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			return ResponseEntity.ok(new PaymentDetalhesDTO(payment.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<PaymentDTO> createPayment(PaymentForm form, UriComponentsBuilder uriBuilder) {
		Payment payment = form.converter();
		paymentRepository.save(payment);
		 URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
		 return ResponseEntity.created(uri).body(new PaymentDTO(payment));
	}
	
	
	public ResponseEntity<PaymentDetalhesDTO> updatePayment(Long id, UpdatePaymentForm form) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		
		if (payment.get().getPaymentDate().isBefore(form.getPaymentDate())){
			throw new InvalidDateException(id);
		}
		
		Payment paymentOld = form.atualizar(id, paymentRepository);
		return ResponseEntity.ok(new PaymentDetalhesDTO(paymentOld));
	}

	public ResponseEntity<PaymentDTO> Pay(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if(payment.isPresent() && payment.get().getStatus().equals(PaymentStatus.PENDING)) {
			payment.get().setStatus(PaymentStatus.PAID);
			return ResponseEntity.ok(new PaymentDTO(payment.get()));
		}
		
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		
		
		return ResponseEntity.notFound().build();	
	}
	
	public ResponseEntity<PaymentDetalhesDTO> delete(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		System.out.println(payment.get().getId());
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		paymentRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public List<Payment> findPending(PaymentStatus pending) {
		return paymentRepository.findByStatus(pending);
	}

}
