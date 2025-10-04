package com.codeoncewithakash.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeoncewithakash.exception.BadCredentialsException;
import com.codeoncewithakash.exception.UserInfoNotFoundException;
import com.codeoncewithakash.payload.ErrorResponse;

@RestControllerAdvice
public class UserInfoGlobalExceptionHandler {

	@ExceptionHandler(exception = UserInfoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserInfoNotFoundExeption(UserInfoNotFoundException uinfe) {
		return new ResponseEntity<>(new ErrorResponse(uinfe.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(exception = BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException bce) {
		return new ResponseEntity<>(new ErrorResponse(bce.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
	}
}
