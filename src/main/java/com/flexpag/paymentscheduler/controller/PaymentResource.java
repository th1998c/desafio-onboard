package com.flexpag.paymentscheduler.controller;

import java.time.LocalDateTime;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.controller.form.UpdatePaymentForm;
import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.services.PaymentService;
import com.flexpag.paymentscheduler.services.DTO.PaymentDTO;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;
import com.flexpag.paymentscheduler.services.form.PaymentForm;

@Controller
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;	
	
	@GetMapping
	@Cacheable(value = "listaDePagamentos")
	public String findAll(Model model, @RequestParam(required = false) PaymentStatus status, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<PaymentDetalhesDTO> pagamentos = paymentService.findAll(status, paginacao);
	    model.addAttribute("currentPage", pagamentos.getNumber());
	    model.addAttribute("totalPages", pagamentos.getTotalPages());
	    model.addAttribute("totalItems", pagamentos.getTotalElements());
		model.addAttribute("payments", pagamentos);
		return "payments";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {
		PaymentDetalhesDTO payment = paymentService.findPaymentById(id);
		model.addAttribute("payment", payment);
		return "payment";
	}
	
	@PostMapping
	@CacheEvict(value = "listaDePagamentos", allEntries = true)
	public String create(@Valid PaymentForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
		paymentService.createPayment(form, uriBuilder, request);
		return "redirect:/payments";
	}
	
	@GetMapping("/create")
    public String createPayment(Model model){
	    model.addAttribute("dataAtual", LocalDateTime.now().toString().substring(0, 16));
        return "create";
    }
	
	@PutMapping("/{id}")
	@Transactional
	public String updatePayment(@PathVariable Long id, @Valid  UpdatePaymentForm form) {
		 paymentService.updatePayment(id, form);
		return "redirect:/payments/{id}";
	}

	@PostMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
	    PaymentDetalhesDTO payment = paymentService.findPaymentById(id);
	    model.addAttribute("dataMaxima", payment.getPaymentDate().toString().substring(0, 16));
	    model.addAttribute("dataAtual", LocalDateTime.now().toString().substring(0, 16));
	    model.addAttribute("payment", payment);
		return "editPayment";
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public String pay(@PathVariable Long id) {
		paymentService.pay(id);
		return "redirect:/payments";
	}
	
	@DeleteMapping("/{id}")
	public String deletePayment(@PathVariable Long id) {
		paymentService.delete(id);
		return "redirect:/payments";
	}
	
	
}
