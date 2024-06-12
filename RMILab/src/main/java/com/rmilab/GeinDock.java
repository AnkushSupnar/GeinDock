package com.rmilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rmilab"})
public class GeinDock extends SpringBootServletInitializer {
	//private static final Logger logger = LoggerFactory.getLogger(GeinDock.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GeinDock.class);
	}
	public static void main(String[] args) {
		//logger.debug("Application starts");
		SpringApplication.run(GeinDock.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
