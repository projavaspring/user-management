package com.UserManagement.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserManagement.entity.UserManageEntity;

public interface UserMgmtRepo extends JpaRepository<UserManageEntity,Serializable> {

	public UserManageEntity findByEmailAndPassword(String email,String Password);
	
	public UserManageEntity findByEmail(String email);
}
