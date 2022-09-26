package com.flexpag.paymentscheduler.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.services.PaymentService;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;	
	
	@GetMapping
	@Cacheable(value = "listaDePagamentos")
	public List<PaymentDTO> findAll() {
		return paymentService.findAll();		
	}
	
	@GetMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePagamentos", allEntries = true)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Payment> payment = paymentService.findById(id);
		if (payment.isPresent()) {
			return ResponseEntity.ok(new PaymentDTO(payment.get()));
		}
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder) {
		 Payment payment = paymentService.createPayment(form);
		 URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
		 return ResponseEntity.created(uri).body(new PaymentDTO(payment));
	}
	
	//create
	//update
	//delete
	
	
}
