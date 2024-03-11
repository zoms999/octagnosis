package com.aptit.octagnosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OctagnosisApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctagnosisApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")  // Allow requests from any origin
						.allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")
						.allowedHeaders("*");
			}
		};
	}
}
