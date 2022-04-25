package com.UserManagement.service;

import java.util.Map;

import com.UserManagement.bindings.LoginForm;
import com.UserManagement.bindings.UnlockAccForm;
import com.UserManagement.bindings.UserForm;

public interface UserMgmtService {

	//Login screen related methods
	public String LoginCheck(LoginForm loginForm);
	
	public String EmailCheck(String emailId);
	
	
	//Registration check methods
	public Map<Integer,String> LoadCountries();
	
	public Map<Integer,String> LoadStates(Integer CountryId);
	
	public Map<Integer,String> LoadCity(Integer StateId);
	
	
	//unlock screen related 
	
	public String unlockUserAcc (UnlockAccForm unlockAccForm);
	
	//forgot password screen related methods
	
	public String forgotPwd(String email);
	
	public String SaveUSerForm(UserForm userForm);
}

