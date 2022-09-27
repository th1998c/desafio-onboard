package com.flexpag.paymentscheduler.services.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Getter;

@Getter
public class PaymentDetalhesDTO {
	
	private Long id;
	private Double valueOfPayment;
	private LocalDate dataPagamento;
	private LocalTime horaPagamento;
	private PaymentStatus status;
	
	public PaymentDetalhesDTO(Payment payment) {
		this.id = payment.getId();
		this.valueOfPayment = payment.getValueOfPayment();
		this.dataPagamento = payment.getDataPagamento();
		this.status = payment.getStatus();
		this.horaPagamento = payment.getHoraPagamento();
	}

	
	public static Page<PaymentDetalhesDTO> converter(Page<Payment> payment) {
		return payment.map(PaymentDetalhesDTO::new);
	}
	
}
