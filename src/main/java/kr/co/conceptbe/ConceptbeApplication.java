package kr.co.conceptbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ConceptbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConceptbeApplication.class, args);
	}

}
