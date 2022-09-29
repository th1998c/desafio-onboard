package com.flexpag.paymentscheduler.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.flexpag.paymentscheduler.model.entities.Payment;
import com.flexpag.paymentscheduler.model.entities.Usuario;
import com.flexpag.paymentscheduler.model.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.repositories.UsuarioRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		Usuario usuario1 = new Usuario("user@email.com", "$2a$10$O3Rkxe5DVBXFteoDUgWN7.Isw8hI5wzyoxnMTuaab40M8Ex34q9gW");
		
		Payment payment1 = new Payment(50.00, LocalDateTime.parse("2022-09-27 11:00", formatter), usuario1);
		Payment payment2 = new Payment(120.00, LocalDateTime.parse("2022-09-27 11:00", formatter), usuario1);
		Payment payment3 = new Payment(100.00, LocalDateTime.parse("2022-09-28 11:00", formatter), usuario1);
		Payment payment4 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter), usuario1);
		Payment payment5 = new Payment(120.00, LocalDateTime.parse("2022-09-30 11:00", formatter), usuario1);
		Payment payment6 = new Payment(100.00, LocalDateTime.parse("2022-09-30 11:00", formatter), usuario1);
		Payment payment7 = new Payment(50.00, LocalDateTime.parse("2022-10-30 11:00", formatter), usuario1);
		Payment payment8 = new Payment(120.00, LocalDateTime.parse("2022-10-30 11:00", formatter), usuario1);
		Payment payment9 = new Payment(100.00, LocalDateTime.parse("2022-11-30 11:00", formatter), usuario1);
		Payment payment10 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter), usuario1);
		Payment payment11 = new Payment(120.00, LocalDateTime.parse("2022-12-30 11:00", formatter), usuario1);
		Payment payment12 = new Payment(100.00, LocalDateTime.parse("2022-10-30 11:00", formatter), usuario1);
		Payment payment13 = new Payment(50.00, LocalDateTime.parse("2022-09-30 11:00", formatter), usuario1);
		Payment payment14 = new Payment(120.00, LocalDateTime.parse("2022-11-30 11:00", formatter), usuario1);
		Payment payment15 = new Payment(100.00, LocalDateTime.parse("2022-12-05 11:00", formatter), usuario1);
		Payment payment16 = new Payment(120.00, LocalDateTime.parse("2022-07-10 11:00", formatter), usuario1);
		Payment payment17 = new Payment(100.00, LocalDateTime.parse("2022-05-02 11:00", formatter), usuario1);
		Payment payment18 = new Payment(50.00, LocalDateTime.parse("2021-09-01 11:00", formatter), usuario1);
		Payment payment19 = new Payment(120.00, LocalDateTime.parse("2022-12-15 11:00", formatter), usuario1);
		Payment payment20 = new Payment(100.00, LocalDateTime.parse("2022-11-25 11:00", formatter), usuario1);
		Payment payment21 = new Payment(100.00, LocalDateTime.parse("2022-12-05 11:00", formatter), usuario1);
		Payment payment22 = new Payment(120.00, LocalDateTime.parse("2022-07-10 11:00", formatter), usuario1);
		Payment payment23 = new Payment(100.00, LocalDateTime.parse("2022-05-02 11:00", formatter), usuario1);
		Payment payment24 = new Payment(50.00, LocalDateTime.parse("2021-09-01 11:00", formatter), usuario1);
		Payment payment25 = new Payment(120.00, LocalDateTime.parse("2022-12-15 11:00", formatter), usuario1);
		Payment payment26 = new Payment(100.00, LocalDateTime.parse("2022-11-25 11:00", formatter), usuario1);
		payment2.setStatus(PaymentStatus.PAID);
		payment12.setStatus(PaymentStatus.PAID);
		payment10.setStatus(PaymentStatus.PAID);
		payment13.setStatus(PaymentStatus.PAID);
		payment14.setStatus(PaymentStatus.PAID);
		
		usuario1.setPayments(Arrays.asList(payment1, payment2, payment3, payment4, payment5, payment6, payment7, payment8, payment9,
				payment10, payment11, payment12, payment13, payment14, payment15, payment16, payment17, payment18, payment19, payment20,
				payment21, payment22, payment23, payment24, payment25, payment26));
		

		usuarioRepository.saveAll(Arrays.asList(usuario1));
		
		paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3, payment4, payment5, payment6, payment7, payment8, payment9,
				payment10, payment11, payment12, payment13, payment14, payment15, payment16, payment17, payment18, payment19, payment20,
				payment21, payment22, payment23, payment24, payment25, payment26));
		



	}

}