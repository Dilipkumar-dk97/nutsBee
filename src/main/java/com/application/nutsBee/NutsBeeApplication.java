package com.application.nutsBee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NutsBeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutsBeeApplication.class, args);
	}
	
	@Configuration
    public class WebConfig implements WebMvcConfigurer {
 
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000","https://kumaraguru-11.github.io/SNT_Project")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS" , "PATCH")
                    .allowedHeaders("Authorization", "Content-Type", "Access-Control-Allow-Origin")
                    .allowCredentials(true)
                    .maxAge(3600);
        }
    }
}
