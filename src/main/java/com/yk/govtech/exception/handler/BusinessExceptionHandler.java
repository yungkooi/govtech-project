package com.yk.govtech.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.yk.govtech.exception.BaseException;
import com.yk.govtech.model.ErrorResponse;

@ControllerAdvice
public class BusinessExceptionHandler {
	
	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	protected ResponseEntity<ErrorResponse> handleNumberFormatException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getClass() + " -> " + ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	protected ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleGenericException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getClass() + " -> " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
