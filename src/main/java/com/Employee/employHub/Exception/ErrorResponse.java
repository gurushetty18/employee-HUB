package com.Employee.employHub.Exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String erro;
	private String message;
	private String path;
	
	
	public ErrorResponse(LocalDateTime timestamp, int status, String erro, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.message = message;
		this.path = path;
	}
	
	
}
