package com.flexpag.paymentscheduler.controller.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

import lombok.Data;

@Data
public class UpdatePaymentForm {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	@NotNull @Future
	private LocalDateTime paymentDate;
	
	public Payment atualizar(Long id, PaymentRepository repository) {
		Optional<Payment> payment = repository.findById(id);
		payment.get().setPaymentDate(this.paymentDate);
		return payment.get();
	}
	
}
