package br.com.izifinance.iziregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IziregisterApplication{

	public static void main(String[] args) {
		SpringApplication.run(IziregisterApplication.class, args);
	}
	
}
