package com.flexpag.paymentscheduler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.controller.form.UpdatePaymentForm;
import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
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
	public Page<PaymentDetalhesDTO> findAll(@RequestParam(required = false) PaymentStatus status, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return paymentService.findAll(status, paginacao);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDetalhesDTO> findById(@PathVariable Long id) {
		return paymentService.findPaymentById(id);
	}
	
	@PostMapping
	@CacheEvict(value = "listaDePagamentos", allEntries = true)
	public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
		return paymentService.createPayment(form, uriBuilder, request);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDetalhesDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody UpdatePaymentForm form) {
		return paymentService.updatePayment(id, form);	
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDTO> pay(@PathVariable Long id) {
		return paymentService.pay(id);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payment> deletePayment(@PathVariable Long id) {
		return paymentService.delete(id);
	}
	
}
