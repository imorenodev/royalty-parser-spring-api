package com.iancmoreno.royaltyparserspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Ian Moreno
 * This class enables CORS requests for local testing
 */

@Configuration
public class RoyaltyParserConfig {
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("GET", "PUT", "POST", "DELETE");
            }
        };
    }
}
