package com.robsonArcoleze.dscommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.info(new Info()
						.title("DSCommerce")
						.version("v1")
						.description("Esta API fornece recursos para a aplicação DSCommerce")
						.termsOfService("https://github.com/RobsonArcoleze/DsCommerce")
						.license(
								new License()
								.name("Apache 2.0")
								.url("https://github.com/RobsonArcoleze/DsCommerce"))
						);
	} 
}
