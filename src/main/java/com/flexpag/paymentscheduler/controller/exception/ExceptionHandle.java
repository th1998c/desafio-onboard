package com.flexpag.paymentscheduler.controller.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flexpag.paymentscheduler.controller.DTO.ErroExceptionDTO;
import com.flexpag.paymentscheduler.controller.DTO.ErroFormDTO;
import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;



@RestControllerAdvice
public class ExceptionHandle{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErroFormDTO> hanler(MethodArgumentNotValidException exception,HttpServletRequest request) {
		List<ErroFormDTO> errosDto = new ArrayList<>();
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		
		fieldErros.forEach(e -> { 
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDTO erro = new ErroFormDTO(e.getField(), mensagem);
			errosDto.add(erro);
		});
		
		return errosDto;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErroExceptionDTO> noSuchElementException(NoSuchElementException e, HttpServletRequest request){
		String error = "Not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErroExceptionDTO> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
		String error = "invalid data";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(ResourceAccessDenied.class)
	public ResponseEntity<ErroExceptionDTO> resourceAccessDenied(ResourceAccessDenied e, HttpServletRequest request){
		String error = "Access Denied";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	
}
