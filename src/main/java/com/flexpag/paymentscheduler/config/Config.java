package com.flexpag.paymentscheduler.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void run(String... args) throws Exception {
		Payment payment1 = new Payment(50.00, LocalDate.of(2022, 05, 05));
		Payment payment2 = new Payment(120.00, LocalDate.of(2023, 05, 12));
		Payment payment3 = new Payment(100.00, LocalDate.of(2022, 11, 15));

		paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3));
	}

}