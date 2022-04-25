package com.UserManagement.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserManagement.entity.CountryMasterEntity;

public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity,Serializable>{

}
