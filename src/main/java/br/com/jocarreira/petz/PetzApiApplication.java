package br.com.jocarreira.petz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class PetzApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetzApiApplication.class, args);
	}

}
