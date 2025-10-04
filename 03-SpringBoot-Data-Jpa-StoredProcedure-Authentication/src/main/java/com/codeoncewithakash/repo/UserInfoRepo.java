package com.codeoncewithakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewithakash.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

}
