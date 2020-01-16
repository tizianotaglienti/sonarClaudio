package logic.controller;


import logic.bean.BeanCredentials;
import logic.entity.Admin;
import logic.entity.User;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.ValidateCredentialsInterface;
import logic.implementationClasses.GoF.FacadeCheckUsername;

public class LoginController implements ValidateCredentialsInterface {  
	
	@Override
	public boolean checkValid( BeanCredentials beanC ) {
		User admin = new Admin();
		admin.setUsername(beanC.getUsername());
		admin.setPassword(beanC.getPassword());
		FacadeCheckUsername check = new FacadeCheckUsername();
		if( !check.usernameExist(admin.getUsername()) ) return false;
		//validare se la pass e quella
		if( check.matchUserPass(admin.getUsername(), admin.getPassword()) ) {
			SingletonInstances.getSingletonInstance();
			SingletonInstances.setCurrentUser(admin);
			SingletonInstances.takeFridgeInstance();
			// devo prendere email e settarla in \admin/
			return true;
		}
		return false;
		
	}
	
	
	

}
