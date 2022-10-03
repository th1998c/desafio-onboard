package com.flexpag.paymentscheduler.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations {
				
	
		 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
	        .authorizeHttpRequests()
	        .antMatchers("/**").permitAll()
	        .and().csrf().disable();
			
			 http.headers().frameOptions().disable();
			return http.build();        
		}
		 	 
}
