package com.Employee.employHub.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDriverNotFound(EmployeeNotFoundException ex, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(),
				"Driver Not Found", 
				ex.getMessage(), 
				webRequest.getDescription(false)
				);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> HandleGenericException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Errro",
				ex.getMessage(), 
				request.getDescription(false)
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
