package com.flexpag.paymentscheduler.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.flexpag.paymentscheduler.repositories.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {
				

	    @Autowired
	    private TokenService tokenService;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder();
	    }

	
		 @Bean
		    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.GET, "/payments").permitAll()
            .antMatchers(HttpMethod.GET, "/payments/*").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            //rotas liberadas para front não precisar usar autenticação
            .antMatchers(HttpMethod.POST, "/payments").permitAll()
            .antMatchers(HttpMethod.PUT, "/payments/*").permitAll()
            .antMatchers(HttpMethod.PATCH, "/payments/*").permitAll()
            .antMatchers(HttpMethod.DELETE, "/payments/*").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            //fim das autorizacoes provisorias
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
			
			// 2 alteracao rotas liberadas para front não precisar usar autenticação
			 http.headers().frameOptions().disable();
			return http.build();        
		}

}
