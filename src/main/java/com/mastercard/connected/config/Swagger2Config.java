package com.mastercard.connected.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mastercard.connected.controller"))
                .paths(PathSelectors.ant("/connected"))
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("MasterCard Coding Challenge"
                , "Determine whether there exists a road between given two cities"
                , "1.0.0", "Terms of service URL"
                , new Contact("Shekar Nyala", "https://github.com/nshekarb4u/mc-connected-api", "nshekarb4u@gmail.com")
                , "License of API", "REST API license URL", Collections.emptyList());
        return apiInfo;
    }
}
