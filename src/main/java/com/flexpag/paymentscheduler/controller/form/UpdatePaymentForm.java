package com.flexpag.paymentscheduler.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

import lombok.Data;

@Data
public class UpdatePaymentForm {

	@NotNull @Future
	private LocalDate dataPagamento;
	@NotNull
	private LocalTime horaPagamento;
	
	public Payment atualizar(Long id, PaymentRepository repository) {
		Optional<Payment> payment = repository.findById(id);
		payment.get().setDataPagamento(this.dataPagamento);
		payment.get().setHoraPagamento(this.horaPagamento);
		return payment.get();
	}
	
}
