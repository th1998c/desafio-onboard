package com.flexpag.paymentscheduler.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.resources.form.UpdatePaymentForm;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<PaymentDetalhesDTO> findAll(){
		return PaymentDetalhesDTO.converter(paymentRepository.findAll());
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
		
		if (payment.get().getStatus().equals(PaymentStatus.PENDING)) {
			Payment paymentOld = form.atualizar(id, paymentRepository);
			return ResponseEntity.ok(new PaymentDetalhesDTO(paymentOld));
		}
		return ResponseEntity.status(403).build();
	}

	public ResponseEntity<PaymentDTO> Pay(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if(payment.isPresent() && payment.get().getStatus().equals(PaymentStatus.PENDING)) {
			payment.get().setStatus(PaymentStatus.PAID);
			return ResponseEntity.ok(new PaymentDTO(payment.get()));
		}
		return ResponseEntity.notFound().build();	
	}

}
