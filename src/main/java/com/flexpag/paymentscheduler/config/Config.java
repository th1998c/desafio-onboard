package com.flexpag.paymentscheduler.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void run(String... args) throws Exception {
		Payment payment1 = new Payment(50.00, LocalDate.of(2022, 05, 05), LocalTime.of(04, 30));
		Payment payment2 = new Payment(120.00, LocalDate.of(2023, 05, 12), LocalTime.of(04, 30));
		Payment payment3 = new Payment(100.00, LocalDate.of(2022, 04, 15), LocalTime.of(04, 30));
		Payment payment4 = new Payment(50.00, LocalDate.of(2022, 05, 05), LocalTime.of(04, 30));
		Payment payment5 = new Payment(120.00, LocalDate.of(2023, 03, 12), LocalTime.of(04, 30));
		Payment payment6 = new Payment(100.00, LocalDate.of(2022, 11, 15), LocalTime.of(04, 30));
		Payment payment7 = new Payment(50.00, LocalDate.of(2022, 05, 05), LocalTime.of(04, 30));
		Payment payment8 = new Payment(120.00, LocalDate.of(2023, 9, 12), LocalTime.of(04, 30));
		Payment payment9 = new Payment(100.00, LocalDate.of(2024, 11, 15), LocalTime.of(04, 30));
		Payment payment10 = new Payment(50.00, LocalDate.of(2022, 05, 03), LocalTime.of(04, 30));
		Payment payment11 = new Payment(120.00, LocalDate.of(2022, 05, 12), LocalTime.of(04, 30));
		Payment payment12 = new Payment(100.00, LocalDate.of(2022, 07, 15), LocalTime.of(04, 30));
		Payment payment13 = new Payment(50.00, LocalDate.of(2022, 12, 05), LocalTime.of(04, 30));
		Payment payment14 = new Payment(120.00, LocalDate.of(2022, 03, 12), LocalTime.of(04, 30));
		Payment payment15 = new Payment(100.00, LocalDate.of(2022, 11, 15), LocalTime.of(04, 30));
		
		payment2.setStatus(PaymentStatus.PAID);
		payment12.setStatus(PaymentStatus.PAID);
		payment10.setStatus(PaymentStatus.PAID);
		payment13.setStatus(PaymentStatus.PAID);
		payment14.setStatus(PaymentStatus.PAID);

		paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3, payment4, payment5, payment6, payment7, payment8, payment9,
												payment10, payment11, payment12, payment13, payment14, payment15));
	}

}