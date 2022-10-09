package com.qds.backend.evaluacion.springrestqdsnotas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .description("Bearer Token (Colocar primero la palabra Bearer luego dejar un espacio)")
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qds.backend.evaluacion.springrestqdsnotas.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(getApiInfo()).globalOperationParameters(parameters);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Universidad Notas",
                "Servicio para Crear y Listar Notas",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Fernando Huaman", "https://www.linkedin.com/in/fernandomhm/", "mfernando@hotmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
