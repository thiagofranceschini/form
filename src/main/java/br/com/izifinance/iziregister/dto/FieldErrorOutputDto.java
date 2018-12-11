package br.com.izifinance.iziregister.dto;

public class FieldErrorOutputDto {

	private String field;
	private String message;
	
	public FieldErrorOutputDto() {
		super();
	}

	public FieldErrorOutputDto(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
