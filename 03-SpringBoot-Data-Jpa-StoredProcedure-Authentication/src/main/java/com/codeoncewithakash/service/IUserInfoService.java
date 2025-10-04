package com.codeoncewithakash.service;

import com.codeoncewithakash.payload.UserRegistrationRequestDto;
import com.codeoncewithakash.payload.UserInfoResponseDto;

public interface IUserInfoService {
	public String registerUser(UserRegistrationRequestDto requestDto);
	public UserInfoResponseDto fetchUserInfoById(Long userId);
	public String login(String username, String password);
}
