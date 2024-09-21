package com.parkapi.park.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocApiConfig {

    @Bean
    OpenAPI openAPI() {
		return new OpenAPI()
			.info(
				new Info()
					.title("REST API - Spring Boot")
					.description("API para gestão de estacionamento de veiculos")
					.version("v1")
					.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
					.contact(new Contact().name("Lincoln Dias").email("lincolnroberto820@gmail.com"))
			);
	}
}





















