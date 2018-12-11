package br.com.izifinance.iziregister.controller.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izifinance.iziregister.dto.ValidationErrorsOutputDto;
import br.com.izifinance.iziregister.services.exception.CpfAlreadyInUseException;
import br.com.izifinance.iziregister.services.exception.EmailAlreadyInUseException;
import br.com.izifinance.iziregister.services.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Not Found!",
				e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);
	}

	@ExceptionHandler(EmailAlreadyInUseException.class)
	public ResponseEntity<StandardError> EmailAlreadyInUseException(EmailAlreadyInUseException e,
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(System.currentTimeMillis(), httpStatus.value(),
				"Email is already in use!", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);
	}

	@ExceptionHandler(CpfAlreadyInUseException.class)
	public ResponseEntity<StandardError> CpfAlreadyInUseException(CpfAlreadyInUseException e,
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(System.currentTimeMillis(), httpStatus.value(),
				"CPF is already in use!", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(httpStatus).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {

		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

		globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));

		fieldErrors.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErrors.addFieldErro(error.getField(), errorMessage);
		});

		return validationErrors;
	}
	

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
