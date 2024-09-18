package com.parkapi.park.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage {

	private String path;
	private String method;
	private int status;
	private String statusText;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private Map<String, String> erros;
	
	public ErrorMessage() {
	}
	
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}
	
	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErrors(result);
	}

	private void addErrors(BindingResult result) {
		this.erros = new HashMap<>();
		for (FieldError fieldError : result.getFieldErrors()) {
			this.erros.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	public String getPath() {
		return path;
	}

	public String getMethod() {
		return method;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusText() {
		return statusText;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getErros() {
		return erros;
	}

	@Override
	public String toString() {
		return "ErrorMessage [path=" + path + ", method=" + method + ", status=" + status + ", statusText=" + statusText
				+ ", message=" + message + ", erros=" + erros + "]";
	}
}
