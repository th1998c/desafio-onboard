package com.flexpag.paymentscheduler.services.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.flexpag.paymentscheduler.model.entities.Payment;

import lombok.Getter;

@Getter
public class PaymentDTO {
	
	private Long id;

	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
	}

	public static List<PaymentDTO> converter(List<Payment> payment) {
		return payment.stream().map(PaymentDTO::new).collect(Collectors.toList());
	}
	
}
