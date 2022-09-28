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
	@Scheduled(fixedDelay = 1800000, initialDelay = 1000)
	@Transactional
	public void Imprime() {
		
		 List<Payment> pagamentos = service.findPending(PaymentStatus.PENDING);	
		 pagamentos.forEach((x) -> {
			 if(x.getPaymentDate().isBefore(LocalDateTime.now())) { service.Pay(x.getId()); }}
		 );
		 
	}
	
}
