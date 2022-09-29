package com.flexpag.paymentscheduler.services.DTO;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;

import lombok.Getter;

@Getter
public class PaymentDetalhesDTO {
	
	private Long id;
    private Double paymentValue;
    private LocalDateTime paymentDate;
	private PaymentStatus status;
	
	public PaymentDetalhesDTO(Payment payment) {
		this.id = payment.getId();
		this.paymentValue = payment.getPaymentValue();
		this.paymentDate = payment.getPaymentDate();
		this.status = payment.getStatus();
	}

	public static Page<PaymentDetalhesDTO> converter(Page<Payment> payment) {
		return payment.map(PaymentDetalhesDTO::new);
	}
	
}
