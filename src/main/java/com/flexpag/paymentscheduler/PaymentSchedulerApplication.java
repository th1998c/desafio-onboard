package com.flexpag.paymentscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class PaymentSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentSchedulerApplication.class, args);
	}

}
