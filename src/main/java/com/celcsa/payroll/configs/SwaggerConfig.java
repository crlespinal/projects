package com.celcsa.payroll.configs;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.celcsa.payroll.controllers"))
                .paths(PathSelectors.any()).build().apiInfo((apiInfo()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Payroll Processing", "description", "1.0", "termsOfServiceUrl",
                new springfox.documentation.service.Contact("Carlos Espinal", "url", "email"), "license", "licenseUrl",
                Collections.emptyList());
    }
}