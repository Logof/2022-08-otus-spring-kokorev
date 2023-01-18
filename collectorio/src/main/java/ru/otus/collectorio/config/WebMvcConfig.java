package ru.otus.collectorio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	//@Value("cors.allowedOrings")
	//private String allowedOrigins;

	//TODO Перенести в конфиги
	public void addCorsMappings(CorsRegistry registry) {
		// Set up routes that allow cross domains
		registry.addMapping("/**")
					// Set the domain name that allows cross-domain requests
					.allowedOriginPatterns("*")
					// Whether to allow certificates (cookies)
					.allowCredentials(true)
					// Set allowed methods
					.allowedMethods("*")
					// Set allowed request headers
					.allowedHeaders("*");
	}
}
