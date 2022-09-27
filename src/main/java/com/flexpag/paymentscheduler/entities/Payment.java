package com.flexpag.paymentscheduler.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
	@NonNull
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