package com.flexpag.paymentscheduler.services.form;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.model.entities.Payment;

import lombok.Setter;

@Setter
public class PaymentForm {
	@NotNull @DecimalMin("0.01")
	private Double paymentValue;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	@NotNull @Future
	private LocalDateTime paymentDate;

	
	public Payment converter() {
		return new Payment(this.paymentValue, this.paymentDate);
	}
	
}
