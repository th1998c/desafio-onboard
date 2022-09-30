package com.flexpag.paymentscheduler.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;


@Component
public class ScheduledPay {
	
	@Autowired
	PaymentService service;
	
	@Scheduled(fixedDelay = 60000, initialDelay = 1000)
	@Transactional
	public void RealizaPagamentosAgendados() {
		
		 List<Payment> pagamentos = service.findStatus(PaymentStatus.PENDING);			 
		 pagamentos.stream()
		 .filter(x -> x.getPaymentDate().isBefore(LocalDateTime.now()))
		 .forEach(x -> service.pay(x.getId()));;
	}
	
}
