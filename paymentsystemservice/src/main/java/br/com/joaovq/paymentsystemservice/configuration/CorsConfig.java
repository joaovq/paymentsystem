package br.com.joaovq.paymentsystemservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                var allowedOrigins = new String[]{
                        "http://localhost:8080",
                    "https://paymentsystem-pagnet-webapp.onrender.com"
                };
                registry.addMapping("/cnab").allowedOrigins(allowedOrigins);
                registry.addMapping("/transactions").allowedOrigins(allowedOrigins);
            }
        };
    }
}
