package com.overstained.smarthousemanager;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.overstained.smarthousemanager.api"))
				.paths(regex("/clean.*")).build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Smart house manager", 
	      "Exposes devices functionality as a rest API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Vlad Radu", "", "vradu.alex@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

}
