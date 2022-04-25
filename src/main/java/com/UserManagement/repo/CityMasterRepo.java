package com.UserManagement.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserManagement.entity.CityMasterEntity;

public interface CityMasterRepo extends JpaRepository<CityMasterEntity,Serializable>{
	
	public List<CityMasterEntity> findByStateId(Integer stateID);

}
