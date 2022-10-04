package com.flexpag.paymentscheduler.controller.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.flexpag.paymentscheduler.controller.DTO.ErroExceptionDTO;
import com.flexpag.paymentscheduler.controller.DTO.ErroFormDTO;
import com.flexpag.paymentscheduler.services.exceptions.InvalidDateException;
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
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), e.getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErroExceptionDTO> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
		String mensagem = "JSON parse error: parametro em formato inválido";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), mensagem, e.getMostSpecificCause().getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceAccessDenied.class)
	public ResponseEntity<ErroExceptionDTO> resourceAccessDenied(ResourceAccessDenied e, HttpServletRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), e.getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ErroExceptionDTO> invalidDateException(InvalidDateException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase() , e.getMessage(), e.getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroExceptionDTO> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		String specificError = e.getMostSpecificCause().getLocalizedMessage();
		String mensagem = e.getMessage().subSequence(0, 31).toString() + ": "+specificError;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), mensagem, specificError, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErroExceptionDTO> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), e.getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ErroExceptionDTO> propertyReferenceException(PropertyReferenceException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroExceptionDTO err = new ErroExceptionDTO(Instant.now(), status.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), e.getLocalizedMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
