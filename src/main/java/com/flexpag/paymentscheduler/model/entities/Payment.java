package com.flexpag.paymentscheduler.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.flexpag.paymentscheduler.model.enums.PaymentStatus;

import lombok.Data;

@Data
@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valueOfPayment;
	private LocalDate dataPagamento;
	private LocalTime horaPagamento;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status = PaymentStatus.PENDING;

	public Payment() {

	}

	public Payment(Double valueOfPayment, LocalDate dataPagamento, LocalTime horaPagamento) {
		this.valueOfPayment = valueOfPayment;
		this.dataPagamento = dataPagamento;
		this.horaPagamento = horaPagamento;
		this.status = PaymentStatus.PENDING;
	}

}