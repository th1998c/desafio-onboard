package com.flexpag.paymentscheduler.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.resources.form.UpdatePaymentForm;
import com.flexpag.paymentscheduler.services.PaymentService;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;	
	
	@GetMapping
	@Cacheable(value = "listaDePagamentos")
	public List<PaymentDetalhesDTO> findAll() {
		return paymentService.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDetalhesDTO> findById(@PathVariable Long id) {
		return paymentService.findPaymentById(id);
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePagamentos", allEntries = true)
	public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder) {
		return paymentService.createPayment(form, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDetalhesDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody UpdatePaymentForm form) {
		return paymentService.updatePayment(id, form);	
		
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDTO> Pay(@PathVariable Long id) {
		return paymentService.Pay(id);		
	}
	

	//delete
	
	
}
