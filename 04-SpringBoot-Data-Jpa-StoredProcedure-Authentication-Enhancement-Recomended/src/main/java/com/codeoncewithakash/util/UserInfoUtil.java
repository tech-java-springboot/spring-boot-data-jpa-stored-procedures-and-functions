package com.codeoncewithakash.util;

import com.codeoncewithakash.entity.UserInfo;
import com.codeoncewithakash.payload.UserInfoResponseDto;

public class UserInfoUtil {
	
	private UserInfoUtil() {
		
	}
	
	public static UserInfoResponseDto mapToUserInfoResponseDto(UserInfo userInfo) {
		return new UserInfoResponseDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPhoneNumber(), userInfo.getUsername(), userInfo.getDob());
	}
}

