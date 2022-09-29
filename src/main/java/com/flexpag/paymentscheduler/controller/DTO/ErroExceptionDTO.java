package com.flexpag.paymentscheduler.controller.DTO;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public class ErroExceptionDTO implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String specificError;
	private String path;
	
	public ErroExceptionDTO() {
		
	}

	public ErroExceptionDTO(Instant timestamp, Integer status, String error, String message,String specificError, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.specificError = specificError;
	}

}