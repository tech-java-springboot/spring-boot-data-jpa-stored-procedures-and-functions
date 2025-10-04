package com.codeoncewithakash.exception;

public class UserInfoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserInfoNotFoundException() {
		super();
	}
	
	public UserInfoNotFoundException(String message) {
		super(message);
	}
}
