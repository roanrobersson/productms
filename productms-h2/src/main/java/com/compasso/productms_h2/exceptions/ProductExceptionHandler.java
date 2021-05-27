package com.compasso.productms_h2.exceptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.compasso.productms_h2.dto.ProductDTO;

@ControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler({ EmptyResultDataAccessException.class, EntityNotFoundException.class,
			IllegalArgumentException.class, NoSuchElementException.class })
	public ResponseEntity<ProductDTO> entityNotFound(RuntimeException e) {
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
