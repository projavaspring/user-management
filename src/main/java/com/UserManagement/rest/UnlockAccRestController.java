package com.UserManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagement.bindings.UnlockAccForm;
import com.UserManagement.service.UserMgmtService;

@RestController
@CrossOrigin
public class UnlockAccRestController {

	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/unlock")
	public ResponseEntity <String> unlockAcc(@RequestBody UnlockAccForm unlockAcc)
	
	{
	String status=service.unlockUserAcc(unlockAcc);
	
	return new ResponseEntity<>(status,HttpStatus.OK);
		
	}
	
}
