package com.elhatron.amnesia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AmnesiaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmnesiaServerApplication.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer(Environment environment) {
		return factory -> {
			//Przygotowanie do przyszłego użycia - kofiguracja serwera pod własny użytek

			String port = environment.getProperty("server.port");
			String url = environment.getProperty("spring.datasource.url");

			System.out.println("Port: " + port);
			System.out.println("Url: " + url);
			/*
			String port = "3031";
			if (port != null) {
				factory.setPort(Integer.parseInt(port));

				System.out.println("Port: " + port);
			}
			*/
		};
	}

}
