package com.robsonArcoleze.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.robsonArcoleze.dscommerce.DTO.CustomError;
import com.robsonArcoleze.dscommerce.DTO.ValidationError;
import com.robsonArcoleze.dscommerce.services.exceptions.DataBaseException;
import com.robsonArcoleze.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}

@ExceptionHandler(DataBaseException.class)
public ResponseEntity<CustomError> databaseException(DataBaseException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
	ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados Inválidos", request.getRequestURI());
	
	for(FieldError f : e.getBindingResult().getFieldErrors()) {
		err.addError(f.getField(), f.getDefaultMessage());
	}
	
	return ResponseEntity.status(status).body(err);
	}

}
