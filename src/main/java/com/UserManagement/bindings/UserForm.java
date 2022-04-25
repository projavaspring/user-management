package com.UserManagement.bindings;



import lombok.Data;

@Data
public class UserForm {

	private String fname;
	
	private String lname;
	private String email;
	private String password;
	private String accStatus;
	private Long phone;
	private String dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	
}
