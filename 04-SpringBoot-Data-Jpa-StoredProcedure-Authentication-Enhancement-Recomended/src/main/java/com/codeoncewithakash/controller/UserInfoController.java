package com.codeoncewithakash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewithakash.payload.UserInfoResponseDto;
import com.codeoncewithakash.payload.UserRegistrationRequestDto;
import com.codeoncewithakash.service.IUserInfoService;

@RestController
@RequestMapping("/api/v1/user")
public class UserInfoController {
	
	private final IUserInfoService userInfoService;
	
	public UserInfoController(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequestDto requestDto) {
		return new ResponseEntity<>(userInfoService.registerUser(requestDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<UserInfoResponseDto> getUserInfoById(@PathVariable(name = "id") Long userId) {
		return ResponseEntity.ok(userInfoService.fetchUserInfoById(userId));
	}
	
	@GetMapping("login/{username}/{password}")
	public ResponseEntity<String> login(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password) {
		return ResponseEntity.ok(userInfoService.login(username, password));
	}
}
