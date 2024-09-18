package com.parkapi.park.exception;

public class UsernameUniqueViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameUniqueViolationException(String message) {
		super(message);
	}
}
