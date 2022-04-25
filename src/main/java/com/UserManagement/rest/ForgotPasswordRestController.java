package com.UserManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.UserManagement.service.UserMgmtService;

@RestController
@CrossOrigin
public class ForgotPasswordRestController {

	@Autowired
	private UserMgmtService service;
	
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity <String> forgotPwd(@RequestParam String email)
	
	{
	String status=service.forgotPwd(email);
	
	return new ResponseEntity<>(status,HttpStatus.OK);
		
	}
}
