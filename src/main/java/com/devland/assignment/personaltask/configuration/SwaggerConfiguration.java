package com.devland.assignment.personaltask.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI app() {
        Info info = new Info();
        info.setTitle("Personal Tasks");
        info.setDescription("Assignment 10");
        info.setVersion("1.0.1");

        return new OpenAPI().info(info);
    }
}