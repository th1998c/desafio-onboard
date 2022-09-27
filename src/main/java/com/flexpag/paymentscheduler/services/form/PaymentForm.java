package com.flexpag.paymentscheduler.services.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.flexpag.paymentscheduler.entities.Payment;

import lombok.Data;

@Data
public class PaymentForm {
	@NotNull
	private Double valueOfPayment;
	@NotNull @Future
	private LocalDate dataPagamento;
	@NotNull
	private LocalTime horaPagamento;
	
	public Payment converter() {
		Payment payment = new Payment(this.valueOfPayment, this.dataPagamento, this.horaPagamento);
		return payment;
	}
	
}
