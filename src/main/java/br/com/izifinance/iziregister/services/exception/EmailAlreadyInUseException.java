package br.com.izifinance.iziregister.services.exception;

public class EmailAlreadyInUseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyInUseException(String exception) {
		super(exception);
	}
	
	
}
