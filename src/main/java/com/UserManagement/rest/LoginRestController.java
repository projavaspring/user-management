package com.UserManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagement.bindings.LoginForm;
import com.UserManagement.service.UserMgmtService;

@RestController
@CrossOrigin
public class LoginRestController {

	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> Login(@RequestBody LoginForm loginfrm)
	{
		
		String status=service.LoginCheck(loginfrm);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}
