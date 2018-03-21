package com.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket uploadApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		 .groupName("CUSTOMER-API")
                .select()               
                .paths(PathSelectors.ant("/api/customer/v1/**"))
                .build()
                .apiInfo(apiInfo());
    }
   
  
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rest API")
				.description("Rest API reference for developers")
				.termsOfServiceUrl("http://pivotal.io")
				.contact("lakshman29@gmail.com").license(" License")
				.licenseUrl("lakshman29@gmail.com").version("1.0").build();
	}
}