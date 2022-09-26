package com.flexpag.paymentscheduler.services.form;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.flexpag.paymentscheduler.entities.Payment;

import lombok.Data;

@Data
public class PaymentForm {
	@NotNull
	private Double valueOfPayment;
	@Future
	private LocalDate dataPagamento;
	
	public Payment converter() {
		Payment payment = new Payment(this.valueOfPayment, this.dataPagamento);
		return payment;
	}
	
}
