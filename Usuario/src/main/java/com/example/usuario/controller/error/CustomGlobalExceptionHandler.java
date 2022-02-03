package com.example.usuario.controller.error;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UsuarioNotFoundException.class)
	public void springHandleUsuarioNotFound(HttpServletResponse response) throws IOException {
		logger.info("------ UsuarioNotFoundException() ");
		response.sendError(HttpStatus.NOT_FOUND.value());
	}
	
	@ExceptionHandler(EntradaNotFoundException.class)
	public void springHandleEntradaNotFound(HttpServletResponse response) throws IOException {
		logger.info("------ EntradaNotFoundException() ");
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public void springHandleIncorrectPassword(HttpServletResponse response) throws IOException {
		logger.info("------ IncorrectPasswordException() ");
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		logger.info("------ ConstraintViolationException() ");
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public void sqlConstraintViolationException(SQLIntegrityConstraintViolationException ex,
			HttpServletResponse response) throws IOException {
		logger.info("------ SQLConstraintViolationException() ");
		response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.info("------ handleMethodArgumentNotValid()");

		CustomErrorJson customError = new CustomErrorJson();

		customError.setTimestamp(new Date());
		// customError.setTrace(ex.getLocalizedMessage());
		customError.setStatus(status.value());
		customError.setError(status.name());

		// Get all errors indicando el campo en el que falla
		List<String> messages = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			messages.add(error.getField() + ": " + error.getDefaultMessage());
		}
		customError.setMessage(messages);

		// Para recoger el path y simular de forma completa los datos originales
		// request.getDescription(false) ---> uri=/students
		String uri = request.getDescription(false);
		uri = uri.substring(uri.lastIndexOf("=") + 1);
		customError.setPath(uri);

		return new ResponseEntity<>(customError, headers, status);

	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("------ handleHttpRequestMethodNotSupported()");
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		body.put("status", status.value());
		body.put("error", ex.getLocalizedMessage());
		body.put("message", builder.toString());

		return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);

	}

}