package ru.ae.coursemodel.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Basic",
        scheme = "basic",
        type = SecuritySchemeType.HTTP)
public class OpenApiConfig {
}
