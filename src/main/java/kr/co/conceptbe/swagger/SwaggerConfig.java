package kr.co.conceptbe.swagger;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Configuration
@SecurityScheme(name = AUTHORIZATION, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo())
			.components(new Components());

	}

	private Info apiInfo() {
		return new Info()
			.title("Conceptbe")
			.description("Conceptbe Swagger UI")
			.version("1.0.0");
	}

}
