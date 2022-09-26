package com.flexpag.paymentscheduler.services.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Getter;

@Getter
public class PaymentDTO {
	
	private Long id;
	private Double valueOfPayment;
	private LocalDate dataPagamento;
	private PaymentStatus status;
	
	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
		this.valueOfPayment = payment.getValueOfPayment();
		this.dataPagamento = payment.getDataPagamento();
		this.status = payment.getStatus();
	}

	
	public static List<PaymentDTO> converter(List<Payment> payment) {
		return payment.stream().map(PaymentDTO::new).collect(Collectors.toList());
	}
	
}
