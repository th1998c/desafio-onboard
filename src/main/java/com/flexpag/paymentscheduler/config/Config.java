package com.flexpag.paymentscheduler.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Payment payment1 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment2 = new Payment(120.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment3 = new Payment(100.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment4 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment5 = new Payment(120.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment6 = new Payment(100.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment7 = new Payment(50.00, LocalDateTime.parse("2022-10-30 11:00", formatter));
		Payment payment8 = new Payment(120.00, LocalDateTime.parse("2022-10-30 11:00", formatter));
		Payment payment9 = new Payment(100.00, LocalDateTime.parse("2022-11-30 11:00", formatter));
		Payment payment10 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment11 = new Payment(120.00, LocalDateTime.parse("2022-12-30 11:00", formatter));
		Payment payment12 = new Payment(100.00, LocalDateTime.parse("2022-10-30 11:00", formatter));
		Payment payment13 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter));
		Payment payment14 = new Payment(120.00, LocalDateTime.parse("2022-11-30 11:00", formatter));
		Payment payment15 = new Payment(100.00, LocalDateTime.parse("2022-12-30 11:00", formatter));
		
		payment2.setStatus(PaymentStatus.PAID);
		payment12.setStatus(PaymentStatus.PAID);
		payment10.setStatus(PaymentStatus.PAID);
		payment13.setStatus(PaymentStatus.PAID);
		payment14.setStatus(PaymentStatus.PAID);

		paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3, payment4, payment5, payment6, payment7, payment8, payment9,
												payment10, payment11, payment12, payment13, payment14, payment15));
	}

}