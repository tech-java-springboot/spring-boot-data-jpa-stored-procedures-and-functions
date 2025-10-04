package com.codeoncewithakash.exception;

public class BadCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadCredentialsException() {
		super();
	}
	
	public BadCredentialsException(String message) {
		super(message);
	}
}
