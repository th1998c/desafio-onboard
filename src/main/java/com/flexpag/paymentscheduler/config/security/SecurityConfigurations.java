package com.flexpag.paymentscheduler.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flexpag.paymentscheduler.repositories.UsuarioRepository;

@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations  implements WebMvcConfigurer{
				
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
	        .antMatchers(HttpMethod.DELETE, "/payments/*").permitAll()
	        .antMatchers(HttpMethod.PATCH, "/payments/*").permitAll()
	        .antMatchers(HttpMethod.PUT, "/payments/**").permitAll()
	        .antMatchers(HttpMethod.POST, "/payments/**").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth").permitAll()
	        .antMatchers(HttpMethod.POST, "/user").permitAll()
	        .antMatchers(HttpMethod.GET, "/user/*").permitAll()
	        .antMatchers().permitAll()
	        .anyRequest().authenticated()
	        .and().cors()
	        .and().csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class)
	        .headers().frameOptions().sameOrigin()
	        .xssProtection().block(false);
			
			return http.build();        
		}
		 
		 @Bean
		 public WebSecurityCustomizer webSecurityCustomizer() {
		        return (web) -> web.ignoring().
		        		antMatchers("/**/*.html", "/**/*.css", "/**/*.js","*/resources/**", "/**/*.svg","/img/**" , "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**",  "/swagger-ui/**", "/swagger*/**", "/swagger-ui/**", "/v3/api-docs/**");
		}
		
}
