package com.flexpag.paymentscheduler.resources.exception;

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

import com.flexpag.paymentscheduler.resources.DTO.ErroFormDTO;



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
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErroFormDTO> noSuchElementException(NoSuchElementException e, HttpServletRequest request ){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroFormDTO err = new ErroFormDTO(request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErroFormDTO> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request ){
		String error = "Dado Inválido";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroFormDTO err = new ErroFormDTO(request.getRequestURI(),error);
		return ResponseEntity.status(status).body(err);
	}
	
}
