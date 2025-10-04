package com.codeoncewithakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.codeoncewithakash.constants.UserInfoConstant;
import com.codeoncewithakash.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
	
	//@Procedure(name = "P_USER_AUTHENTICATION")
	@Procedure(procedureName = UserInfoConstant.SP_USER_AUTHENTICATION)
	String authenticate(@Param("UNAME") String UNAME, @Param("PWD") String PWD);

}
