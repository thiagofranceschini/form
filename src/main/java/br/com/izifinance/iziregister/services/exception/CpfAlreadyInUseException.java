package br.com.izifinance.iziregister.services.exception;

public class CpfAlreadyInUseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CpfAlreadyInUseException(String exception) {
		super(exception);
	}
	
	
}
