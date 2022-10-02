package com.flexpag.paymentscheduler.config.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfigurations implements WebMvcConfigurer {
	
	@Bean
	public GroupedOpenApi publicApi() {
	    return GroupedOpenApi.builder()
	            .group("com.flexpag")
	            .pathsToMatch("/**")
	            .build();
	}

	@Bean
	public OpenAPI paymentSchedulerOpenAPI() {
	    return new OpenAPI()
	            .info(new Info().title("PaymentScheduler API")
	                            .description("Documentacao de API PaymentScheduler")
	                            .version("v1.0.0")
	                            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	            				.externalDocs(new ExternalDocumentation()
	            				.description("SpringShop Wiki Documentation")
	            				.url("https://springshop.wiki.github.org/docs"))
	            				.components(new Components()
	            				.addSecuritySchemes("bearer-key",new SecurityScheme()
	            				.type(SecurityScheme.Type.HTTP).scheme("bearer").in(SecurityScheme.In.HEADER).bearerFormat("JWT")));
	}
	
}
