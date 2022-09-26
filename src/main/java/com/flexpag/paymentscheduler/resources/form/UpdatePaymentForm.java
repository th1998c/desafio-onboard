package com.flexpag.paymentscheduler.resources.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

import lombok.Data;

@Data
public class UpdatePaymentForm {

	@NotNull @Future
	private LocalDate dataPagamento;
	
	public Payment atualizar(Long id, PaymentRepository repository) {
		Optional<Payment> payment = repository.findById(id);
		payment.get().setDataPagamento(this.dataPagamento);
		return payment.get();
	}
	
}
