package logic.implementationClasses;

import logic.bean.BeanCredentials;
import logic.implementationClasses.GoF.FactorySign;

public class Credentials {
	
	
	public boolean isValid(String username, String password) {
		//controlli semantico
		BeanCredentials beanC = new BeanCredentials();
		beanC.setUsername(username);
		beanC.setPassword(password);		
		FactorySign signFactory = new FactorySign();
		ValidateCredentialsInterface loginCTRL = signFactory.createSignCTRL(1);		
		if( loginCTRL.checkValid(beanC) ) {
			return true;
		}
		return false;
	}
	
	public boolean notExist(String username, String emailAddress, String password) {
		//controlli semantico
		BeanCredentials beanC = new BeanCredentials();
		beanC.setUsername(username);
		beanC.setEmailAddress(emailAddress);
		beanC.setPassword(password);
		FactorySign signFactory = new FactorySign();
		ValidateCredentialsInterface registrationCTRL = signFactory.createSignCTRL(2);
		if( registrationCTRL.checkValid(beanC) == true) {
			return true;
		}
		return false;
	}
	
}
