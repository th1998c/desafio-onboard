package com.flexpag.paymentscheduler.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;

import lombok.Data;

@Data
@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Double paymentValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
    private LocalDateTime paymentDate;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status = PaymentStatus.PENDING;
	@ManyToOne
	private Usuario usuario;

	public Payment() {

	}
	
	public Payment(Double paymentValue, LocalDateTime paymentDate) {
		this.paymentValue = paymentValue;
		this.paymentDate = paymentDate;
		this.status = PaymentStatus.PENDING;
	}

	public Payment(Double paymentValue, LocalDateTime paymentDate , Usuario usuario) {
		this.paymentValue = paymentValue;
		this.paymentDate = paymentDate;
		this.status = PaymentStatus.PENDING;
		this.usuario = usuario;
	}

}