package com.UserManagement.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserManagement.bindings.LoginForm;
import com.UserManagement.bindings.UnlockAccForm;
import com.UserManagement.bindings.UserForm;
import com.UserManagement.entity.CityMasterEntity;
import com.UserManagement.entity.CountryMasterEntity;
import com.UserManagement.entity.StateMasterEntity;
import com.UserManagement.entity.UserManageEntity;
import com.UserManagement.repo.CityMasterRepo;
import com.UserManagement.repo.CountryMasterRepo;
import com.UserManagement.repo.StateMasterRepo;
import com.UserManagement.repo.UserMgmtRepo;
import com.UserManagement.utils.EmailsUtils;



@Service
public class UserManagementServiceImpl implements UserMgmtService {

	@Autowired
	private UserMgmtRepo userMgmtrepo;
    @Autowired
    private CountryMasterRepo countryrepo;
    
    @Autowired
    private StateMasterRepo staterepo;
    
    @Autowired
    private CityMasterRepo cityrepo;
    
    @Autowired
    private EmailsUtils emailutils;
	
    @Override
	public String LoginCheck(LoginForm loginForm) {
		
		UserManageEntity userAcc=userMgmtrepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if(userAcc==null)
		{
			return "Invalid Credentionals";
		}
		if(userAcc.getAccStatus().equals("LOCKED"))
		{
			return "Account is Locked";
		}
		return "SUCCESS";
	}

	@Override
	public String EmailCheck(String EmailId) {
		UserManageEntity userAcc=userMgmtrepo.findByEmail(EmailId);
		if(userAcc==null)
		{
			return "Unique";
		}
		
		return "Duplicate";
	}

	@Override
	public Map<Integer, String> LoadCountries() {
		List<CountryMasterEntity> findAll=countryrepo.findAll();
		Map<Integer,String> countryMap=new HashMap<>();
		findAll.forEach(country -> {
			countryMap.put(country.getCountryId(),country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> LoadStates(Integer CountryId) {
     List<StateMasterEntity> states=staterepo.findByCountryId(CountryId);
     Map<Integer,String> stateMap=new HashMap<>();
		states.forEach(state -> {
			stateMap.put(state.getStateId(),state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> LoadCity(Integer StateId) {
		List<CityMasterEntity> cities=cityrepo.findByStateId(StateId);
		 Map<Integer,String> cityMap=new HashMap<>();
			cities.forEach(city -> {
				cityMap.put(city.getCityId(),city.getCityName());
			});
		return cityMap;
	}

	@Override
	public String unlockUserAcc(UnlockAccForm unlockAccForm) {
	UserManageEntity userMng=userMgmtrepo.findByEmailAndPassword(unlockAccForm.getEmail(), unlockAccForm.getTempPwd());
	if(userMng==null)
	{
		return "Invalid Pwd";
	}
	userMng.setPassword(unlockAccForm.getNewPwd());
	userMng.setAccStatus("UNLOCKED");
	userMgmtrepo.save(userMng);
		return "SUCCESS";
		
	}

	@Override
	public String forgotPwd(String email) {
		UserManageEntity userMng=userMgmtrepo.findByEmail(email);
		if(userMng==null) {
			return "Invalid Email";
		}
		String Subject="Recover Password -AkshIT";
		String body=readForgotPwdEmailBody(userMng);
		emailutils.sendEmail(email, Subject, body);
		return "Password sent to registered email";
	}

	@Override
	public String SaveUSerForm(UserForm userForm) {
		
		userForm.setPassword(generateRandomPwd(6));  // need to generate random password;
		UserManageEntity userManage=new UserManageEntity();
		BeanUtils.copyProperties(userForm, userManage);
		userManage.setAccStatus("LOCKED");
		userMgmtrepo.save(userManage);
		
		String subject="User Registration";
		String emailBody=readUnlockAccEmailBody(userForm);
		emailutils.sendEmail(userForm.getEmail(),subject, emailBody);
		
		return "SUCCESS";
	}
	
	private  String generateRandomPwd(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }

	private String readUnlockAccEmailBody(UserForm userform)
	{
		String body="";
		StringBuffer buffer= new StringBuffer();
		Path filePath =Paths.get("Unlock-ACC-Email-Template.txt");
		
		try (Stream <String> stream=Files.lines(filePath)){
			stream.forEach(line ->{
				buffer.append(line);
			});
			
			body=buffer.toString();
			body=body.replace("{FNAME}",userform.getFname());
			body=body.replace("{LNAME}",userform.getLname());
			body=body.replace("{TEMP-PWD}",userform.getPassword());
			body=body.replace("{EMAIL}",userform.getEmail());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return body;
	}
	
	private String readForgotPwdEmailBody(UserManageEntity userEntity)
	{
		String body="";
		StringBuffer buffer= new StringBuffer();
		Path filePath =Paths.get("Recover-PWD-EMAIL-Body.txt");
		
		try (Stream <String> stream=Files.lines(filePath)){
			stream.forEach(line ->{
				buffer.append(line);
			});
			
			body=buffer.toString();
			body=body.replace("{FNAME}",userEntity.getFname());
			body=body.replace("{LNAME}",userEntity.getLname());
			body=body.replace("{PWD}",userEntity.getPassword());
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return body;
	}
	
}
