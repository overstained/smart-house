package com.overstained.smarthousemanager.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CleaningControllerAdvice extends ResponseEntityExceptionHandler {
	
	/*
	 * If request arguments are invalid, respond with Unprocessable Entity status and
	 * with the fields with issues
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		Map<String, Set<String>> errorsMap = fieldErrors.stream().collect(Collectors.groupingBy(FieldError::getField,
				Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
		if (errorsMap.isEmpty()) {
			ex.getBindingResult().getAllErrors().stream()
					.forEach(err -> errorsMap.put(err.getCodes()[1], Collections.singleton(err.getDefaultMessage())));
		}
		return new ResponseEntity<>(errorsMap, headers, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
