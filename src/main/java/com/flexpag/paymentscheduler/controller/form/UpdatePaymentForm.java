package com.flexpag.paymentscheduler.controller.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.services.exceptions.InvalidDateException;
import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;

import lombok.Setter;

@Setter
public class UpdatePaymentForm {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone="GMT-3")
	@NotNull @Future
	private LocalDateTime paymentDate;
	
	public Payment atualizar(Long id, PaymentRepository repository) {
		Optional<Payment> payment = repository.findById(id);
		if (payment.get().getStatus().equals(PaymentStatus.PAID)) {
			throw new ResourceAccessDenied(id);
		}
		
		if (payment.get().getPaymentDate().isBefore(this.paymentDate)){
			throw new InvalidDateException(id);
		}
		
		payment.get().setPaymentDate(this.paymentDate);
		return payment.get();
	}
	
}
