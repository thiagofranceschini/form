package br.com.izifinance.iziregister.services;

import org.springframework.stereotype.Service;

import br.com.izifinance.iziregister.model.Register;

@Service
public class FileServices {
	
	private static String BASE_FOLDER="\\izibank\\";

	public String mkdir(Register register) {
		return BASE_FOLDER+register.getId();
	}
}
