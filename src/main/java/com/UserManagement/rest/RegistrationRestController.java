package com.UserManagement.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagement.bindings.UserForm;
import com.UserManagement.service.UserMgmtService;

@RestController
@CrossOrigin
public class RegistrationRestController {

	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/emailCheck/{email}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String emailid)
	{
	String status=service.EmailCheck(emailid);
	
	return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@GetMapping("/countries")
	public ResponseEntity<Map<Integer,String>> getCountries()
	{
	Map<Integer,String> countriesMap=service.LoadCountries();
	
	return new ResponseEntity<>(countriesMap,HttpStatus.OK);
	}
	
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer,String>> getStates(@PathVariable("CountryId")Integer countryId)
	{
	Map<Integer,String> statesMap=service.LoadStates(countryId);
	
	return new ResponseEntity<>(statesMap,HttpStatus.OK);
	}
	
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<Map<Integer,String>> getCities(@PathVariable("stateId")Integer stateId)
	{
	Map<Integer,String> cityMap=service.LoadStates(stateId);
	
	return new ResponseEntity<>(cityMap,HttpStatus.OK);
	
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(UserForm userform)
	{
		String status=service.SaveUSerForm(userform);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
}
