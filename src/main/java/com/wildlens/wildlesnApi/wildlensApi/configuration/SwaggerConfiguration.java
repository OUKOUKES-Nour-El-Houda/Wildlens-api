package com.wildlens.wildlesnApi.wildlensApi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SwaggerConfiguration {

    @Value("${app.wildlens-prefix-url}")
    private String wildlensPrefixUrl;

    private final ObjectMapper objectMapper;

    public SwaggerConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public OpenAPI openAPI() {
        ModelConverters.getInstance().addConverter(new ModelResolver(objectMapper));
        return new OpenAPI()
                .info(new Info()
                        .title("Wildlens API")
                        .description("API for managing wildlife data and observations")
                        .version("v1")
                        .contact(new Contact()
                                .name("Wildlens Team")
                                .email("nourelhoudaoukoukes@gmail.com")))
                .addServersItem(new Server().url(wildlensPrefixUrl));
    }

    @Bean
    public GroupedOpenApi wildlensApis() {
        return GroupedOpenApi.builder().group("wildlens").pathsToMatch("/**").build();
    }
}
