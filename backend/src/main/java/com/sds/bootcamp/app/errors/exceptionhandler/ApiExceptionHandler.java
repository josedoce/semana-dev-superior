package com.sds.bootcamp.app.errors.exceptionhandler;

import java.time.OffsetDateTime;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sds.bootcamp.app.errors.exception.TesteException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDatetime(OffsetDateTime.now());
		problema.setTitle(ex.getMessage());
		problema.setErrors(null);
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(TesteException.class)
	public ResponseEntity<Object> handleTeste(TesteException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDatetime(OffsetDateTime.now());
		problema.setTitle(ex.getMessage());
		problema.setErrors(null);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
		
}
