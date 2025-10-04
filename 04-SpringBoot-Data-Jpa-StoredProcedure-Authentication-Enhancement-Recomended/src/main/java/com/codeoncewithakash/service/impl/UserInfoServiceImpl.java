package com.codeoncewithakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewithakash.constants.UserInfoConstant;
import com.codeoncewithakash.entity.UserInfo;
import com.codeoncewithakash.exception.BadCredentialsException;
import com.codeoncewithakash.exception.UserInfoNotFoundException;
import com.codeoncewithakash.payload.UserInfoResponseDto;
import com.codeoncewithakash.payload.UserRegistrationRequestDto;
import com.codeoncewithakash.repo.UserInfoRepo;
import com.codeoncewithakash.service.IUserInfoService;
import com.codeoncewithakash.util.UserInfoUtil;

/**
	CREATE OR REPLACE PROCEDURE P_USER_AUTHENTICATION 
	(
	  UNAME IN VARCHAR2 
	, PWD IN VARCHAR2 
	, LOGIN_RESULT OUT VARCHAR2 
	) AS 
	    CNT NUMBER;
	BEGIN
	    SELECT COUNT(*) INTO CNT FROM SP_USER_INFO WHERE USER_NAME=UNAME AND PASSW=PWD; 
	    
	    IF(CNT<>0) THEN
	        LOGIN_RESULT:='LOGGED IN SUCCESSFULLY!';
	    ELSE
	        LOGIN_RESULT:='BAD CREDENTIALS!';
	    END IF;
	END P_USER_AUTHENTICATION;
 */

@Service
public class UserInfoServiceImpl implements IUserInfoService{

	private final UserInfoRepo userInfoRepo;
	
	public UserInfoServiceImpl(UserInfoRepo userInfoRepo) {
		this.userInfoRepo = userInfoRepo;
	}
	
	@Override
	public String registerUser(UserRegistrationRequestDto requestDto) {
		UserInfo userInfo = UserInfo.builder()
		.firstName(requestDto.firstName())
		.lastName(requestDto.lastName())
		.phoneNumber(requestDto.phoneNumber())
		.username(requestDto.username())
		.password(requestDto.password())
		.dob(requestDto.dob())
		.build();
		
		UserInfo registeredUserInfo = userInfoRepo.save(userInfo);
		
		return String.format(UserInfoConstant.USER_REGISTER_SUCCESS_MSG, registeredUserInfo.getId());
	}

	@Override
	public UserInfoResponseDto fetchUserInfoById(Long userId) {
		UserInfo userInfo = userInfoRepo.findById(userId).orElseThrow(() -> new UserInfoNotFoundException(String.format(UserInfoConstant.USER_BY_ID_FAILUER_MSG, userId)));
		return UserInfoUtil.mapToUserInfoResponseDto(userInfo);
	}

	@Override
	public String login(String username, String password) {
		//1. Call Stored Procedure 
		String result = userInfoRepo.authenticate(username, password);
		
		//2. Check result
		if(UserInfoConstant.USER_LOGIN_BAD_CRED_MSG.equals(result))
			throw new BadCredentialsException(result);
		 
		return result;
	}
	
}
