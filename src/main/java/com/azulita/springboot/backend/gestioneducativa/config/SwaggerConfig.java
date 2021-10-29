package com.azulita.springboot.backend.gestioneducativa.config;


/**
 * @author SebasNavarro
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
                .paths(regex("/api/v1.*"))
                .build()
                .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	            .title("Gestión educativa API")
	            .description("This application designed for integration")
	             .build();
	}
}
