package com.marco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageModuleConfig {
    @Bean
    public WebMvcConfigurer corsConfigurerImage() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/v1")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .exposedHeaders("*");
            }

        };

    }

}
