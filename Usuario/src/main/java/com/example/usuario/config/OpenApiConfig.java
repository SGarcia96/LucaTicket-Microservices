package com.example.usuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI StudentOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Usuarios API")
                .description("Documentación de Usuarios API")
                .version("v1.0")
                .contact(new Contact().name("Grupo 3").email("lucatic_grupo03@gmail.es"))
                .license(new License().name("LICENSE").url("http://springdoc.org")));
    }
    
}
