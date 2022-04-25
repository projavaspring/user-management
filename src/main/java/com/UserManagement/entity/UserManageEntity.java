package com.UserManagement.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name="USER_MASTER")
public class UserManageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;

	@Column(name="FIRST_NAME")
	private String fname;
	
	@Column(name="LAST_NAME")
	private String lname;
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="ACC_STATUS")
	private String accStatus;
	
	
	@Column(name="PHONE")
	private long phone;
	
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
}
