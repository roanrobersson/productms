package com.compasso.productms_h2.controllers.exceptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.compasso.productms_h2.dto.ProductDTO;
import com.compasso.productms_h2.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ProductDTO> entityNotFound(ResourceNotFoundException e) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Serializable>> invalidMethod(MethodArgumentNotValidException e) {
		Map<String, Serializable> err = new HashMap<String, Serializable>();
		err.put("status_code", 400);
		err.put("message", "Validation error");
		return ResponseEntity.badRequest().body(err);
	}
}
