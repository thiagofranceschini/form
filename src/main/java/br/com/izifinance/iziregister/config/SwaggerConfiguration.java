package br.com.izifinance.iziregister.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.izifinance.iziregister"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apInfo());
	}
	
	private ApiInfo apInfo() {
		Contact contato = new Contact("IziFinance", "https://izifinance.com/", "thiagoconsultor1@gmail.com");
		return new ApiInfoBuilder()
				.title("IziRegister - Api de registro de usuários ao IziBank")
				.description("interactive user api documentation")
				.version("BETA")
				.contact(contato)
				.build();
	}
}
