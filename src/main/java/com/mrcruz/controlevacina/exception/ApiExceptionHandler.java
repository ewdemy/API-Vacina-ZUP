package com.mrcruz.controlevacina.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.apache.tomcat.util.json.ParseException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> handleDataException(DateTimeParseException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("data inválida, usar formato: dd/MM/yyyy");
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleNegocio(DataIntegrityViolationException ex, WebRequest request) {
		var status = HttpStatus.NOT_ACCEPTABLE;
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("E-mail já cadastrado");
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(TransientPropertyValueException.class)
	public ResponseEntity<Object> handleCPFInexistente(TransientPropertyValueException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Usuário não encontrado");
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CpfExistenteException.class)
	public ResponseEntity<Object> handleCPFExistente(CpfExistenteException ex, WebRequest request) {
		var status = HttpStatus.NOT_ACCEPTABLE;
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			var campos = new ArrayList<Problema.Campo>();
			for(ObjectError error: ex.getBindingResult().getAllErrors()) {
				String nome = ((FieldError) error).getField();
				String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
				campos.add(new Problema.Campo(nome, mensagem));
			}
			var problema =  new Problema();
			problema.setStatus(status.value());
			problema.setTitulo("Um ou mais campos inválidos, preencha corretamente!");
			problema.setDataHora(LocalDateTime.now());
			problema.setCampos(campos);
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}
