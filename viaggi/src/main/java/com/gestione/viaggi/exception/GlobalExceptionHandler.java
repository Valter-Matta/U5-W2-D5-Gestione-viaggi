package com.gestione.viaggi.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler (value = EntityNotFoundException.class)
	protected ResponseEntity<Error> entityNotFound (EntityNotFoundException ex) {
		Error err = new Error();
		err.setMessage("Entity not found");
		err.setDetails(ex.getMessage());
		err.setStatus("404");
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EntityExistsException.class)
	protected ResponseEntity<Error> alreadyExistsException(EntityExistsException ex) {
		Error err = new Error();
		err.setMessage("Entity already exists");
		err.setDetails(ex.getMessage());
		err.setStatus("409");
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConsraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String fieldName = violation.getPropertyPath().toString();
			if (fieldName.contains(".")) {
				fieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
			}
			errors.put(fieldName, violation.getMessage());
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}